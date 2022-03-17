package com.binar.grab.service.impl;

import com.binar.grab.model.Karyawan;
import com.binar.grab.model.KaryawanTraining;
import com.binar.grab.model.Training;
import com.binar.grab.repository.KaryawanRepo;
import com.binar.grab.repository.KaryawanTrainingRepo;
import com.binar.grab.repository.TrainingRepo;
import com.binar.grab.service.KaryawanTrainingService;
import com.binar.grab.util.TemplateResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class KaryawanTrainingImpl implements KaryawanTrainingService {
    public static final Logger log = LoggerFactory.getLogger(KaryawanImpl.class);

    @Autowired
    public TemplateResponse templateResponse;
    @Autowired
    public KaryawanRepo karyawanRepo;
    @Autowired
    public TrainingRepo trainingRepo;
    @Autowired
    public KaryawanTrainingRepo karyawanTrainingRepo;

    @Override
    public Map insert(KaryawanTraining obj, Long idKaryawan, Long idTraining) {
        try {
            log.info("ini Karyawan : " + obj);
            if(templateResponse.checkNull(obj.getTanggalTraining())){
                return   templateResponse.templateError("Tanggal training tidak boleh null");
            }

            Karyawan checkIdKaryawan =  karyawanRepo.getById(idKaryawan);
            if(templateResponse.checkNull(checkIdKaryawan)){
                return   templateResponse.templateError("Id Karyawan tidak ada di database");
            }

            Training checkIdTraining =  trainingRepo.getById(idTraining);
            if(templateResponse.checkNull(checkIdKaryawan)){
                return   templateResponse.templateError("Id Training tidak ada di database");
            }

            //disimpan ke db: objek transaksi
            KaryawanTraining objSave = new KaryawanTraining();
            objSave.setKaryawan(checkIdKaryawan);
            objSave.setTraining(checkIdTraining);
            objSave.setTanggalTraining(obj.getTanggalTraining());
            com.binar.grab.model.KaryawanTraining doSave = karyawanTrainingRepo.save(objSave);
            return templateResponse.templateSukses(doSave);
        }catch (Exception e){
            return templateResponse.templateError(e);
        }

    }

    @Override
    public Map update(KaryawanTraining obj, Long idKaryawan, Long idTraining) {
        try {
            if (templateResponse.checkNull(obj.getTanggalTraining())){
                return templateResponse.templateError("Tanggal training tidak boleh kosong");
            }
            Karyawan checkIdKaryawan = karyawanRepo.getById(idKaryawan);
            if (templateResponse.checkNull(checkIdKaryawan)){
                return templateResponse.templateError("Id Karyawan tidak ditemukan");
            }
            Training checkIdTraining = trainingRepo.getById(idTraining);
            if (templateResponse.checkNull(checkIdTraining)){
                return templateResponse.templateError("Id Training tidak ditemukan");
            }
            KaryawanTraining checkIdKaryawanTraining = karyawanTrainingRepo.getById(obj.getId());
            if (templateResponse.checkNull(checkIdKaryawanTraining)){
                return templateResponse.templateError("Id tidak ditemukan");
            }
            checkIdKaryawanTraining.setTraining(checkIdTraining);
            checkIdKaryawanTraining.setKaryawan(checkIdKaryawan);
            checkIdKaryawanTraining.setTanggalTraining(obj.getTanggalTraining());
            KaryawanTraining save = karyawanTrainingRepo.save(checkIdKaryawanTraining);
            return templateResponse.templateSukses(save);
        }catch (Exception e){
            log.error("Error pada method update Karyawan Training");
            return templateResponse.templateError(e);
        }
    }

    @Override
    public Map delete(Long id) {
        try {
            if (templateResponse.checkNull(id)) {
                return templateResponse.templateError("Id Karyawan Training tidak boleh kosong");
            }
            KaryawanTraining checkIdKaryawanTraining = karyawanTrainingRepo.getById(id);
            if (templateResponse.checkNull(checkIdKaryawanTraining)) {
                return templateResponse.templateError("Id Karyawan Training tidak ditemukan");
            }

            checkIdKaryawanTraining.setDeleted_date(new Date());//
            karyawanTrainingRepo.save(checkIdKaryawanTraining);

            return templateResponse.templateSukses("sukses deleted");

        } catch (Exception e) {
            return templateResponse.templateError(e);
        }
    }
}

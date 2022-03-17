package com.binar.grab.service.impl;

import com.binar.grab.model.Karyawan;
import com.binar.grab.model.Rekening;
import com.binar.grab.model.Training;
import com.binar.grab.repository.DetailKaryawanRepo;
import com.binar.grab.repository.KaryawanRepo;
import com.binar.grab.repository.TrainingRepo;
import com.binar.grab.service.TrainingService;
import com.binar.grab.util.TemplateResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class TrainingImpl implements TrainingService {
    public static final Logger log = LoggerFactory.getLogger(KaryawanImpl.class);

    @Autowired
    public TemplateResponse templateResponse;
    @Autowired
    public TrainingRepo trainingRepo;

    @Override
    public Map insert(Training obj) {
        try{
            if (templateResponse.checkNull(obj.getTema())){
                return templateResponse.templateError("Tema tidak boleh kosong");
            }
            if (templateResponse.checkNull(obj.getNamaPengajar())){
                return templateResponse.templateError("Nama Pengajar tidak boleh kosong");
            }
            Training save = trainingRepo.save(obj);
            return templateResponse.templateSukses(save);
        }catch (Exception e){
            log.error("Error pada method insert Training");
            return templateResponse.templateError(e);
        }
    }

    @Override
    public Map update(Training obj) {
        try{
            if (templateResponse.checkNull(obj.getTema())){
                return templateResponse.templateError("Tema tidak boleh kosong");
            }
            if (templateResponse.checkNull(obj.getNamaPengajar())){
                return templateResponse.templateError("Nama Pengajar tidak boleh kosong");
            }

            Training checkIdTraining = trainingRepo.getById(obj.getId());
            if (templateResponse.checkNull(checkIdTraining)){
                return templateResponse.templateError("Id tidak sesuai");
            }

            checkIdTraining.setTema(obj.getTema());
            checkIdTraining.setNamaPengajar(obj.getNamaPengajar());

            Training save = trainingRepo.save(checkIdTraining);
            return templateResponse.templateSukses(save);
        }catch (Exception e){
            log.error("Error pada method update Training");
            return templateResponse.templateError(e);
        }
    }

    @Override
    public Map delete(Long id) {
        try {
            if (templateResponse.checkNull(id)){
                return templateResponse.templateError("Id Training tidak boleh kosong");
            }
            Training checkId = trainingRepo.getById(id);
            if (templateResponse.checkNull(checkId)){
                return templateResponse.templateError("Id Training tidak ditemukan");
            }

            checkId.setDeleted_date(new Date());
            trainingRepo.save(checkId);
            return templateResponse.templateSukses("Sukses Delete " + checkId.getTema());
        }catch (Exception e){
            log.error("Error pada method delete Rekening");
            return templateResponse.templateError(e);
        }
    }
}

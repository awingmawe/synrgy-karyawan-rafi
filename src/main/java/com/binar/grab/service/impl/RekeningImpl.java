package com.binar.grab.service.impl;

import com.binar.grab.model.Karyawan;
import com.binar.grab.model.Rekening;
import com.binar.grab.repository.KaryawanRepo;
import com.binar.grab.repository.RekeningRepo;
import com.binar.grab.service.RekeningService;
import com.binar.grab.util.TemplateResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.tomcat.util.json.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class RekeningImpl implements RekeningService {
    public static final Logger log = LoggerFactory.getLogger(RekeningImpl.class);

    @Autowired
    public TemplateResponse templateResponse;
    @Autowired
    public KaryawanRepo karyawanRepo;
    @Autowired
    public RekeningRepo rekeningRepo;

    @Override
    public Map insert(Rekening obj, Long id) {
        try{
            if (templateResponse.checkNull(obj.getNama())){
                return templateResponse.templateError("Nama tidak boleh kosong");
            }
            if (templateResponse.checkNull(obj.getJenis())){
                return templateResponse.templateError("Jenis tidak boleh kosong");
            }
            if (templateResponse.checkNull(obj.getNomor())){
                return templateResponse.templateError("Nomor tidak boleh kosong");
            }
            Karyawan checkId = karyawanRepo.getById(id);
            if (templateResponse.checkNull(checkId)){
                return templateResponse.templateError("Id tidak sesuai");
            }
            obj.setKaryawan(checkId);
            Rekening save = rekeningRepo.save(obj);
            return templateResponse.templateSukses(save);
        }catch (Exception e){
            log.error("Error pada method insert Rekening");
            return templateResponse.templateError(e);
        }
    }

    @Override
    public Map update(Rekening obj, Long id) {
        try{
            if (templateResponse.checkNull(obj.getNama())){
                return templateResponse.templateError("Nama tidak boleh kosong");
            }
            if (templateResponse.checkNull(obj.getJenis())){
                return templateResponse.templateError("Jenis tidak boleh kosong");
            }
            if (templateResponse.checkNull(obj.getNomor())){
                return templateResponse.templateError("Nomor tidak boleh kosong");
            }
            log.info("Ini rekening : " + obj);

            Karyawan checkIdKaryawan = karyawanRepo.getById(id);
            if (templateResponse.checkNull(checkIdKaryawan)){
                return templateResponse.templateError("Id tidak sesuai");
            }

            Rekening checkIdRekening = rekeningRepo.getById(obj.getId());
            if (templateResponse.checkNull(checkIdRekening)){
                return templateResponse.templateError("Id tidak sesuai");
            }

            checkIdRekening.setNama(obj.getNama());
            checkIdRekening.setJenis(obj.getJenis());
            checkIdRekening.setNomor(obj.getNomor());
            Rekening doSave = rekeningRepo.save(checkIdRekening);
            return templateResponse.templateSukses(doSave);
        }catch (Exception e){
            log.error("Error pada method update Rekening");
            return templateResponse.templateError(e);
        }
    }

    @Override
    public Map delete(Long idRekening) {
        try {
            if (templateResponse.checkNull(idRekening)){
                return templateResponse.templateError("Id Rekening tidak boleh kosong");
            }
            Rekening checkId = rekeningRepo.getById(idRekening);
            if (templateResponse.checkNull(checkId)){
                return templateResponse.templateError("Id Rekening tidak ditemukan");
            }

            checkId.setDeleted_date(new Date());
            rekeningRepo.save(checkId);
            return templateResponse.templateSukses("Sukses Delete " + checkId.getNama());
        }catch (Exception e){
            log.error("Error pada method delete Rekening");
            return templateResponse.templateError(e);
        }
    }
}

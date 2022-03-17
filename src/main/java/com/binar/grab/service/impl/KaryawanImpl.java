package com.binar.grab.service.impl;

import com.binar.grab.model.DetailKaryawan;
import com.binar.grab.model.Karyawan;
import com.binar.grab.repository.DetailKaryawanRepo;
import com.binar.grab.repository.KaryawanRepo;
import com.binar.grab.service.KaryawanService;
import com.binar.grab.util.TemplateResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class KaryawanImpl implements KaryawanService{
    public static final Logger log = LoggerFactory.getLogger(KaryawanImpl.class);

    @Autowired
    public TemplateResponse templateResponse;
    @Autowired
    public KaryawanRepo karyawanRepo;
    @Autowired
    public DetailKaryawanRepo detailKaryawanRepo;

    @Override
    public Map insert(Karyawan obj) {
        try{
            // Cek apakah request dari klien kosong atau tidak
            log.info("ini karyawan : " + obj);
            if(templateResponse.checkNull(obj.getNama())){
                return templateResponse.templateError("Nama tidak boleh kosong");
            }
            if (templateResponse.checkNull(obj.getDetailKaryawan())){
                return templateResponse.templateError("Detail karyawan tidak boleh kosong");
            }
            if (templateResponse.checkNull(obj.getDetailKaryawan().getAlamat())){
                return templateResponse.templateError("Alamat tidak boleh kosong");
            }if (templateResponse.checkNull(obj.getDetailKaryawan().getNik())){
                return templateResponse.templateError("NIK tidak boleh kosong");
            }if (templateResponse.checkNull(obj.getDetailKaryawan().getNpwp())){
                return templateResponse.templateError("NPWP tidak boleh kosong");
            }if (templateResponse.checkNull(obj.getDetailKaryawan().getJk())){
                return templateResponse.templateError("JK tidak boleh kosong");
            }
            // Save data karyawan
            Karyawan karyawanData = new Karyawan();
            karyawanData.setNama(obj.getNama());
            Karyawan saveKaryawan = karyawanRepo.save(karyawanData);

            // Save data detail karyawan
            DetailKaryawan detailKaryawanData = new DetailKaryawan();
            detailKaryawanData.setAlamat(obj.getDetailKaryawan().getAlamat());
            detailKaryawanData.setNik(obj.getDetailKaryawan().getNik());
            detailKaryawanData.setNpwp(obj.getDetailKaryawan().getNpwp());
            detailKaryawanData.setJk(obj.getDetailKaryawan().getJk());
            detailKaryawanData.setKaryawan(karyawanData);
            saveKaryawan.setDetailKaryawan(detailKaryawanData);

            detailKaryawanRepo.save(detailKaryawanData);
            return templateResponse.templateSukses(saveKaryawan);
        }catch (Exception e){
//            log.error("Error pada method insert Karyawan");
            return templateResponse.templateError(e);
        }
    }

    @Override
    public Map update(Karyawan obj) {
        try{
            // Cek apakah request dari klien kosong atau tidak
            if(templateResponse.checkNull(obj.getId())){
                return templateResponse.templateError("Id tidak boleh kosong");
            }
            if (templateResponse.checkNull(obj.getDetailKaryawan())){
                return templateResponse.templateError("Detail karyawan tidak boleh kosong");
            }
            if (templateResponse.checkNull(obj.getDetailKaryawan().getAlamat())){
                return templateResponse.templateError("Alamat tidak boleh kosong");
            }if (templateResponse.checkNull(obj.getDetailKaryawan().getNik())){
                return templateResponse.templateError("NIK tidak boleh kosong");
            }if (templateResponse.checkNull(obj.getDetailKaryawan().getNpwp())){
                return templateResponse.templateError("NPWP tidak boleh kosong");
            }if (templateResponse.checkNull(obj.getDetailKaryawan().getJk())){
                return templateResponse.templateError("JK tidak boleh kosong");
            }
            Karyawan update = karyawanRepo.getById(obj.getId());
            // Cek apakah id dari database dengan request klien ada atau tidak
            if (templateResponse.checkNull(update)){
                return templateResponse.templateError("Id tidak ditemukan");
            }
            // Update data
            update.setNama(obj.getNama());
            update.getDetailKaryawan().setNpwp(obj.getDetailKaryawan().getNpwp());
            update.getDetailKaryawan().setNik(obj.getDetailKaryawan().getNik());
            update.getDetailKaryawan().setAlamat(obj.getDetailKaryawan().getAlamat());
            update.getDetailKaryawan().setJk(obj.getDetailKaryawan().getJk());

            // Save data
            Karyawan save = karyawanRepo.save(update);
            return templateResponse.templateSukses(save);
        }catch (Exception e){
            log.error("Error pada method update Karyawan");
            System.err.println(e.getMessage());
            return templateResponse.templateError(e);
        }
    }

    @Override
    public Map delete(Long idKaryawan) {
        try {
            if (templateResponse.checkNull(idKaryawan)){
                return templateResponse.templateError("Id Karyawan kosong");
            }
            Karyawan checkId = karyawanRepo.getById(idKaryawan);
            if (templateResponse.checkNull(checkId)){
                return templateResponse.templateError("Id Karyawan tidak ditemukan");
            }

            checkId.setDeleted_date(new Date());
            karyawanRepo.save(checkId);
            return templateResponse.templateSukses("Sukses Delete " + checkId.getNama());
        }catch (Exception e){
            log.error("Error pada method delete karyawan");
            return templateResponse.templateError(e);
        }
    }

    @Override
    public Map findByNama(String nama) {
        try{
            if (templateResponse.checkNull(nama)){
                return templateResponse.templateError("Nama tidak boleh kosong");
            }
            Karyawan getNama = karyawanRepo.findByNamaIgnoreCase(nama);
            if (templateResponse.checkNull(getNama)){
                return templateResponse.templateError("Nama tidak sesuai");
            }
            return templateResponse.templateSukses(getNama);
        }catch (Exception e){
            log.error("Error pada method findByNama");
            return templateResponse.templateError(e);
        }
    }
}

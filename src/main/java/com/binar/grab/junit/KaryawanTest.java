package com.binar.grab.junit;


import com.binar.grab.controller.KaryawanController;
import com.binar.grab.model.DetailKaryawan;
import com.binar.grab.model.Karyawan;
import com.binar.grab.repository.KaryawanRepo;
import com.binar.grab.service.KaryawanService;
import com.binar.grab.service.impl.KaryawanImpl;
import com.binar.grab.service.impl.KaryawanRestTemplateImpl;
import com.binar.grab.util.ConvertJson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class KaryawanTest {
    public static final Logger log = LoggerFactory.getLogger(KaryawanTest.class);

    @Autowired
    public KaryawanRestTemplateImpl impl;

    @Qualifier("karyawanRestTemplateImpl")
    @Autowired
    public KaryawanService karyawanService;

    @Autowired
    private ConvertJson convertJson;

    @Test
    public void insertKaryawanTest(){
        Karyawan karyawan = new Karyawan();
        DetailKaryawan detailKaryawan = new DetailKaryawan();
        karyawan.setNama("Rafi");
        detailKaryawan.setAlamat("Bandung");
        detailKaryawan.setNik("1234");
        detailKaryawan.setNpwp("4321");
        detailKaryawan.setJk("Ada");
        karyawan.setDetailKaryawan(detailKaryawan);

        // Tanpa Rest Template
        // ResponseEntity<Map> map = karyawanController.save(karyawan);

        // Dengan Rest Template
        Map map = karyawanService.insert(karyawan);

        String status = (String) map.get("status");
        if (status.equals("200")){
            System.out.println("Data " + convertJson.toJson(map));
            System.out.println("Berhasil!");
        }else{
            System.out.println("Error code : " + status);
            System.out.println("Message : " + map.get("message"));
        }
        Assert.assertEquals("200", status);
    }

    @Test
    public void updateKaryawanTest(){
        Karyawan karyawanUpdate = new Karyawan();
        DetailKaryawan detailKaryawan = new DetailKaryawan();
        karyawanUpdate.setId(54L);
        karyawanUpdate.setNama("Rafi Baru");
        detailKaryawan.setAlamat("Jakarta");
        detailKaryawan.setNik("1234");
        detailKaryawan.setNpwp("4321");
        detailKaryawan.setJk("Ada");
        karyawanUpdate.setDetailKaryawan(detailKaryawan);

        // Tanpa Rest Template
        // ResponseEntity<Map> map = karyawanController.update(karyawanUpdate);

        // Dengan Rest Template
        Map map = karyawanService.update(karyawanUpdate);

        String status = (String) map.get("status");
        if (status.equals("200")){
            System.out.println("Data sesudah" + convertJson.toJson(map.get("data")));
            System.out.println("Berhasil!");
        }else{
            System.out.println("Error code : " + status);
            System.out.println("Message : " + convertJson.toJson(map.get("message")));
        }
        Assert.assertEquals("200", status);
    }

    @Test
    public void getAllData(){
        Map map = impl.getData();
        String status = (String) map.get("status");
        if (status.equals("success")){
            System.out.println(convertJson.toJson(map.get("data")));
            System.out.println("Berhasil!");
        }else{
            System.out.println("Error code : " + status);
            System.out.println("Message : " + convertJson.toJson(map.get("message")));
        }
        Assert.assertEquals("success", status);
    }

    @Test
    public void deleteData(){
        Map map = karyawanService.delete(50L);
        String status = (String) map.get("status");
        if (status.equals("200")){
            System.out.println(convertJson.toJson(map.get("data")));
            System.out.println("Berhasil!");
        }else{
            System.out.println("Error code : " + status);
            System.out.println("Message : " + convertJson.toJson(map.get("message")));
        }
        Assert.assertEquals("200", status);
    }
}

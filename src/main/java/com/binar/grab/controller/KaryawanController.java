package com.binar.grab.controller;

import com.binar.grab.config.Config;
import com.binar.grab.model.Karyawan;
import com.binar.grab.repository.KaryawanRepo;
import com.binar.grab.repository.oauth.UserRepository;
import com.binar.grab.service.KaryawanService;
import com.binar.grab.service.oauth.Oauth2UserDetailsService;
import com.binar.grab.util.SimpleStringUtils;
import com.binar.grab.util.TemplateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/grab/karyawan/")
public class KaryawanController {

    Config config = new Config();

    @Qualifier("karyawanImpl")
    @Autowired
    public KaryawanService karyawanService;

    @Autowired
    public KaryawanRepo karyawanRepo;

    @Autowired
    public TemplateResponse templateResponse;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    private Oauth2UserDetailsService userDetailsService;

    SimpleStringUtils simpleStringUtils = new SimpleStringUtils();

    @PostMapping("")
    public ResponseEntity<Map> save(
            @RequestBody Karyawan obj){
        try{
            Map save = karyawanService.insert(obj);
            return new ResponseEntity<Map>(save, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Map>((Map) e, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("update")
    public ResponseEntity<Map> update(
            @RequestBody Karyawan obj){
        Map update = karyawanService.update(obj);
        return new ResponseEntity<Map>(update, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Map> delete(@PathVariable(value = "id") Long id){
        Map delete = karyawanService.delete(id);
        return new ResponseEntity<Map>(delete, HttpStatus.OK);
    }

    // Pencarian berdasarkan link parameter
    @GetMapping("{nama}")
    public ResponseEntity<Map> findByNama(@PathVariable(value = "nama") String nama){
        Map findNama = karyawanService.findByNama(nama);
        return new ResponseEntity<Map>(findNama, HttpStatus.OK);
    }

    @GetMapping("listKaryawan")
    public ResponseEntity<Map> getList(@RequestParam(required = false) String namaKaryawan){
        Map map = new HashMap();
        if (namaKaryawan != null && !namaKaryawan.isEmpty()){
            Karyawan findNama = karyawanRepo.findByNama("%"+namaKaryawan+"%");
            map.put("data", findNama);
            map.put("code", "200");
        }else{
            map.put("data", karyawanRepo.findAll());
            map.put("code", "200");
            map.put("status", "success");
        }
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }
}

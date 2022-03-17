package com.binar.grab.controller;

import com.binar.grab.model.Karyawan;
import com.binar.grab.model.Rekening;
import com.binar.grab.repository.KaryawanRepo;
import com.binar.grab.repository.RekeningRepo;
import com.binar.grab.service.KaryawanService;
import com.binar.grab.service.RekeningService;
import com.binar.grab.util.TemplateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/grab/rekening/")
public class RekeningController {

    @Autowired
    public RekeningService rekeningService;

    @Autowired
    public RekeningRepo rekeningRepo;

    @Autowired
    public TemplateResponse templateResponse;

    @PostMapping("{id}")
    public ResponseEntity<Map> save(@PathVariable(value = "id") Long id ,@RequestBody Rekening obj ){
        Map save = rekeningService.insert(obj, id);
        return new ResponseEntity<Map>(save, HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Map> update(@PathVariable(value = "id") Long id, @RequestBody Rekening obj){
        Map update = rekeningService.update(obj, id);
        return new ResponseEntity<Map>(update, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Map> delete(@PathVariable(value = "id") Long id){
        Map delete = rekeningService.delete(id);
        return new ResponseEntity<Map>(delete, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Map> getList(@RequestParam(required = false) String nama){
        Map map = new HashMap();
        if (nama != null && !nama.isEmpty()){
            Rekening findNama = rekeningRepo.findByNamaIgnoreCase("%"+nama+"%");
            map.put("data", findNama);
            map.put("code", "200");
        }else{
            map.put("data", rekeningRepo.findAll());
            map.put("code", "200");
            map.put("status", "success");
        }
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }
}

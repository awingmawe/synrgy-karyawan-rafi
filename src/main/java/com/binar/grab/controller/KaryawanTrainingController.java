package com.binar.grab.controller;

import com.binar.grab.model.KaryawanTraining;
import com.binar.grab.repository.KaryawanTrainingRepo;
import com.binar.grab.service.KaryawanTrainingService;
import com.binar.grab.util.TemplateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/binar/karyawanTraining")
public class KaryawanTrainingController {
    @Autowired
    public KaryawanTrainingService karyawanTrainingService;

    @Autowired
    public TemplateResponse templateResponse;

    @Autowired
    public KaryawanTrainingRepo karyawanTrainingRepo;

    @PostMapping("")
    public ResponseEntity<Map> save(
            @RequestBody KaryawanTraining objModel,
            @RequestParam Long idKaryawan,
            @RequestParam Long idTraining) {
        Map obj = karyawanTrainingService.insert(objModel, idKaryawan, idTraining);
        return new ResponseEntity<Map>(obj, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<Map> update(@RequestBody KaryawanTraining objModel,
                                      @RequestParam Long idKaryawan,
                                      @RequestParam Long idTraining ) {
        Map map = karyawanTrainingService.update(objModel, idKaryawan, idTraining);
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map> delete(@PathVariable(value = "id") Long id) {
        Map map = karyawanTrainingService.delete(id);
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Map> listByBama(
            @RequestParam(required = false) String tanggalTraining) {
        Map map = new HashMap();

        if(tanggalTraining != null && !tanggalTraining.isEmpty()){
            map.put("data", karyawanTrainingRepo.findKaryawanTrainingByTanggalTrainingLike("%"+tanggalTraining+"%"));
        } else{
            map.put("data", karyawanTrainingRepo.findAll());
        }
        return new ResponseEntity<Map>(templateResponse.templateSukses(map), new HttpHeaders(), HttpStatus.OK);
    }


}

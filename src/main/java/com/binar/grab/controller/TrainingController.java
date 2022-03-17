package com.binar.grab.controller;

import com.binar.grab.model.Rekening;
import com.binar.grab.model.Training;
import com.binar.grab.repository.TrainingRepo;
import com.binar.grab.service.TrainingService;
import com.binar.grab.util.TemplateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/grab/training/")
public class TrainingController {

    @Autowired
    public TrainingService trainingService;

    @Autowired
    public TrainingRepo trainingRepo;

    @Autowired
    public TemplateResponse templateResponse;

    @PostMapping("")
    public ResponseEntity<Map> save(@RequestBody Training obj ){
        Map save = trainingService.insert(obj);
        return new ResponseEntity<Map>(save, HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<Map> update(@RequestBody Training obj){
        Map update = trainingService.update(obj);
        return new ResponseEntity<Map>(update, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Map> delete(@PathVariable(value = "id") Long id){
        Map delete = trainingService.delete(id);
        return new ResponseEntity<Map>(delete, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Map> getList(@RequestParam(required = false) String tema){
        Map map = new HashMap();
        if (tema != null && !tema.isEmpty()){
            Training findNama = trainingRepo.findTrainingByTemaLike("%"+tema+"%");
            map.put("data", findNama);
            map.put("code", "200");
        }else{
            map.put("data", trainingRepo.findAll());
            map.put("code", "200");
            map.put("status", "success");
        }
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }
}

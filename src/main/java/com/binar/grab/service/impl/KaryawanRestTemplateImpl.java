package com.binar.grab.service.impl;

import com.binar.grab.model.Karyawan;
import com.binar.grab.repository.KaryawanRepo;
import com.binar.grab.service.KaryawanService;
import com.binar.grab.util.TemplateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;


import java.util.Map;

@Service
public class KaryawanRestTemplateImpl implements KaryawanService {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    public KaryawanRepo karyawanRepo;

    @Autowired
    public TemplateResponse templateResponse;


    @Override
    public Map insert(Karyawan obj) {
        try {
            String url = "http://localhost:9090/api/v1/grab/karyawan/";
            ResponseEntity<Map> result = restTemplateBuilder.build().postForEntity(url, obj, Map.class);
            return result.getBody();
        }catch (Exception e){
            return templateResponse.templateError(e);
        }
    }

    @Override
    public Map update(Karyawan obj) {
        try{
            if (templateResponse.checkNull(obj.getId())) {
                return templateResponse.templateError("Id Karyawan is required");
            }
            Karyawan checkId = karyawanRepo.getById(obj.getId());
            if (templateResponse.checkNull(checkId)){
                return templateResponse.templateError("Id Karyawan not found");
            }
            RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
            String url = "http://localhost:9090/api/v1/grab/karyawan/update";
            HttpHeaders headers = new HttpHeaders();
            // set `content-type` header
            headers.setContentType(MediaType.APPLICATION_JSON);
            // set `accept` header
            headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

            // build the request
            HttpEntity<Karyawan> entity = new HttpEntity<>(obj, headers);

            // send PUT request to update post with `id` 10
            ResponseEntity<Map> response = restTemplateBuilder.build().exchange(url, HttpMethod.PUT, entity, Map.class);

            // check response status code
            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else {
                System.out.println("error");
                return templateResponse.templateError(response.getBody());
            }
        }catch (Exception e){
            return templateResponse.templateError(e);
        }
    }

    @Override
    public Map delete(Long idKaryawan) {
        try {
            if (templateResponse.checkNull(idKaryawan)) {
                return templateResponse.templateError("Id Karyawan is required");
            }
            //1. chek id barang
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", "*/*");
            headers.set("Content-Type", "application/json");
            HttpEntity<String> entity = new HttpEntity<String>(null, headers);

            RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
            ResponseEntity<Map> exchange = restTemplateBuilder.build().exchange("http://localhost:9090/api/v1/grab/karyawan/delete/"+idKaryawan , HttpMethod.DELETE, entity, Map.class);

            System.out.println("response karyawan = "+exchange.getBody());

            return exchange.getBody();

        } catch (Exception e) {
            return templateResponse.templateError(e);
        }
    }

    @Override
    public Map findByNama(String nama) {
        return null;
    }

    public Map getData(){
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Accept", MediaType.APPLICATION_JSON_VALUE);
            final String url = "http://localhost:9090/api/v1/grab/karyawan/listKaryawan";
            HttpEntity<?> httpEntity  = new HttpEntity<>(httpHeaders);
            ResponseEntity<Map> result = restTemplateBuilder.build().exchange(url, HttpMethod.GET, httpEntity,Map.class);
            return result.getBody();
        } catch (Exception e) {
            return templateResponse.templateError(e);
        }

    }
}

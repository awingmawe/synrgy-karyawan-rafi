package com.binar.grab.service;

import com.binar.grab.model.DetailKaryawan;
import com.binar.grab.model.Karyawan;
import com.binar.grab.model.Rekening;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface KaryawanService {
    public Map insert(Karyawan obj);
    public Map update(Karyawan obj);
    public Map delete(Long idKaryawan);
    public Map findByNama(String nama);
}

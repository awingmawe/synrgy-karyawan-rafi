package com.binar.grab.service;

import com.binar.grab.model.Karyawan;
import com.binar.grab.model.Rekening;

import java.util.Map;

public interface RekeningService {
    public Map insert(Rekening obj, Long id);
    public Map update(Rekening obj, Long id);
    public Map delete(Long id);
}

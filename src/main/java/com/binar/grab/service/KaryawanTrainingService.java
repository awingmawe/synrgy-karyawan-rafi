package com.binar.grab.service;

import com.binar.grab.model.KaryawanTraining;

import java.util.Map;

public interface KaryawanTrainingService {
    public Map insert(KaryawanTraining obj, Long idKaryawan, Long idTraining);
    public Map update(KaryawanTraining obj, Long idKaryawan, Long idTraining);
    public Map delete(Long id);
}

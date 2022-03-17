package com.binar.grab.repository;

import com.binar.grab.model.Karyawan;
import com.binar.grab.model.Rekening;
import com.binar.grab.model.Training;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface TrainingRepo extends PagingAndSortingRepository<Training, Long> {
    @Query("select c from Training c where c.id = :id")
    public Training getById(@Param("id") Long id);

    public Training findTrainingByTemaLike(String tema);
}

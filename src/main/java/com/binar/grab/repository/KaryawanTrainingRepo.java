package com.binar.grab.repository;

import com.binar.grab.model.KaryawanTraining;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface KaryawanTrainingRepo extends PagingAndSortingRepository<KaryawanTraining, Long> {
    @Query("select c from KaryawanTraining c where c.id = :id")
    public KaryawanTraining getById(@Param("id") Long id);

    public KaryawanTraining findKaryawanTrainingByTanggalTrainingLike(String nama);
}

package com.binar.grab.repository;

import com.binar.grab.model.Karyawan;
import com.binar.grab.model.Rekening;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface RekeningRepo extends PagingAndSortingRepository<Rekening, Long> {
    @Query("select c from Rekening c where c.id = :id")
    public Rekening getById(@Param("id") Long id);

    public Rekening findByNamaIgnoreCase(String nama);
}

package com.binar.grab.repository;

import com.binar.grab.model.DetailKaryawan;
import com.binar.grab.model.Karyawan;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailKaryawanRepo extends PagingAndSortingRepository<DetailKaryawan, Long> {
    @Query("select c from Karyawan c where c.id = :id")
    public DetailKaryawan getById(@Param("id") Long id);

    @Query("select c from DetailKaryawan c where c.nik like :nik")
    public DetailKaryawan findByNik(String nik);
}


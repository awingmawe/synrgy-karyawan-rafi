package com.binar.grab.repository;

import com.binar.grab.model.Karyawan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface KaryawanRepo extends PagingAndSortingRepository<Karyawan, Long> {
    @Query("select c from Karyawan c where c.id = :id")
    public Karyawan getById(@Param("id") Long id);

    public Karyawan findByNamaIgnoreCase(String nama);

    @Query("select c from Karyawan c where c.nama like :namaKaryawan")
    public Karyawan findByNama(String namaKaryawan);
}

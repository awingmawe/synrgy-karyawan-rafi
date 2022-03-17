package com.binar.grab.model;

import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "karyawan")
@Where(clause = "deleted_date is null")
public class Karyawan extends AbstractDate implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama", nullable = false, length = 45)
    private String nama;

    //step 1 - relasi one to one
    @OneToOne(mappedBy = "karyawan")
    private DetailKaryawan detailKaryawan;

//    @OneToMany(mappedBy = "karyawan")
//    List<KaryawanTraining> karyawanTraining;

//    @OneToMany(mappedBy = "karyawan")
//    List<Rekening> rekening;
}

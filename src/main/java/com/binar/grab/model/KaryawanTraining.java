package com.binar.grab.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "karyawantraining")
@Where(clause = "deleted_date is null")
public class KaryawanTraining extends AbstractDate implements Serializable {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(targetEntity = Training.class, cascade = CascadeType.ALL)
    Training training;

    @ManyToOne(targetEntity = Karyawan.class, cascade = CascadeType.ALL)
    Karyawan karyawan;

    private String tanggalTraining;
}

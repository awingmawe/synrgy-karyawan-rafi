package com.binar.grab.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "detailkaryawan")
public class DetailKaryawan implements Serializable {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nik", nullable = false, length = 15)
    private String nik;

    @Column(name = "npwp", nullable = false, length = 15)
    private String npwp;

    @Column(name = "jk")
    private String jk;

    @Column(name = "alamat", columnDefinition="TEXT")
    private String alamat;

    @Column(name = "status")
    private String status;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_karyawan", referencedColumnName = "id")
    private Karyawan karyawan;
}

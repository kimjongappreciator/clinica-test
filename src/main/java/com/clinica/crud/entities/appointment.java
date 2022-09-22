package com.clinica.crud.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Data
public class appointment {

    @Id
    @GeneratedValue
    private Long id;
    @Column(length = 50, nullable = true)
    private Date date;
    @Column(length = 50, nullable = true)
    private Long hospitalId;
    @Column(length = 50, nullable = true)
    private Long patientId;
    @Column(length = 50, nullable = true)
    private Long doctorId;

    @Transient
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "hospital_id")
    private hospital hospital;

    @Transient
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_dni")
    private patient patient;

    @Transient
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_dni")
    private doctor doctor;


}

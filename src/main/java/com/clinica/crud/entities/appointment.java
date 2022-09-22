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
    private Date date = new Date();
    @Column(length = 50, nullable = true)
    private Long hospitalId;
    @Column(length = 50, nullable = true)
    private Long patientId;
    @Column(length = 50, nullable = true)
    private Long doctorId;

    @Transient
    private hospital hospital;
    @Transient
    private patient patient;
    @Transient
    private doctor doctor;


}

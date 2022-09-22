package com.clinica.crud.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class doctor {
    @Id
    @GeneratedValue
    private Long id;
    @Column(length = 50, nullable = true)
    private String name;
    @Column(length = 50, nullable = true)
    private String surname;
    @Column(length = 50, nullable = true, unique = true)
    private String dni;
    @Column(length = 50, nullable = true)
    private String speciality;
}

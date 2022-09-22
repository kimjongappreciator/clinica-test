package com.clinica.crud.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class hospital {
    @Id
    @GeneratedValue
    private Long id;
    @Column(length = 50, nullable = true)
    private String name;
    @Column(length = 50, nullable = true)
    private String district;
    @Column(length = 50, nullable = true)
    private String Address;
}

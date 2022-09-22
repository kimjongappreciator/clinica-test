package com.clinica.crud.services;

import com.clinica.crud.entities.doctor;

import java.util.List;

public interface doctorService extends CrudService<doctor, Long>{
    List<doctor> findBySpeciality();
    doctor findByDni();
}

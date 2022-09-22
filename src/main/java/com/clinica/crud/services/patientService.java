package com.clinica.crud.services;

import com.clinica.crud.entities.patient;

public interface patientService extends CrudService<patient, Long>{
    patient findByDni(String dni) throws Exception;
}

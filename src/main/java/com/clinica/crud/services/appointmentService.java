package com.clinica.crud.services;

import com.clinica.crud.entities.appointment;

import java.util.Date;
import java.util.List;

public interface appointmentService extends CrudService<appointment, Long>{
    List<appointment> findByPatientId(Long patientId) throws Exception;
    List<appointment> findByHospitalId(Long hospitalId) throws Exception;
    List<appointment> findByDoctorId(Long doctorId) throws Exception;
    List<appointment> findByDate(Date date) throws Exception;
}

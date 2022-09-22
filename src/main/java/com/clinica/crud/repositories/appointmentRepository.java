package com.clinica.crud.repositories;

import com.clinica.crud.entities.appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface appointmentRepository extends JpaRepository<appointment, Long> {
    List<appointment> findByPatientId(Long patientId) throws Exception;
    List<appointment> findByHospitalId(Long hospitalId) throws Exception;
    List<appointment> findByDoctorId(Long doctorId) throws Exception;
    //List<appointment> findByDate(Date date) throws Exception;
}

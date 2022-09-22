package com.clinica.crud.repositories;

import com.clinica.crud.entities.patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface patientRepository extends JpaRepository<patient, Long> {
    patient findByDni(String dni) throws Exception;
    }

package com.clinica.crud.repositories;

import com.clinica.crud.entities.doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface doctorRepository extends JpaRepository<doctor, Long> {
    List<doctor> findBySpeciality(String speciality);
    doctor findByDni(String dni);
}

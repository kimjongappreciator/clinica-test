package com.clinica.crud.repositories;

import com.clinica.crud.entities.hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface hospitalRepository extends JpaRepository<hospital, Long> {
}

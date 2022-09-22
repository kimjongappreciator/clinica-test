package com.clinica.crud.services.impls;

import com.clinica.crud.entities.doctor;
import com.clinica.crud.repositories.doctorRepository;
import com.clinica.crud.services.doctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class doctorServiceImpl implements doctorService {

    @Autowired
    doctorRepository doctorRepo;
    @Override
    public doctor save(doctor entity) throws Exception {
        return doctorRepo.save(entity);
    }

    @Override
    public List<doctor> findAll() throws Exception {
        return doctorRepo.findAll();
    }

    @Override
    public Optional<doctor> findById(Long aLong) throws Exception {
        doctor doctor = doctorRepo.findById(aLong).orElse(null);
        return Optional.ofNullable(doctor);
    }

    @Override
    public doctor update(doctor entity, Long id) throws Exception {
        entity.setDni(id);
        return this.doctorRepo.save(entity);
    }

    @Override
    public void deleteById(Long aLong) throws Exception {
        doctorRepo.deleteById(aLong);

    }

    @Override
    public List<doctor> findBySpeciality(String speciality) throws Exception{
        return doctorRepo.findBySpeciality(speciality);
    }
}

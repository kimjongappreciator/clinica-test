package com.clinica.crud.services.impls;

import com.clinica.crud.entities.patient;
import com.clinica.crud.repositories.patientRepository;
import com.clinica.crud.services.patientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class patientServiceImpl implements patientService {

    @Autowired
    patientRepository patientRepo;

    @Override
    public patient save(patient entity) throws Exception {
        return patientRepo.save(entity);
    }

    @Override
    public List<patient> findAll() throws Exception {
        return patientRepo.findAll();
    }

    @Override
    public Optional<patient> findById(Long aLong) throws Exception {
        patient patient = patientRepo.findById(aLong).orElse(null);
        return Optional.ofNullable(patient);
    }

    @Override
    public patient update(patient entity, Long id) throws Exception {
        entity.setDni(id);
        return this.patientRepo.save(entity);
    }

    @Override
    public void deleteById(Long aLong) throws Exception {
        patientRepo.deleteById(aLong);

    }
}

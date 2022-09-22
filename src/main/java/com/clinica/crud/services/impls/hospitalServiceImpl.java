package com.clinica.crud.services.impls;

import com.clinica.crud.entities.hospital;
import com.clinica.crud.repositories.hospitalRepository;
import com.clinica.crud.services.hospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class hospitalServiceImpl implements hospitalService {

    @Autowired
    hospitalRepository hospitalRepository;

    @Override
    public hospital save(hospital entity) throws Exception {
        return hospitalRepository.save(entity);
    }

    @Override
    public List<hospital> findAll() throws Exception {
        return hospitalRepository.findAll();
    }

    @Override
    public Optional<hospital> findById(Long aLong) throws Exception {
        hospital temp = hospitalRepository.findById(aLong).orElse(null);
        return Optional.ofNullable(temp);
    }

    @Override
    public hospital update(hospital entity, Long id) throws Exception {
        entity.setId(id);
        return this.hospitalRepository.save(entity);
    }

    @Override
    public void deleteById(Long aLong) throws Exception {
        hospitalRepository.deleteById(aLong);

    }
}

package com.clinica.crud.services.impls;

import com.clinica.crud.entities.appointment;
import com.clinica.crud.repositories.appointmentRepository;
import com.clinica.crud.repositories.doctorRepository;
import com.clinica.crud.repositories.hospitalRepository;
import com.clinica.crud.repositories.patientRepository;
import com.clinica.crud.services.appointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class appointmentServiceImpl implements appointmentService {

    @Autowired
    appointmentRepository aRepo;
    @Autowired
    doctorRepository dRepo;
    @Autowired
    hospitalRepository hRepo;
    @Autowired
    patientRepository pRepo;


    @Override
    public appointment save(appointment entity) throws Exception {
        return aRepo.save(entity);
    }

    @Override
    public List<appointment> findAll() throws Exception {
        return aRepo.findAll();
    }

    @Override
    public Optional<appointment> findById(Long aLong) throws Exception {
        appointment appointment = aRepo.findById(aLong).orElse(null);
        Long doctor = appointment.getDoctorId();
        Long patient = appointment.getPatientId();
        Long hospital = appointment.getHospitalId();
        appointment.setDoctor(dRepo.findById(doctor).orElse(null));
        appointment.setPatient(pRepo.findById(patient).orElse(null));
        appointment.setHospital(hRepo.findById(hospital).orElse(null));
        return Optional.ofNullable(appointment);
    }

    @Override
    public appointment update(appointment entity, Long id) throws Exception {
        entity.setId(id);
        return this.aRepo.save(entity);
    }

    @Override
    public void deleteById(Long aLong) throws Exception {
        aRepo.deleteById(aLong);
    }

    @Override
    public List<appointment> findByPatientId(Long patientId) throws Exception {
        return aRepo.findByPatientId(patientId);
    }

    @Override
    public List<appointment> findByHospitalId(Long hospitalId) throws Exception {
        return aRepo.findByHospitalId(hospitalId);
    }

    @Override
    public List<appointment> findByDoctorId(Long doctorId) throws Exception {
        return aRepo.findByDoctorId(doctorId);
    }
}

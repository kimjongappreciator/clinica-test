package com.clinica.crud.controllers;

import com.clinica.crud.entities.appointment;
import com.clinica.crud.services.appointmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/appointment")
@CrossOrigin
public class appointmentController{

    @Autowired
    private appointmentService aService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<appointment>> getAll() throws Exception {
        List<appointment> hospitals = aService.findAll();
        if ( null == hospitals ) {
            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(hospitals);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<appointment> fetchById(@PathVariable("id") Long id) {
        try {
            Optional<appointment> optionalHotel = aService.findById(id);
            if(optionalHotel.isPresent()) {
                return new ResponseEntity<appointment>(optionalHotel.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "doctor/{var}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <List<appointment>> findBydoc(@PathVariable("var") Long aLong) throws Exception {

        List<appointment> doctors = aService.findByDoctorId(aLong);
        if ( null == doctors ) {
            log.error("appointments with {} not found.", aLong);
            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(doctors);
    }
    @GetMapping(path = "patient/{var}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <List<appointment>> findBypatient(@PathVariable("var") Long aLong) throws Exception {

        List<appointment> doctors = aService.findByPatientId(aLong);
        if ( null == doctors ) {
            log.error("doctors with {} not found.", aLong);
            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(doctors);
    }

    @GetMapping(path = "hospital/{var}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <List<appointment>> findByHospital(@PathVariable("var") Long aLong) throws Exception {

        List<appointment> doctors = aService.findByHospitalId(aLong);
        if ( null == doctors ) {
            log.error("doctors with {} not found.", aLong);
            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(doctors);
    }

    @PostMapping
    public ResponseEntity<appointment> save(@Valid @RequestBody appointment hospitalTemp, BindingResult result) {

        try {
            appointment hospitalCreate = aService.save(hospitalTemp);
            return ResponseEntity.status(HttpStatus.CREATED).body(hospitalCreate);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<appointment> update(@PathVariable("id") Long id, @RequestBody appointment hospitalTemp) {

        try {
            Optional<appointment> optionalHospital = aService.findById(id);
            if (optionalHospital.isPresent()) {
                appointment hospitalCreate = aService.update(hospitalTemp, id);
                return ResponseEntity.status(HttpStatus.CREATED).body(hospitalCreate);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<appointment> deleteById(@PathVariable("id") Long id) {

        try {
            Optional<appointment> optionalHospital = aService.findById(id);
            if (optionalHospital.isPresent()) {
                aService.deleteById(id);
                return new ResponseEntity<appointment>(HttpStatus.OK);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

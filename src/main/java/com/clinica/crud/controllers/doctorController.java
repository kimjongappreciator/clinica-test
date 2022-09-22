package com.clinica.crud.controllers;

import com.clinica.crud.entities.doctor;
import com.clinica.crud.services.doctorService;
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
@RequestMapping("/doctor")
@Slf4j
@CrossOrigin
public class doctorController {

    @Autowired
    private doctorService dService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<doctor>> getAll() throws Exception {
        List<doctor> hospitals = dService.findAll();
        if ( null == hospitals ) {
            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(hospitals);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<doctor> fetchById(@PathVariable("id") Long id) {
        try {
            Optional<doctor> optionalHotel = dService.findById(id);
            if(optionalHotel.isPresent()) {
                return new ResponseEntity<doctor>(optionalHotel.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "speciality/{var}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <List<doctor>> findByspeciality(@PathVariable("var") String speciality) throws Exception {

        List<doctor> doctors = dService.findBySpeciality(speciality);
        if ( null == doctors ) {
            log.error("doctors with {} not found.", speciality);
            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(doctors);
    }

    @PostMapping
    public ResponseEntity<doctor> save(@Valid @RequestBody doctor hospitalTemp, BindingResult result) {

        try {
            doctor hospitalCreate = dService.save(hospitalTemp);
            return ResponseEntity.status(HttpStatus.CREATED).body(hospitalCreate);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<doctor> update(@PathVariable("id") Long id, @RequestBody doctor hospitalTemp) {

        try {
            Optional<doctor> optionalHospital = dService.findById(id);
            if (optionalHospital.isPresent()) {
                doctor hospitalCreate = dService.update(hospitalTemp, id);
                return ResponseEntity.status(HttpStatus.CREATED).body(hospitalCreate);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<doctor> deleteById(@PathVariable("id") Long id) {

        try {
            Optional<doctor> optionalHospital = dService.findById(id);
            if (optionalHospital.isPresent()) {
                dService.deleteById(id);
                return new ResponseEntity<doctor>(HttpStatus.OK);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

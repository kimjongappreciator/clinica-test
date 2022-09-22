package com.clinica.crud.controllers;

import com.clinica.crud.entities.patient;
import com.clinica.crud.services.patientService;
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
@RequestMapping("/patient")
public class patientController {

    @Autowired
    private patientService pService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<patient>> getAll() throws Exception {
        List<patient> hospitals = pService.findAll();
        if ( null == hospitals ) {
            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(hospitals);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<patient> fetchById(@PathVariable("id") Long id) {
        try {
            Optional<patient> optionalHotel = pService.findById(id);
            if(optionalHotel.isPresent()) {
                return new ResponseEntity<patient>(optionalHotel.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<patient> save(@Valid @RequestBody patient hospitalTemp, BindingResult result) {

        try {
            patient hospitalCreate = pService.save(hospitalTemp);
            return ResponseEntity.status(HttpStatus.CREATED).body(hospitalCreate);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<patient> update(@PathVariable("id") Long id, @RequestBody patient hospitalTemp) {

        try {
            Optional<patient> optionalHospital = pService.findById(id);
            if (optionalHospital.isPresent()) {
                patient hospitalCreate = pService.update(hospitalTemp, id);
                return ResponseEntity.status(HttpStatus.CREATED).body(hospitalCreate);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<patient> deleteById(@PathVariable("id") Long id) {

        try {
            Optional<patient> optionalHospital = pService.findById(id);
            if (optionalHospital.isPresent()) {
                pService.deleteById(id);
                return new ResponseEntity<patient>(HttpStatus.OK);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

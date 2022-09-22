package com.clinica.crud.controllers;

import com.clinica.crud.entities.hospital;
import com.clinica.crud.services.hospitalService;
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
@RequestMapping("/hospital")
public class hospitalController {
    @Autowired
    private hospitalService hservice;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<hospital>> getAll() throws Exception {
        List<hospital> hospitals = hservice.findAll();
        if ( null == hospitals ) {
            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(hospitals);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<hospital> fetchById(@PathVariable("id") Long id) {
        try {
            Optional<hospital> optionalHotel = hservice.findById(id);
            if(optionalHotel.isPresent()) {
                return new ResponseEntity<hospital>(optionalHotel.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<hospital> save(@Valid @RequestBody hospital hospitalTemp, BindingResult result) {

        try {
            hospital hospitalCreate = hservice.save(hospitalTemp);
            return ResponseEntity.status(HttpStatus.CREATED).body(hospitalCreate);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<hospital> update(@PathVariable("id") Long id, @RequestBody hospital hospitalTemp) {

        try {
            Optional<hospital> optionalHospital = hservice.findById(id);
            if (optionalHospital.isPresent()) {
                hospital hospitalCreate = hservice.update(hospitalTemp, id);
                return ResponseEntity.status(HttpStatus.CREATED).body(hospitalCreate);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<hospital> deleteById(@PathVariable("id") Long id) {

        try {
            Optional<hospital> optionalHospital = hservice.findById(id);
            if (optionalHospital.isPresent()) {
                hservice.deleteById(id);
                return new ResponseEntity<hospital>(HttpStatus.OK);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

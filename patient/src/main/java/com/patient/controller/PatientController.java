package com.patient.controller;

import com.patient.model.Patient;
import com.patient.service.IPatientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class PatientController {
    private IPatientService patientService;
    private static final Logger logger = LogManager.getLogger("PatientController");

    public PatientController(IPatientService patientService) {
        this.patientService = patientService;
    }

    @RequestMapping(value = {"/api/patient/all"}, method = RequestMethod.GET)
    public List<Patient> getAllPatient() {
        logger.info("GET request to /api/patient/all");
        return StreamSupport.stream(patientService.getPatients().spliterator(), false).collect(Collectors.toList());
    }

    @RequestMapping(value = {"/api/patient"}, method = RequestMethod.GET, params = {"id"})
    public Patient getPatientById(@RequestParam(value = "id") int id) {
        logger.info("GET request to /api/patient?id=%s", id);
        return patientService.getPatientById(id).get();


    }
    @RequestMapping(value = {"/api/patient"}, method = RequestMethod.GET, params = {"lastName"})
    public Patient getPatientByLastName(@RequestParam(value = "lastName") String lastName) {
        logger.info("GET request to /api/patient?lastName=%s", lastName);
        return patientService.getPatientByLastName(lastName).get();
    }

    @RequestMapping(value = {"/api/patient"}, method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {
        logger.info("POST request to /api/patient");

        try {
            Patient createdPatient = patientService.savePatient(patient);
            return ResponseEntity.created(URI.create("/api/patient"))
                    .body(createdPatient);
        } catch (Exception e) {
            logger.error("Error while creating new patient : %s", e.toString());
            return ResponseEntity.badRequest().body(patient);
        }
    }

    @RequestMapping(value = {"/api/patient"}, method = RequestMethod.PUT,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Patient> editPatient(@RequestBody Patient patient) {
        logger.info("PUT request to /api/patient");

        try {
            Patient editedPatient = patientService.savePatient(patient);
            return ResponseEntity.created(URI.create("/api/patient"))
                    .body(editedPatient);
        } catch (Exception e) {
            logger.error("Error while modifying patient : %s", e.toString());
            return ResponseEntity.badRequest().body(patient);
        }
    }

    // Virer le try/catch
    @RequestMapping(value = {"/api/patient"}, method = RequestMethod.DELETE,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Patient> deletePatientById(@RequestBody Patient patient) {
        logger.info("DELETE request to /api/patient");

        try {
            patientService.deletePatientById(patient.getPatientId());
            return ResponseEntity.created(URI.create("/api/patient"))
                    .body(patient);
        } catch (Exception e) {
            logger.error("Error while deleting patient : %s", e.toString());
            return ResponseEntity.badRequest().body(patient);
        }
    }
}

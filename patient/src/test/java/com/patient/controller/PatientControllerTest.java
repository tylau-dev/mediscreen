package com.patient.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.patient.model.Patient;
import com.patient.service.IPatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

class PatientControllerTest {
    private PatientController patientController;

    @Mock
    private IPatientService patientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        patientController = new PatientController(patientService);
    }

    @Test
    void getAllPatient_Endpoint_Should_Return_Patients() {
        // arrange
        List<Patient> testPatients = new ArrayList<>();
        testPatients.add(new Patient());
        testPatients.add(new Patient());

        when(patientService.getPatients()).thenReturn(testPatients);

        // act
        List<Patient> result = patientController.getAllPatient();

        // assess
        verify(patientService).getPatients();
        assertEquals(testPatients.size(), result.size());
    }

    @Test
    void getPatientById_Endpoint_Should_Return_Patient() {
        // arrange
        int patientId = 1;
        Patient testPatient = new Patient();

        when(patientService.getPatientById(patientId)).thenReturn(Optional.of(testPatient));

        // act
        Patient result = patientController.getPatientById(patientId);

        // assess
        verify(patientService).getPatientById(patientId);
        assertEquals(testPatient, result);
    }

    @Test
    void getPatientByLastName_Endpoint_Should_Return_Patient() {
        String lastname = "Smith";
        Patient testPatient = new Patient();

        // arrange
        when(patientService.getPatientByLastName(lastname)).thenReturn(testPatient);

        // act
        Patient result = patientController.getPatientByLastName(lastname);

        // assess
        verify(patientService).getPatientByLastName(lastname);
        assertEquals(testPatient, result);
    }

    @Test
    void addPatient_Endpoint_Should_SavePatient_And_ReturnBody() {
        // arrange
        Patient testPatient = new Patient();

        when(patientService.savePatient(testPatient)).thenReturn(testPatient);

        // act
        ResponseEntity<Patient> result = patientController.addPatient(testPatient);

        // assess
        verify(patientService).savePatient(testPatient);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(URI.create("/api/patient"), result.getHeaders().getLocation());
        assertEquals(testPatient, result.getBody());
    }

    @Test
    void editPatient_Endpoint_Should_SavePatient_And_ReturnBody() {
        // arrange
        Patient testPatient = new Patient();

        when(patientService.savePatient(testPatient)).thenReturn(testPatient);

        // act
        ResponseEntity<Patient> result = patientController.editPatient(testPatient);

        // assess
        verify(patientService).savePatient(testPatient);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(URI.create("/api/patient"), result.getHeaders().getLocation());
        assertEquals(testPatient, result.getBody());
    }

    @Test
    void deletePatient_Endpoint_Should_DeletePatient_And_ReturnBody() {
        // arrange
        Patient testPatient = new Patient();
        testPatient.setPatientId(1);

        // act
        ResponseEntity<Patient> result = patientController.deletePatientById(testPatient);

        // assess
        verify(patientService).deletePatientById(testPatient.getPatientId());
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(URI.create("/api/patient"), result.getHeaders().getLocation());
        assertEquals(testPatient, result.getBody());

    }
}


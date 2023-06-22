package com.front.service;

import com.front.client.IPatientClient;
import com.front.model.Patient;
import com.front.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PatientServiceTest {
    private PatientService patientService;

    @Mock
    private IPatientClient patientClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        patientService = new PatientService(patientClient);
    }

    @Test
    void retrieveAllPatient() {
        // Arrange
        List<Patient> expectedPatients = new ArrayList<>();
        when(patientClient.getAllPatient()).thenReturn(expectedPatients);

        // Act
        List<Patient> actualPatients = patientService.retrieveAllPatient();

        // Assert
        assertEquals(expectedPatients, actualPatients);
        verify(patientClient, times(1)).getAllPatient();
    }

    @Test
    void retrievePatientById() {
        // Arrange
        int id = 1;
        Patient expectedPatient = new Patient();
        when(patientClient.getPatientById(id)).thenReturn(expectedPatient);

        // Act
        Patient actualPatient = patientService.retrievePatientById(id);

        // Assert
        assertEquals(expectedPatient, actualPatient);
        verify(patientClient, times(1)).getPatientById(id);
    }

    @Test
    void savePatient() {
        // Arrange
        Patient patient = new Patient();

        // Act
        patientService.savePatient(patient);

        // Assert
        verify(patientClient, times(1)).addPatient(patient);
    }

    @Test
    void updatePatient() {
        // Arrange
        Patient patient = new Patient();

        // Act
        patientService.updatePatient(patient);

        // Assert
        verify(patientClient, times(1)).updatePatient(patient);
    }

    @Test
    void deletePatient() {
        // Arrange
        Patient patient = new Patient();

        // Act
        patientService.deletePatient(patient);

        // Assert
        verify(patientClient, times(1)).deletePatient(patient);
    }
}

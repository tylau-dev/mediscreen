package com.alert.controller;

import com.alert.model.Alert;
import com.alert.model.body.AlertBodyFamilyName;
import com.alert.model.body.AlertBodyId;
import com.alert.service.IAlertService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AlertControllerTest {
    @Mock
    private IAlertService alertService;

    private AlertController alertController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        alertController = new AlertController(alertService);
    }

    @Test
    public void testGetAssessmentById_Success() {
        // arrange
        int patientId = 1;
        AlertBodyId alertBodyId = new AlertBodyId(patientId);
        Alert assessment = new Alert();

        when(alertService.generateAlertById(patientId)).thenReturn(assessment);

        // act
        ResponseEntity<Alert> response = alertController.getAssessmentById(alertBodyId);

        // assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("/api/assess/id", response.getHeaders().getLocation().getPath());
        assertEquals(assessment, response.getBody());
    }

    @Test
    public void testGetAssessmentById_Exception() {
        // arrange
        int patientId = 1;
        AlertBodyId alertBodyId = new AlertBodyId(patientId);

        when(alertService.generateAlertById(patientId)).thenThrow(new RuntimeException());

        // act
        ResponseEntity<Alert> response = alertController.getAssessmentById(alertBodyId);

        // assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testGetAssessmentByFamilyName_Success() {
        // arrange
        String familyName = "Doe";
        AlertBodyFamilyName alertBodyFamilyName = new AlertBodyFamilyName(familyName);
        Alert assessment = new Alert();

        when(alertService.generateAlertByFamilyName(familyName)).thenReturn(assessment);

        // act
        ResponseEntity<Alert> response = alertController.getAssessmentByFamilyName(alertBodyFamilyName);

        // assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("/api/Alert", response.getHeaders().getLocation().getPath());
        assertEquals(assessment, response.getBody());
    }

    @Test
    public void testGetAssessmentByFamilyName_Exception() {
        // arrange
        String familyName = "Doe";
        AlertBodyFamilyName alertBodyFamilyName = new AlertBodyFamilyName(familyName);

        when(alertService.generateAlertByFamilyName(familyName)).thenThrow(new RuntimeException());

        // act
        ResponseEntity<Alert> response = alertController.getAssessmentByFamilyName(alertBodyFamilyName);

        // assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void getPatientById_Endpoint_Should_Return_Patient() {
        Alert assessment = new Alert();

        // arrange
        when(alertService.generateAlertById(1)).thenReturn(assessment);

        // act
        Alert response = alertController.getAlertById(1);

        // assess
        assertEquals(assessment, response);
    }

    @Test
    void getPatientByLastName_Endpoint_Should_Return_Patient() {
        String lastname = "Smith";
        Alert assessment = new Alert();

        // arrange
        when(alertService.generateAlertByFamilyName(lastname)).thenReturn(assessment);

        // act
        Alert response = alertController.getAlertByLastName(lastname);

        // assess
        assertEquals(assessment, response);
    }

}

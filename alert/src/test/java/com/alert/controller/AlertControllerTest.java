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
        int patientId = 1;
        AlertBodyId alertBodyId = new AlertBodyId(patientId);
        Alert assessment = new Alert();

        when(alertService.generateAlertById(patientId)).thenReturn(assessment);

        ResponseEntity<Alert> response = alertController.getAssessmentById(alertBodyId);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("/api/Alert", response.getHeaders().getLocation().getPath());
        assertEquals(assessment, response.getBody());

        verify(alertService).generateAlertById(patientId);
    }

    @Test
    public void testGetAssessmentById_Exception() {
        int patientId = 1;
        AlertBodyId alertBodyId = new AlertBodyId(patientId);

        when(alertService.generateAlertById(patientId)).thenThrow(new RuntimeException());

        ResponseEntity<Alert> response = alertController.getAssessmentById(alertBodyId);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(new Alert(), response.getBody());

        verify(alertService).generateAlertById(patientId);
    }

    @Test
    public void testGetAssessmentByFamilyName_Success() {
        String familyName = "Doe";
        AlertBodyFamilyName alertBodyFamilyName = new AlertBodyFamilyName(familyName);
        Alert assessment = new Alert();

        when(alertService.generateAlertByFamilyName(familyName)).thenReturn(assessment);

        ResponseEntity<Alert> response = alertController.getAssessmentByFamilyName(alertBodyFamilyName);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("/api/Alert", response.getHeaders().getLocation().getPath());
        assertEquals(assessment, response.getBody());

        verify(alertService).generateAlertByFamilyName(familyName);
    }

    @Test
    public void testGetAssessmentByFamilyName_Exception() {
        String familyName = "Doe";
        AlertBodyFamilyName alertBodyFamilyName = new AlertBodyFamilyName(familyName);

        when(alertService.generateAlertByFamilyName(familyName)).thenThrow(new RuntimeException());

        ResponseEntity<Alert> response = alertController.getAssessmentByFamilyName(alertBodyFamilyName);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(new Alert(), response.getBody());

        verify(alertService).generateAlertByFamilyName(familyName);
    }

}

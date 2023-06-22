package com.front.service;

import com.front.client.IAlertClient;
import com.front.model.Alert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AlertServiceTest {
    @Mock
    private IAlertClient alertClient;

    private AlertService alertService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        alertService = new AlertService(alertClient);
    }

    @Test
    void retrieveAssessmentByPatientId_ShouldReturnAssessment() {
        // Arrange
        int patientId = 1;

        Alert alert = new Alert("John Doe", 49, "Early Onset");
        when(alertClient.getAlertByPatientId(patientId)).thenReturn(alert);

        // Act
        String assessment = alertService.retrieveAssessmentByPatientId(patientId);

        // Assert
        assertEquals("Early Onset", assessment);
        verify(alertClient, times(1)).getAlertByPatientId(patientId);
    }
}

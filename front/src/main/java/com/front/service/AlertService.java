package com.front.service;

import com.front.client.IAlertClient;
import org.springframework.stereotype.Service;

@Service
public class AlertService implements IAlertService {
    private IAlertClient alertClient;

    public AlertService(IAlertClient alertClient) {
        this.alertClient = alertClient;
    }

    public String retrieveAssessmentByPatientId(int patientId) {
        return alertClient.getAlertByPatientId(patientId).getAssessment();
    }

}

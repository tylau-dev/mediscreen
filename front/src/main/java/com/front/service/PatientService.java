package com.front.service;

import com.front.client.IPatientClient;
import com.front.model.Patient;

import java.util.List;

public class PatientService implements IPatientService {
    private IPatientClient patientClient;

    public PatientService(IPatientClient patientClient) {
        this.patientClient = patientClient;
    }

    public List<Patient> retrieveAllPatient() {
        return patientClient.getAllPatient();
    }

}

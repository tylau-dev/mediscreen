package com.front.service;

import com.front.client.IPatientClient;
import com.front.model.Patient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService implements IPatientService {
    private IPatientClient patientClient;
    public PatientService(IPatientClient patientClient) {
        this.patientClient = patientClient;
    }
    public List<Patient> retrieveAllPatient() {
        return patientClient.getAllPatient();
    }
    public Patient retrievePatientById(int id) {
        return patientClient.getPatientById(id);
    }
    public void savePatient(Patient patient) {
        patientClient.addPatient(patient);
    }
    public void updatePatient(Patient patient) {
        patientClient.updatePatient(patient);
    }
    public void deletePatient(Patient patient) {
        patientClient.deletePatient(patient);
    }

}

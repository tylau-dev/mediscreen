package com.front.service;

import com.front.model.Patient;

import java.util.List;

public interface IPatientService {
    List<Patient> retrieveAllPatient();
    Patient retrievePatientById(int id);
    void savePatient(Patient patient);
    void updatePatient(Patient patient);
    void deletePatient(Patient patient);
}

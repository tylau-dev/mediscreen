package com.front.client;

import com.front.model.Patient;

import java.util.List;

public interface IPatientClient {
    List<Patient> getAllPatient();
    Patient getPatientById(int id);
    Patient getPatientByFamilyName(String lastname);
    void addPatient(Patient patient);
    void updatePatient(Patient patient);
    void deletePatient(Patient patient);
}

package com.front.client;

import com.front.model.Patient;

import java.util.List;

public interface IPatientClient {
    List<Patient> getAllPatient();
    public Patient getPatientById(int id);
    public Patient getPatientByFamilyName(String lastName);
}

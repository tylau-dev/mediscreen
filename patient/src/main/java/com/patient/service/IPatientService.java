package com.patient.service;

import com.patient.model.Patient;

import java.util.Optional;

public interface IPatientService {
    Iterable<Patient> getPatients();
    Optional<Patient> getPatientById(Integer id);
    Patient savePatient(Patient patient);
    void deletePatientById(Integer id);
}

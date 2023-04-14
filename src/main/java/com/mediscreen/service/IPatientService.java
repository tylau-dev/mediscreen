package com.mediscreen.service;

import com.mediscreen.model.Patient;

import java.util.Optional;

public interface IPatientService {
    Iterable<Patient> getPatients();
    Optional<Patient> getPatientById(Integer id);
    Patient savePatient(Patient patient);
    void deletePatientById(Integer id);
}

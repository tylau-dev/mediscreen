package com.patient.service;

import com.patient.model.Patient;
import com.patient.repository.PatientRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientService implements IPatientService {
    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    @Transactional
    public Iterable<Patient> getPatients() {
        return patientRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Patient> getPatientById(Integer id) {
        return patientRepository.findById(id);
    }

    @Override
    @Transactional
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    @Transactional
    public void deletePatientById(Integer id) {
        patientRepository.deleteById(id);
    }
}

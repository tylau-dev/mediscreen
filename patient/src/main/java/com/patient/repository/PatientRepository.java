package com.patient.repository;

import com.patient.model.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Integer> {
    Optional<Patient> findByLastName(String lastName);
}

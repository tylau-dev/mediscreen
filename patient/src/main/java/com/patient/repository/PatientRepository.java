package com.patient.repository;

import com.patient.model.Patient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Integer> {


    @Query(value = "SELECT * FROM patient a WHERE a.last_name = :lastname", nativeQuery = true)
    public Optional<Patient> getPatientByLastname(@Param("lastname") String lastname);

}

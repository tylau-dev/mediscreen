package com.patient.service;

import com.patient.model.Patient;
import com.patient.repository.PatientRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class PatientServiceTest {
    private PatientRepository patientRepository;
    private IPatientService patientService;


    @BeforeEach
    void setup() {
        patientRepository = Mockito.mock(PatientRepository.class);
        patientService = new PatientService(patientRepository);
    }

    @Test
    void getPatients_should_return_list_and_should_call_repository() {
        // Arrange
        Patient testPatientOne = new Patient();
        testPatientOne.setPatientId(1);

        Patient testPatientTwo = new Patient();
        testPatientOne.setPatientId(2);

        ArrayList<Patient> testPatientList = new ArrayList<>();
        testPatientList.add(testPatientOne);
        testPatientList.add(testPatientTwo);

        when(patientRepository.findAll()).thenReturn(testPatientList);

        // Act
        Iterable<Patient> result = patientService.getPatients();

        // Assess
        assertTrue(StreamSupport.stream(result.spliterator(), false).count() == 2);
        verify(patientRepository, times(1)).findAll();
    }

    @Test
    void getPatientById_should_return_correct_patient_and_should_call_repository() {
        // Arrange
        Patient testPatientOne = new Patient();
        testPatientOne.setPatientId(1);

        when(patientRepository.findById(isA(int.class))).thenReturn(Optional.of(testPatientOne));

        // Act
        Optional<Patient> result = patientService.getPatientById(1);

        // Assess
        assertTrue(result.get().getPatientId() == testPatientOne.getPatientId());
        verify(patientRepository, times(1)).findById(isA(int.class));
    }

    @Test
    void getPatientByLastName_should_return_correct_patient_and_should_call_repository() {
        // Arrange
        Patient testPatientOne = new Patient();
        testPatientOne.setLastName("John");
        testPatientOne.setPatientId(1);

        when(patientRepository.findByLastName(isA(String.class))).thenReturn(Optional.of(testPatientOne));

        // Act
        Optional<Patient> result = patientService.getPatientByLastName(testPatientOne.getLastName());

        // Assess
        assertTrue(result.get().getLastName() == testPatientOne.getLastName());
        verify(patientRepository, times(1)).findByLastName(isA(String.class));
    }

    @Test
    void savePatient_should_return_correct_patient_and_should_call_repository() {
        // Arrange
        Patient testPatientOne = new Patient();
        testPatientOne.setPatientId(1);

        when(patientRepository.save(isA(Patient.class))).thenReturn(testPatientOne);

        // Act
        Patient result = patientService.savePatient(testPatientOne);

        // Assess
        assertTrue(result.getPatientId() == testPatientOne.getPatientId());
        verify(patientRepository, times(1)).save(isA(Patient.class));
    }

    @Test
    void deletePatientById_should_call_repository_and_should_call_repository() {
        // Arrange
        doNothing().when(patientRepository).deleteById(isA(Integer.class));

        // Act
        patientService.deletePatientById(1);

        // Assess
        verify(patientRepository, times(1)).deleteById(isA(Integer.class));
    }

}

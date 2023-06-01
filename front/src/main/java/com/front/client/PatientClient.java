package com.front.client;

import com.front.configuration.EndpointProperties;
import com.front.model.Patient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Component
public class PatientClient implements  IPatientClient {
    @Autowired
    private EndpointProperties endpointProperties;
    private RestTemplate restTemplate;
    private final Gson gson;
    public PatientClient(Gson gson) {
        this.gson = gson;
        restTemplate = new RestTemplate();

    }
    public Patient getPatientById(int id) {
        ResponseEntity<String> response = restTemplate.getForEntity(endpointProperties.getPatientUri() + String.format("/api/patient?id=%s", id), String.class);
        String responseBody = response.getBody();

        return gson.fromJson(responseBody, Patient.class);
    }

    public List<Patient> getAllPatient() {
        ResponseEntity<String> response = restTemplate.getForEntity(endpointProperties.getPatientUri() + String.format("/api/patient/all"), String.class);
        String responseBody = response.getBody();
        Type patientListType = new TypeToken<List<Patient>>() {}.getType();

        ArrayList<Patient> deserializedResponse = gson.fromJson(responseBody, patientListType);
        return deserializedResponse;
    }

    public Patient getPatientByFamilyName(String lastName) {
        ResponseEntity<String> response = restTemplate.getForEntity(endpointProperties.getPatientUri() + String.format("/api/patient?lastName=%s", lastName), String.class);
        String responseBody = response.getBody();

        return gson.fromJson(responseBody, Patient.class);
    }





}
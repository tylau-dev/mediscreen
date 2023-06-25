package com.front.client;

import com.front.configuration.EndpointProperties;
import com.front.model.Patient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
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

    public Patient getPatientByFamilyName(String lastname) {
        ResponseEntity<String> response = restTemplate.getForEntity(endpointProperties.getPatientUri() + String.format("/api/patient?lastname=%s", lastname), String.class);
        String responseBody = response.getBody();

        return gson.fromJson(responseBody, Patient.class);
    }

    public void addPatient(Patient patient) {
        HttpHeaders headers = createJsonHeader();
        HttpEntity<Patient> request = new HttpEntity<Patient>(patient, headers);
        String response = restTemplate.postForObject(endpointProperties.getPatientUri() + "/api/patient", request, String.class);
    }

    public void updatePatient(Patient patient) {
        HttpHeaders headers = createJsonHeader();
        HttpEntity<Patient> request = new HttpEntity<Patient>(patient, headers);

        restTemplate.exchange(endpointProperties.getPatientUri() + "/api/patient", HttpMethod.PUT, request, Void.class);
    }
    public void deletePatient(Patient patient) {
        HttpHeaders headers = createJsonHeader();
        HttpEntity<Patient> request = new HttpEntity<Patient>(patient, headers);

        restTemplate.exchange(endpointProperties.getPatientUri() + "/api/patient", HttpMethod.DELETE, request, Void.class);
    }


    public HttpHeaders createJsonHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return headers;
    }



}
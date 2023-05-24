package com.alert.client;

import com.alert.configuration.EndpointProperties;
import com.alert.model.Patient;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class PatientClient {
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

    public Patient getPatientByFamilyName(String lastName) {
        ResponseEntity<String> response = restTemplate.getForEntity(endpointProperties.getPatientUri() + String.format("/api/patient?lastName=%s", lastName), String.class);
        String responseBody = response.getBody();

        return gson.fromJson(responseBody, Patient.class);
    }





}
package com.alert.client;

import com.alert.configuration.EndpointProperties;
import com.alert.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class PatientClient {
    @Autowired
    private EndpointProperties endpointProperties;

    private HttpClient client;

    public PatientClient() {
        client = HttpClient.newHttpClient();
    }

    public Patient getPatientById(int id) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpointProperties.getPatientUri() + String.format("/api/patient?id=%s")))
                .build();

        HttpResponse<String> response = client.send((request, HttpResponse.BodyHandlers.ofString());

        return objectMapper.readValue(response.body(), Patient.class);

    }





}
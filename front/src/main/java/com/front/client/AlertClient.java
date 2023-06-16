package com.front.client;

import com.front.configuration.EndpointProperties;
import com.front.model.Alert;
import com.front.model.Note;
import com.front.model.body.AlertBodyId;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
@Component
public class AlertClient implements IAlertClient {
    @Autowired
    private EndpointProperties endpointProperties;
    private RestTemplate restTemplate;
    private final Gson gson;
    public AlertClient(Gson gson) {
        this.gson = gson;
        restTemplate = new RestTemplate();
    }

    public Alert getAlertByPatientId(int patientId) {
        HttpHeaders headers = createHeaders();

        AlertBodyId alertBodyId = new AlertBodyId();
        alertBodyId.setPatId(patientId);

        String alertBodyString = gson.toJson(alertBodyId);

        HttpEntity<String> request = new HttpEntity<String>(alertBodyString, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(endpointProperties.getAlertUri() + "api/assess/id", request, String.class);
        String responseBody = response.getBody();

        Alert deserializedResponse = gson.fromJson(responseBody, Alert.class);
        return deserializedResponse;
    }

    public HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return headers;
    }

}

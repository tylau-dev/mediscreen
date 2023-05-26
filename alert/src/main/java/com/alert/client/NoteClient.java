package com.alert.client;

import com.alert.configuration.EndpointProperties;
import com.alert.model.Note;
import com.alert.model.Patient;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NoteClient implements INoteClient {
    @Autowired
    private EndpointProperties endpointProperties;
    private RestTemplate restTemplate;
    private final Gson gson;
    public NoteClient(Gson gson) {
        this.gson = gson;
        restTemplate = new RestTemplate();

    }
    public Note getNoteByPatientId(int patientId) {
        ResponseEntity<String> response = restTemplate.getForEntity(endpointProperties.getNoteUri() + String.format("/api/note?id=%s", patientId), String.class);
        String responseBody = response.getBody();

        return gson.fromJson(responseBody, Note.class);
    }
}
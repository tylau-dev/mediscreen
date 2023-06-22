package com.alert.client;

import com.alert.configuration.EndpointProperties;
import com.alert.model.Note;
import com.alert.model.Patient;
import com.alert.model.response.NoteListResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public List<Note> getNoteByPatientId(int patientId) {
        ResponseEntity<String> response = restTemplate.getForEntity(endpointProperties.getNoteUri() + String.format("/api/note?patientId=%s", patientId), String.class);
        String responseBody = response.getBody();
        Type noteListType = new TypeToken<List<Note>>() {}.getType();

        ArrayList<Note> deserializedResponse = gson.fromJson(responseBody, noteListType);
        return deserializedResponse;
    }
}
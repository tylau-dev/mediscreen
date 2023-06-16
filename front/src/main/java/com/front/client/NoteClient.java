package com.front.client;

import com.front.configuration.EndpointProperties;
import com.front.model.Note;
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
        ResponseEntity<String> response = restTemplate.getForEntity(endpointProperties.getNoteUri() + String.format("/api/note/all?patientId=%s", patientId), String.class);
        String responseBody = response.getBody();
        Type noteListType = new TypeToken<List<Note>>() {}.getType();

        ArrayList<Note> deserializedResponse = gson.fromJson(responseBody, noteListType);
        return deserializedResponse;
    }

    public void addNote(Note note) {
        HttpHeaders headers = createJsonHeader();
        HttpEntity<Note> request = new HttpEntity<Note>(note, headers);
        String response = restTemplate.postForObject(endpointProperties.getPatientUri() + "/api/note", request, String.class);
    }

    public void updateNote(Note note) {
        HttpHeaders headers = createJsonHeader();
        HttpEntity<Note> request = new HttpEntity<Note>(note, headers);

        restTemplate.exchange(endpointProperties.getPatientUri() + "/api/note", HttpMethod.PUT, request, Void.class);
    }
    public void deleteNote(Note note) {
        HttpHeaders headers = createJsonHeader();
        HttpEntity<Note> request = new HttpEntity<Note>(note, headers);

        restTemplate.exchange(endpointProperties.getPatientUri() + "/api/note", HttpMethod.DELETE, request, Void.class);
    }


    public HttpHeaders createJsonHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return headers;
    }

}
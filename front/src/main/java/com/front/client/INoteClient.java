package com.front.client;

import com.front.model.Note;

import java.util.List;

public interface INoteClient {
    public List<Note> getNoteByPatientId(int patientId);

}

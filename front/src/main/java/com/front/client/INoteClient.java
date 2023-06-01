package com.front.client;

import com.alert.model.Note;

import java.util.List;

public interface INoteClient {
    public List<Note> getNoteByPatientId(int patientId);

}

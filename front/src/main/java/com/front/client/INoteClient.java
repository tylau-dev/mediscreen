package com.front.client;

import com.front.model.Note;

import java.util.List;

public interface INoteClient {
    public List<Note> getNoteByPatientId(int patientId);
    void addNote(Note note);
    void updateNote(Note note);
    void deleteNote(String id);
}

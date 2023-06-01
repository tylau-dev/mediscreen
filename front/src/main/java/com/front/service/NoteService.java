package com.front.service;

import com.front.client.INoteClient;
import com.front.client.IPatientClient;
import com.front.model.Note;

import java.util.List;

public class NoteService implements  INoteService {
    private INoteClient noteClient;

    public NoteService(INoteClient noteClient) {
        this.noteClient = noteClient;
    }

    public List<Note> retrieveNoteByPatientId(int patientId) {
        return noteClient.getNoteByPatientId(patientId);
    }

}

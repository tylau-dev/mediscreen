package com.front.service;

import com.front.client.INoteClient;
import com.front.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService implements INoteService {
    private INoteClient noteClient;

    public NoteService(INoteClient noteClient) {
        this.noteClient = noteClient;
    }

    public List<Note> retrieveNoteByPatientId(int patientId) {
        return noteClient.getNoteByPatientId(patientId);
    }

    public void saveNote(Note note) {
        noteClient.addNote(note);
    }
    public void updateNote(Note note) {
        noteClient.updateNote(note);
    }
    public void deleteNote(String id) {
        noteClient.deleteNote(id);
    }

}

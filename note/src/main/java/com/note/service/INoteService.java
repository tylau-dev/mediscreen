package com.note.service;

import com.note.model.Note;

import java.util.List;
import java.util.Optional;

public interface INoteService {
    Optional<Note> getNotesById(String id);
    List<Note> getAllNotesByPatientId(int patientId);
    void saveNote(Note note);
    void deleteNoteById(String id);

}

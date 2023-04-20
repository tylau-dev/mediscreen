package com.note.service;

import com.note.model.Note;
import com.note.repository.NoteRepository;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService implements INoteService {
    private NoteRepository noteRepository;
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Optional<Note> getNotesById(String id) {
        return noteRepository.findById(id);
    }

    public List<Note> getAllNotesByPatientId(int patientId) {
        return noteRepository.findByPatientId(patientId);
    }

    public void saveNote(Note note) {
        noteRepository.save(note);
    }

    public void deleteNoteById(String id) {
        noteRepository.deleteById(id);
    }
}

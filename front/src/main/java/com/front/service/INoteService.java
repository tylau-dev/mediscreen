package com.front.service;

import com.front.model.Note;

import java.util.List;

public interface INoteService {
    List<Note> retrieveNoteByPatientId(int patientId);
}

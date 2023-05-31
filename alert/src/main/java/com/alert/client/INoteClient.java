package com.alert.client;

import com.alert.model.Note;
import com.alert.model.Patient;
import com.alert.model.response.NoteListResponse;

import java.util.List;

public interface INoteClient {
    public List<Note> getNoteByPatientId(int patientId);

}

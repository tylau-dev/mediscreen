package com.alert.client;

import com.alert.model.Note;
import com.alert.model.Patient;

public interface INoteClient {
    public Note getNoteByPatientId(int patientId);

}

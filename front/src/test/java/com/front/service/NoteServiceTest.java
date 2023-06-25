package com.front.service;

import com.front.client.INoteClient;
import com.front.model.Note;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class NoteServiceTest {
    @Mock
    private INoteClient noteClient;

    private NoteService noteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        noteService = new NoteService(noteClient);
    }

    @Test
    void retrieveNoteByPatientId_ShouldReturnListOfNotes() {
        // Arrange
        int patientId = 1;
        List<Note> expectedNotes = new ArrayList<>();
        expectedNotes.add(new Note("1", patientId, "Note 1", new Date()));
        expectedNotes.add(new Note("2", patientId, "Note 2", new Date()));
        when(noteClient.getNoteByPatientId(patientId)).thenReturn(expectedNotes);

        // Act
        List<Note> actualNotes = noteService.retrieveNoteByPatientId(patientId);

        // Assess
        assertEquals(expectedNotes, actualNotes);
        verify(noteClient, times(1)).getNoteByPatientId(patientId);
    }

    @Test
    void saveNote_ShouldCallAddNote() {
        // Arrange
        Note note = new Note("1", 1, "Note 1", new Date());

        // Act
        noteService.saveNote(note);

        // Assess
        verify(noteClient, times(1)).addNote(note);
    }

    @Test
    void updateNote_ShouldCallUpdateNote() {
        // Arrange
        Note note = new Note("1", 1, "Note 1", new Date());

        // Act
        noteService.updateNote(note);

        // Assess
        verify(noteClient, times(1)).updateNote(note);
    }

    @Test
    void deleteNote_ShouldCallDeleteNote() {
        // Arrange
        String noteId = "1";

        // Act
        noteService.deleteNote(noteId);

        // Assess
        verify(noteClient, times(1)).deleteNote(noteId);
    }
}

package com.note.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import com.note.model.Note;
import com.note.service.INoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

class NoteControllerTest {
    private NoteController noteController;

    @Mock
    private INoteService noteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        noteController = new NoteController(noteService);
    }

    @Test
    void testGetAllNoteByPatientId() {
        // Mock data
        int patientId = 1;
        List<Note> mockNotes = new ArrayList<>();
        mockNotes.add(new Note());
        mockNotes.add(new Note());

        // Mock the noteService behavior
        when(noteService.getAllNotesByPatientId(patientId)).thenReturn(mockNotes);

        // Perform the test
        List<Note> result = noteController.getAllNoteByPatientId(patientId);

        // Verify the interactions and assertions
        verify(noteService).getAllNotesByPatientId(patientId);
        assertEquals(mockNotes.size(), result.size());
    }

    @Test
    void testAddNote() {
        // Mock data
        Note mockNote = new Note();

        // Perform the test
        ResponseEntity<Note> result = noteController.addNote(mockNote);

        // Verify the interactions and assertions
        verify(noteService).saveNote(mockNote);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(URI.create("/api/note"), result.getHeaders().getLocation());
        assertEquals(mockNote, result.getBody());
    }

    @Test
    void testEditNote() {
        // Mock data
        Note mockNote = new Note();

        // Perform the test
        ResponseEntity<Note> result = noteController.editNote(mockNote);

        // Verify the interactions and assertions
        verify(noteService).saveNote(mockNote);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(URI.create("/api/note"), result.getHeaders().getLocation());
        assertEquals(mockNote, result.getBody());
    }

    @Test
    void testDeleteNoteById() {
        // Mock data
        String noteId = "123";

        // Perform the test
        ResponseEntity<String> result = noteController.deleteNoteById(noteId);

        // Verify the interactions and assertions
        verify(noteService).deleteNoteById(noteId);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(URI.create("/api/note"), result.getHeaders().getLocation());
        assertEquals(noteId, result.getBody());
    }
}

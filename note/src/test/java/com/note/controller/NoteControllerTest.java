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
        // arrange
        int patientId = 1;
        List<Note> mockNotes = new ArrayList<>();
        mockNotes.add(new Note());
        mockNotes.add(new Note());
        when(noteService.getAllNotesByPatientId(patientId)).thenReturn(mockNotes);

        // act
        List<Note> result = noteController.getAllNoteByPatientId(patientId);

        // assess
        assertEquals(mockNotes.size(), result.size());
    }

    @Test
    void testAddNote() {
        // arrange
        Note mockNote = new Note();

        // act
        ResponseEntity<Note> result = noteController.addNote(mockNote);

        // assess
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(URI.create("/api/note"), result.getHeaders().getLocation());
        assertEquals(mockNote, result.getBody());
    }

    @Test
    void testEditNote() {
        // arrange
        Note mockNote = new Note();

        // act
        ResponseEntity<Note> result = noteController.editNote(mockNote);

        // assess
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(URI.create("/api/note"), result.getHeaders().getLocation());
        assertEquals(mockNote, result.getBody());
    }

    @Test
    void testDeleteNoteById() {
        // arrange
        String noteId = "123";

        // act
        ResponseEntity<String> result = noteController.deleteNoteById(noteId);

        // assess
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(URI.create("/api/note"), result.getHeaders().getLocation());
        assertEquals(noteId, result.getBody());
    }
}

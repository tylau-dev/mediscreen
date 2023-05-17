package com.note.service;

import com.note.model.Note;
import com.note.repository.NoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class NoteServiceTest {
    private NoteRepository noteRepository;
    private INoteService noteService;


    @BeforeEach
    void setup() {
        noteRepository = Mockito.mock(NoteRepository.class);
        noteService = new NoteService(noteRepository);
    }

    @Test
    void getNotesById_should_return_note_and_should_call_repository() {
        // Arrange
        Note testNoteOne = new Note();
        testNoteOne.setId("1");


        ArrayList<Note> testNoteList = new ArrayList<>();
        testNoteList.add(testNoteOne);

        when(noteRepository.findById(isA(String.class))).thenReturn(Optional.of(testNoteOne));

        // Act
        Optional<Note> result = noteService.getNotesById("1");

        // Assess
        assertTrue(result.get().getId() == testNoteOne.getId());
        verify(noteRepository, times(1)).findById(isA(String.class));
    }

    @Test
    void getAllNotesByPatientId_should_return_correct_List_and_should_call_repository() {
        // Arrange
        Note testNoteOne = new Note();
        testNoteOne.setId("1");
        testNoteOne.setPatientId(1);

        Note testNoteTwo = new Note();
        testNoteOne.setId("2");
        testNoteOne.setPatientId(1);

        ArrayList<Note> testNoteList = new ArrayList<Note>();
        testNoteList.add(testNoteOne);
        testNoteList.add(testNoteTwo);

        when(noteRepository.findByPatientId(isA(int.class))).thenReturn(testNoteList);

        // Act
        List<Note> result = noteService.getAllNotesByPatientId(1);

        // Assess
        assertTrue(result.stream().count() == testNoteList.stream().count());
        verify(noteRepository, times(1)).findByPatientId(isA(int.class));
    }

    @Test
    void saveNote_should_call_repository() {
        // Arrange
        Note testNoteOne = new Note();
        testNoteOne.setId("1");
        testNoteOne.setPatientId(1);

        when(noteRepository.save(isA(Note.class))).thenReturn(testNoteOne);

        // Act
        noteService.saveNote(testNoteOne);

        // Assess
        verify(noteRepository, times(1)).save(isA(Note.class));
    }

    @Test
    void deleteNoteById_should_call_repository() {
        // Arrange
        doNothing().when(noteRepository).deleteById(isA(String.class));

        // Act
        noteService.deleteNoteById("1");

        // Assess
        verify(noteRepository, times(1)).deleteById(isA(String.class));
    }
}

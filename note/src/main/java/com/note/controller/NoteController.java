package com.note.controller;

import com.note.model.Note;
import com.note.service.INoteService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class NoteController {
    private INoteService noteService;
    private static final Logger logger = LogManager.getLogger("NoteController");

    public NoteController(INoteService noteService) {
        this.noteService = noteService;
    }

    @RequestMapping(value = {"/api/note"}, method = RequestMethod.GET, params = {"patientId"})
    public List<Note> getAllNoteByPatientId(int patientId) {
        logger.info("GET request to /api/note");
        return StreamSupport.stream(noteService.getAllNotesByPatientId(patientId).spliterator(), false).collect(Collectors.toList());
    }

    @RequestMapping(value = {"/api/note"}, method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Note> addNote(@RequestBody Note note) {
        logger.info("POST request to /api/note");

        try {
            noteService.saveNote(note);
            return ResponseEntity.created(URI.create("/api/note"))
                    .body(note);
        } catch (Exception e) {
            logger.error("Error while creating new note : %s", e.toString());
            return ResponseEntity.badRequest().body(note);
        }
    }

    @RequestMapping(value = {"/api/note"}, method = RequestMethod.PUT,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Note> editNote(@RequestBody Note note) {
        logger.info("PUT request to /api/note");

        try {
            noteService.saveNote(note);
            return ResponseEntity.created(URI.create("/api/note"))
                    .body(note);
        } catch (Exception e) {
            logger.error("Error while modifying note : %s", e.toString());
            return ResponseEntity.badRequest().body(note);
        }
    }

    @RequestMapping(value = {"/api/note"}, method = RequestMethod.DELETE,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> deleteNoteById(@RequestBody String id) {
        logger.info("DELETE request to /api/note");

        try {
            noteService.deleteNoteById(id);
            return ResponseEntity.created(URI.create("/api/note"))
                    .body(id);
        } catch (Exception e) {
            logger.error("Error while deleting note : %s", e.toString());
            return ResponseEntity.badRequest().body(id);
        }
    }

}

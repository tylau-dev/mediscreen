package com.front.controller;

import com.front.model.Alert;
import com.front.model.Note;
import com.front.model.Patient;
import com.front.service.IAlertService;
import com.front.service.INoteService;
import com.front.service.IPatientService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
public class NoteFrontController {
    private static final Logger logger = LogManager.getLogger("PatientFrontController");

    @Autowired
    private IPatientService patientService;
    @Autowired
    private IAlertService alertService;
    @Autowired
    private INoteService noteService;

    @RequestMapping("/note/{patientId}")
    public String home(@PathVariable("patientId") Integer patientId, Model model) {
        logger.info("GET /note/list");

        List<Note> notes = new ArrayList<Note>();
        this.noteService.retrieveNoteByPatientId(patientId).forEach(notes::add);
        model.addAttribute("noteList", notes);

        model.addAttribute("patientId", patientId);

        String alert = this.alertService.retrieveAssessmentByPatientId(patientId);
        model.addAttribute("alert", alert);

        return "note/list";
    }

    @RequestMapping("/note/{patientId}/add")
    public String addNoteForm(@PathVariable("patientId") Integer patientId, Model model) {
        logger.info("GET /note/add");

        Note noteToAdd = new Note();
        noteToAdd.setPatientId(patientId);

        model.addAttribute("patientId", patientId);
        model.addAttribute("noteToAdd", noteToAdd);

        return "note/add";
    }

    @RequestMapping("/note/{patientId}/edit/{id}")
    public String updatePatientForm(@PathVariable("patientId") Integer patientId, @PathVariable("id") String id, Model model) {
        logger.info("GET /note/edit");

        Note noteToEdit = new Note();

        List<Note> notes = new ArrayList<Note>();
        this.noteService.retrieveNoteByPatientId(patientId).forEach(notes::add);

        for (Note note: notes) {
            String idToGet = note.getId();

            if (idToGet.equals(id)) {
                noteToEdit.setId(note.getId());
                noteToEdit.setPatientId(note.getPatientId());
                noteToEdit.setNote(note.getNote());
                noteToEdit.setDate(note.getDate());
                break;
            }
        }

        model.addAttribute("patientId", patientId);
        model.addAttribute("noteToEdit", noteToEdit);

        return "note/edit";
    }

    @PostMapping("/note/{patientId}/validate/add")
    public String validateAdd(@PathVariable("patientId") Integer patientId, @Valid Note noteToAdd, BindingResult result, Model model) {
        logger.info("POST /note/validate/add");

        if (result.hasErrors()) {
            logger.error("Error with form input");
            return "note/add";
        }

        model.addAttribute("patientId", patientId);
        noteService.saveNote(noteToAdd);
        return  String.format("redirect:/note/%d/add", patientId);
    }

    @PostMapping("/note/validate/edit")
    public String validateEdit(@Valid Note note, BindingResult result, Model model) {
        logger.info("POST /note/validate");

        if (result.hasErrors()) {
            logger.error("Error with form input");
            return "note/add";
        }

        noteService.saveNote(note);

        return "redirect:/patient/list";
    }

    @PostMapping("/note/{patientId}/edit/{id}")
    public String updateBid(@PathVariable("patientId") Integer patientId, @PathVariable("id") String id, @Valid Note note, BindingResult result, Model model) {
        logger.info("POST /note/edit");

        if (result.hasErrors()) {
            logger.error("Error with form input");
            return "patient/edit";
        }

        noteService.updateNote(note);


        return "redirect:/patient/list";
    }

    @GetMapping("/note/{patientId}/delete/{id}")
    public String deleteBid(@PathVariable("patientId") Integer patientId, @PathVariable("id") String id, Model model) {
        logger.info("GET /note/delete");

        this.noteService.deleteNote(id);

        return "redirect:/patient/list";
    }




}


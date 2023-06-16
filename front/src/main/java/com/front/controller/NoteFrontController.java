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
    private List<Note> notes;

    @RequestMapping("/note/{patientId}")
    public String home(@PathVariable("patientId") Integer patientId, Model model) {
        logger.info("GET /note/list");

        this.notes = new ArrayList<Note>();
        this.noteService.retrieveNoteByPatientId(patientId).forEach(this.notes::add);
        model.addAttribute("noteList", this.notes);

        Patient patient = this.patientService.retrievePatientById(patientId);
        model.addAttribute("patient", patient);

        String alert = this.alertService.retrieveAssessmentByPatientId(patientId);
        model.addAttribute("alert", alert);

        return "note/list";
    }

    @RequestMapping("/note/{patientId}/add")
    public String addNoteForm(@PathVariable("patientId") Integer patientId, Note note) {
        logger.info("GET /note/add");

        return "note/add";
    }

    @RequestMapping("/note/{patientId}/edit/{id}")
    public String updatePatientForm(@PathVariable("patientId") Integer patientId, @PathVariable("id") String id, Model model) {
        logger.info("GET /note/edit");
        Note noteToEdit = new Note();

        for (Note note: this.notes) {
            if (note.getId() == id) {
                noteToEdit = note;
            }
        }

        model.addAttribute("note", noteToEdit);
        return "patient/edit";
    }

    @PostMapping("/note/{patientId}/validate")
    public String validate(@PathVariable("patientId") Integer patientId, @Valid Note note, BindingResult result, Model model) {
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

        // Retrieve the Patient with updated values
        this.noteService.retrieveNoteByPatientId(note.getPatientId()).forEach(this.notes::add);
        model.addAttribute("noteList", this.notes);

        Patient patient = this.patientService.retrievePatientById(note.getPatientId());
        model.addAttribute("patient", patient);

        String alert = this.alertService.retrieveAssessmentByPatientId(note.getPatientId());
        model.addAttribute("alert", alert);

        return "redirect:/patient/list";
    }

    @GetMapping("/patient/{patientId}/delete/{id}")
    public String deleteBid(@PathVariable("patientId") Integer patientId, @PathVariable("id") String id, Model model) {
        logger.info("GET /patient/delete");

        this.noteService.deleteNote(id);

        // Retrieve the Patient with updated values
        this.noteService.retrieveNoteByPatientId(patientId).forEach(this.notes::add);
        model.addAttribute("noteList", this.notes);

        Patient patient = this.patientService.retrievePatientById(patientId);
        model.addAttribute("patient", patient);

        String alert = this.alertService.retrieveAssessmentByPatientId(patientId);
        model.addAttribute("alert", alert);


        return "redirect:/patient/list";
    }




}


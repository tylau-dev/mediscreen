package com.front.controller;

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

    @RequestMapping("/note/{id}")
    public String home(@PathVariable("id") Integer id, Model model) {
        logger.info("GET /note/list");

        List<Note> patients = new ArrayList<Note>();
        this.noteService.retrieveNoteByPatientId(id).forEach(patients::add);
        model.addAttribute("patientList", patients);

        return "patient/list";
    }

    @RequestMapping("/patient/add")
    public String addPatientForm(Patient patient) {
        logger.info("GET /patient/list");

        return "patient/add";
    }

    @RequestMapping("/patient/edit/{id}")
    public String updatePatientForm(@PathVariable("id") Integer id, Model model) {
        logger.info("GET /patient/edit");
        Patient patientToEdit = this.patientService.retrievePatientById(id);


        model.addAttribute("patient", patientToEdit);

        return "patient/edit";
    }

    @PostMapping("/patient/validate")
    public String validate(@Valid Patient patient, BindingResult result, Model model) {
        logger.info("POST /patient/validate");

        if (result.hasErrors()) {
            logger.error("Error with form input");
            return "patient/add";
        }

        patientService.savePatient(patient);

        return "redirect:/patient/list";
    }

    @PostMapping("/patient/edit/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid Patient patient, BindingResult result, Model model) {
        logger.info("POST /patient/edit");

        if (result.hasErrors()) {
            logger.error("Error with form input");
            return "patient/edit";
        }

        // Save also updates automatically with Entity Framework
        patientService.updatePatient(patient);

        // Retrieve the Patient with updated values
        List<Patient> patients = new ArrayList<Patient>();
        this.patientService.retrieveAllPatient().forEach(patients::add);
        model.addAttribute("patient", patients);

        return "redirect:/patient/list";
    }

    @GetMapping("/patient/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        logger.info("GET /patient/delete");
        Patient patientToDelete = this.patientService.retrievePatientById(id);

        this.patientService.deletePatient(patientToDelete);

        // Retrieve the Patient with updated values
        List<Patient> patients = new ArrayList<Patient>();
        this.patientService.retrieveAllPatient().forEach(patients::add);
        model.addAttribute("patient", patients);

        return "redirect:/patient/list";
    }




}


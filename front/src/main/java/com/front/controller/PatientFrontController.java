package com.front.controller;

import com.front.model.Patient;
import com.front.service.IAlertService;
import com.front.service.IPatientService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
public class PatientFrontController {
    private static final Logger logger = LogManager.getLogger("PatientFrontController");

    private IPatientService patientService;
    private IAlertService alertService;

    public PatientFrontController(IPatientService patientService, IAlertService alertService) {
        this.patientService = patientService;
        this.alertService = alertService;
    }

    @RequestMapping("/patient/list")
    public String home(Model model) {
        logger.info("GET /patient/list");

        List<Patient> patients = new ArrayList<Patient>();
        this.patientService.retrieveAllPatient().forEach(patients::add);
        model.addAttribute("patientList", patients);

        return "patient/list";
    }

//    @PostMapping("/bidList/validate")
//    public String validate(@Valid Patient patient, BindingResult result, Model model) {
//        logger.info("POST /patient/validate");
//
//        if (result.hasErrors()) {
//            logger.error("Error with form input");
//            return "bidList/add";
//        }
//
//        patientService.savePatient(patient);
//
//        return "redirect:/patient/list";
//    }
//
//    @GetMapping("/bidList/update/{id}")
//    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
//        Patient patientResult = this.patientService.retrievePatientById(id);
//
//        model.addAttribute("patient", patientResult);
//
//        return "patient/update";
//    }
//
//    @PostMapping("/bidList/update/{id}")
//    public String updateBid(@PathVariable("id") Integer id, @Valid Patient patient, BindingResult result, Model model) {
//        logger.info("POST /patient/update");
//
//        if (result.hasErrors()) {
//            logger.error("Error with form input");
//            return "patient/update";
//        }
//
//        // Save also updates automatically with Entity Framework
//        patientService.savePatient(patient);
//
//        // Retrieve the BidList with updated values
//        List<Patient> patients = new ArrayList<Patient>();
//        this.patientService.retrieveAllPatient().forEach(patients::add);
//        model.addAttribute("patient", patients);
//
//        return "redirect:/patient/list";
//    }
//
//    @GetMapping("/bidList/delete/{id}")
//    public String deleteBid(@PathVariable("id") Integer id, Model model) {
//        logger.info("POST /bidList/delete");
//
//        this.bidListService.deleteBidListById(id);
//
//        // Retrieve the BidList with updated values
//        List<BidList> bidListResult = new ArrayList<BidList>();
//        this.bidListService.getBidLists().forEach(bidListResult::add);
//        model.addAttribute("bidList", bidListResult);
//
//        return "redirect:/bidList/list";
//    }




}


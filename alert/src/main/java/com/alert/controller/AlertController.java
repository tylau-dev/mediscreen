package com.alert.controller;

import com.alert.model.Alert;
import com.alert.model.body.AlertBodyFamilyName;
import com.alert.model.body.AlertBodyId;
import com.alert.service.IAlertService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class AlertController {
    private IAlertService AlertService;
    private static final Logger logger = LogManager.getLogger("AlertController");

    public AlertController(IAlertService AlertService) {
        this.AlertService = AlertService;
    }

    @RequestMapping(value = {"/api/assess/id"}, method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Alert> getAssessmentById(@RequestBody AlertBodyId alertBodyId) {
        logger.info("POST request to /api/Alert");

        try {
            Alert assessment = AlertService.generateAlertById(alertBodyId.getPatId());;
            return ResponseEntity.created(URI.create("/api/Alert"))
                    .body(assessment);
        } catch (Exception e) {
            logger.error("Error while creating new Alert : %s", e.toString());
            return ResponseEntity.badRequest().body(new Alert());
        }
    }

    @RequestMapping(value = {"/api/assess/familyName"}, method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Alert> getAssessmentByFamilyName(@RequestBody AlertBodyFamilyName alertBodyFamilyName) {
        logger.info("POST request to /api/Alert");

        try {
            Alert assessment = AlertService.generateAlertByFamilyName(alertBodyFamilyName.getFamilyName());;
            return ResponseEntity.created(URI.create("/api/Alert"))
                    .body(assessment);
        } catch (Exception e) {
            logger.error("Error while creating new Alert : %s", e.toString());
            return ResponseEntity.badRequest().body(new Alert());
        }
    }
}


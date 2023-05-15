package com.alert.controller;

import com.alert.model.Alert;
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

//    @RequestMapping(value = {"/api/alert/all"}, method = RequestMethod.GET)
//    public List<Alert> getAllAlert() {
//        logger.info("GET request to /api/Alert/all");
//        return StreamSupport.stream(AlertService.getAlerts().spliterator(), false).collect(Collectors.toList());
//    }
//
//    @RequestMapping(value = {"/api/Alert"}, method = RequestMethod.GET, params = {"id"})
//    public Alert getAlertById(@RequestParam(value = "id") int id) {
//        logger.info("GET request to /api/Alert?id=%s", id);
//        return AlertService.getAlertById(id).get();
//    }
//
//    @RequestMapping(value = {"/api/Alert"}, method = RequestMethod.POST,
//            consumes = {MediaType.APPLICATION_JSON_VALUE},
//            produces = {MediaType.APPLICATION_JSON_VALUE})
//    public ResponseEntity<Alert> addAlert(@RequestBody Alert Alert) {
//        logger.info("POST request to /api/Alert");
//
//        try {
//            Alert createdAlert = AlertService.saveAlert(Alert);
//            return ResponseEntity.created(URI.create("/api/Alert"))
//                    .body(createdAlert);
//        } catch (Exception e) {
//            logger.error("Error while creating new Alert : %s", e.toString());
//            return ResponseEntity.badRequest().body(Alert);
//        }
//    }
//
//    @RequestMapping(value = {"/api/Alert"}, method = RequestMethod.PUT,
//            consumes = {MediaType.APPLICATION_JSON_VALUE},
//            produces = {MediaType.APPLICATION_JSON_VALUE})
//    public ResponseEntity<Alert> editAlert(@RequestBody Alert Alert) {
//        logger.info("PUT request to /api/Alert");
//
//        try {
//            Alert editedAlert = AlertService.saveAlert(Alert);
//            return ResponseEntity.created(URI.create("/api/Alert"))
//                    .body(editedAlert);
//        } catch (Exception e) {
//            logger.error("Error while modifying Alert : %s", e.toString());
//            return ResponseEntity.badRequest().body(Alert);
//        }
//    }
//
//    // Virer le try/catch
//    @RequestMapping(value = {"/api/Alert"}, method = RequestMethod.DELETE,
//            consumes = {MediaType.APPLICATION_JSON_VALUE},
//            produces = {MediaType.APPLICATION_JSON_VALUE})
//    public ResponseEntity<Alert> deleteAlertById(@RequestBody Alert Alert) {
//        logger.info("DELETE request to /api/Alert");
//
//        try {
//            AlertService.deleteAlertById(Alert.getAlertId());
//            return ResponseEntity.created(URI.create("/api/Alert"))
//                    .body(Alert);
//        } catch (Exception e) {
//            logger.error("Error while deleting Alert : %s", e.toString());
//            return ResponseEntity.badRequest().body(Alert);
//        }
//    }
}

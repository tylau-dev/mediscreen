package com.alert.service;

import com.alert.client.INoteClient;
import com.alert.client.IPatientClient;
import com.alert.client.NoteClient;
import com.alert.client.PatientClient;
import com.alert.helper.GetAge;
import com.alert.model.Alert;
import com.alert.model.Note;
import com.alert.model.Patient;
import org.springframework.stereotype.Service;

import java.text.Format;
import java.util.*;

@Service
public class AlertService implements IAlertService {
    private INoteClient noteClient;
    private IPatientClient patientClient;

    public static final List<String> triggerList = new ArrayList<String>(){{
        add("Hémoglobine A1C");
        add("Microalbumine");
        add("Taille");
        add("Poids");
        add("Fumeur");
        add("Anormal");
        add("Cholestérol");
        add("Vertige");
        add("Rechute");
        add("Réaction");
        add("Anticorps");
    }};

    public static enum RiskLevel {
        None,
        Borderline,
        Danger,
        EarlyOnset
    }

    public static Map<RiskLevel, String> RiskLabel = new HashMap<RiskLevel, String>() {{
        put(RiskLevel.None, "None");
        put(RiskLevel.Borderline, "Borderline");
        put(RiskLevel.Danger, "In danger");
        put(RiskLevel.EarlyOnset, "Early onset");
    }};

    public AlertService(INoteClient noteClient, IPatientClient patientClient) {
        this.noteClient = noteClient;
        this.patientClient = patientClient;
    }

    public Alert generateAlertById(int id) {
        Patient patient = patientClient.getPatientById(id);
        Note note = noteClient.getNoteByPatientId(patient.getPatientId());

        return assessRiskWithPatientAndNote(patient, note);
    }
    public Alert generateAlertByFamilyName(String lastName) {
        Patient patient = patientClient.getPatientByFamilyName(lastName);
        Note note = noteClient.getNoteByPatientId(patient.getPatientId());

        return assessRiskWithPatientAndNote(patient, note);
    }

    private Alert assessRiskWithPatientAndNote(Patient patient, Note note) {
        int patientAge = GetAge.calculateBasedOnBirthdate(patient.getBirthDate());

        boolean ageCheck = isAgeOverThirty(patientAge);
        int riskTrigger = countTrigger(note.getNote());
        RiskLevel riskLevel = assessRisk(ageCheck, riskTrigger, patient.getGender());

        return new Alert() {
            {
                setPatientFullName(String.format("%s %s", patient.getLastName(), patient.getLastName()));
                setPatientAge(patientAge);
                setAssessment(RiskLabel.get(riskLevel));
            }
        };
    }

    private boolean isAgeOverThirty(int age) {
        if (age >= 30 ) {
            return true;
        }
        else {
            return false;
        }
    }

    private int countTrigger(String note) {
        List<String> noteList = new ArrayList<String>(Arrays.asList(note.split(",")));
        noteList.retainAll(triggerList);

        return noteList.size();
    }

    private RiskLevel assessRisk(boolean isAgeOverThirty, int countTrigger, String gender) {
        if ((gender == "M" && !isAgeOverThirty && countTrigger >= 5)
                || (gender == "F" && !isAgeOverThirty && countTrigger >= 7)
                || (isAgeOverThirty && countTrigger >= 8))
        {
            return RiskLevel.EarlyOnset;
        } else if ((gender == "M" && !isAgeOverThirty && countTrigger == 3)
                || (gender == "F" && !isAgeOverThirty && countTrigger == 4)
                || (isAgeOverThirty && countTrigger == 6)
        ) {
            return RiskLevel.Danger;
        } else if (isAgeOverThirty && countTrigger == 2) {
            return RiskLevel.Borderline;
        } else {
            return RiskLevel.None;
        }
    }


}

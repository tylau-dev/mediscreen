package com.alert.model;

public class Alert {
    private string patientFullName;

    private int patientAge;

    private string assessment;

    public string getPatientFullName() {
        return patientFullName;
    }

    public void setPatientFullName(string patientFullName) {
        this.patientFullName = patientFullName;
    }

    public int getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(int patientAge) {
        this.patientAge = patientAge;
    }

    public string getAssessment() {
        return assessment;
    }

    public void setAssessment(string assessment) {
        this.assessment = assessment;
    }
}

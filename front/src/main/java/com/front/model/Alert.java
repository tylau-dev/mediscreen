package com.front.model;

public class Alert {
    private String patientFullName;

    private int patientAge;

    private String assessment;

    public Alert() {
    }

    public Alert(String patientFullName, int patientAge, String assessment) {
        this.patientFullName = patientFullName;
        this.patientAge = patientAge;
        this.assessment = assessment;
    }

    public String getPatientFullName() {
        return patientFullName;
    }

    public void setPatientFullName(String patientFullName) {
        this.patientFullName = patientFullName;
    }

    public int getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(int patientAge) {
        this.patientAge = patientAge;
    }

    public String getAssessment() {
        return assessment;
    }

    public void setAssessment(String assessment) {
        this.assessment = assessment;
    }
}

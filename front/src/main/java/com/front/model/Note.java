package com.front.model;

import java.util.Date;

public class Note {
    private String id;
    private int patientId;
    private String note;
    private Date date;

    public Note(String id, int patientId,String note, Date date) {
        this.id = id;
        this.patientId = patientId;
        this.note = note;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

package com.alert.model;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Patient {
    private int patientId;

    private String firstname;

    private String lastname;

    private Date birthDate;

    private String gender;

    private String address;

    private String telephone;

    public Patient(int patientId) {
        this.patientId = patientId;
    }

    public Patient() {
    }

    public Patient(int patientId, String firstname, String lastname, Date birthDate, String gender, String address, String telephone) {
        this.patientId = patientId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.telephone = telephone;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastname) {
        this.lastname = lastname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}

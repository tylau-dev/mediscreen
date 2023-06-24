package com.front.model.form;
import java.util.Date;

public class PatientForm {
    private int patientId;

    private String firstname;

    private String lastname;

    private String birthDateString;

    private String gender;

    private String address;

    private String telephone;

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

    public String getBirthDate() {
        return birthDateString;
    }

    public void setBirthDate(String birthDateString) {
        this.birthDateString = birthDateString;
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

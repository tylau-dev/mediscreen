package com.front.client;

import com.alert.model.Patient;

public interface IPatientClient {
    public Patient getPatientById(int id);
    public Patient getPatientByFamilyName(String lastName);
}

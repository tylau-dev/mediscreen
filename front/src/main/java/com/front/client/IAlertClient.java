package com.front.client;

import com.front.model.Alert;

import java.util.List;

public interface IAlertClient {
    Alert getAlertByPatientId(int patientId);
}

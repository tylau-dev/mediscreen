package com.alert.service;

import com.alert.model.Alert;

import java.util.Optional;

public interface IAlertService {
    public Alert generateAlertById(int id);
    public Alert generateAlertByFamilyName(String lastname);
}

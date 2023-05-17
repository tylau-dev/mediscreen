package com.alert.helper;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

class GetAge {
    public static int calculateBasedOnBirthdate(Date birthday) {
        return Period.between(birthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now()).getYears();
    }
}
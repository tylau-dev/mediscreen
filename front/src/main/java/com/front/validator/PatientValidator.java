package com.front.validator;

import com.front.model.form.PatientForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PatientValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return PatientForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PatientForm patientForm = (PatientForm) target;

        if (!patientForm.getGender().matches("M$|F$|O$")) {
            errors.rejectValue("gender", "Incorrect.patientForm.gender");
        }

        if (!patientForm.getGender().matches("^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$")) {
            errors.rejectValue("birthDate", "Incorrect.patientForm.birthDate");
        }
    }
}

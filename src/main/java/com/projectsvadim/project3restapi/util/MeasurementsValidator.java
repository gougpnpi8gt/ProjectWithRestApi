package com.projectsvadim.project3restapi.util;

import com.projectsvadim.project3restapi.models.Measurements;
import com.projectsvadim.project3restapi.service.ServiceMeasurements;
import com.projectsvadim.project3restapi.service.ServiceSensors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class MeasurementsValidator implements Validator {
    private final ServiceSensors serviceSensors;

    @Autowired
    public MeasurementsValidator(ServiceSensors serviceSensors) {
        this.serviceSensors = serviceSensors;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Measurements.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Measurements measurements = (Measurements) target;
        if (measurements.getSensor() == null){
            return;
        }
        if (serviceSensors.findByName(measurements.getSensor().getName()).isEmpty()){
            errors.rejectValue("Sensor", "Нет зарегистрированного сенсора с таким именем!");
        }
    }
}

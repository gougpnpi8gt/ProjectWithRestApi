package com.projectsvadim.project3restapi.util;

import com.projectsvadim.project3restapi.DTO.SensorDTO;
import com.projectsvadim.project3restapi.service.ServiceSensors;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SensorValidator implements Validator {
    final ServiceSensors serviceSensors;
    @Autowired
    public SensorValidator(ServiceSensors serviceSensors) {
        this.serviceSensors = serviceSensors;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return SensorDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorDTO sensorDTO = (SensorDTO) target;
        if (sensorDTO.getName().equals(serviceSensors.findByName(sensorDTO.getName()))){
            errors.rejectValue("Name", "", "This name sensor's is already taken");
        }
    }
}

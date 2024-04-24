package com.projectsvadim.project3restapi.controllers;

import com.projectsvadim.project3restapi.DTO.SensorDTO;
import com.projectsvadim.project3restapi.models.Sensor;
import com.projectsvadim.project3restapi.service.ServiceSensors;
import com.projectsvadim.project3restapi.util.ErrorResponse;
import com.projectsvadim.project3restapi.util.SensorNotCreatedException;
import com.projectsvadim.project3restapi.util.SensorValidator;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensors")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SensorController {

    final ServiceSensors serviceSensors;
    final SensorValidator sensorValidator;
    final ModelMapper modelMapper;
    @Autowired
    public SensorController(ServiceSensors serviceSensors,
                            SensorValidator sensorValidator,
                            ModelMapper modelMapper) {
        this.serviceSensors = serviceSensors;
        this.sensorValidator = sensorValidator;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registrationSensor(@RequestBody @Valid SensorDTO sensorDTO, // регистрация нового сенсора
                                                    BindingResult bindingResult){
        Sensor sensor = convertToSensorDTOInSensor(sensorDTO);
        sensorValidator.validate(sensor, bindingResult);
        if (bindingResult.hasErrors()){
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors){
                errorMsg
                        .append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }
            throw new SensorNotCreatedException(errorMsg.toString());
        }
        serviceSensors.save(sensor);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    private Sensor convertToSensorDTOInSensor(SensorDTO sensorDTO){
        return modelMapper.map(sensorDTO, Sensor.class);
    }


    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handlerException(SensorNotCreatedException e){
        ErrorResponse response = new ErrorResponse(
                e.getMessage(), System.currentTimeMillis()
        );
        // в Http ответе тело ответа(response) и статус в заголовке
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // NOT_FOUND - 404 статус
    }

}

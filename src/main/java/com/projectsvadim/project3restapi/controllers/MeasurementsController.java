package com.projectsvadim.project3restapi.controllers;

import com.projectsvadim.project3restapi.DTO.MeasurementsDTO;
import com.projectsvadim.project3restapi.DTO.MeasurementsResponse;
import com.projectsvadim.project3restapi.models.Measurements;
import com.projectsvadim.project3restapi.service.ServiceMeasurements;
import com.projectsvadim.project3restapi.util.ErrorResponse;
import com.projectsvadim.project3restapi.util.MeasurementsError;
import com.projectsvadim.project3restapi.util.MeasurementsValidator;
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
import java.util.stream.Collectors;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/measurements")
public class MeasurementsController {

    final ServiceMeasurements serviceMeasurements;
    final ModelMapper modelMapper;
    final MeasurementsValidator measurementsValidator;

    @Autowired
    public MeasurementsController(ServiceMeasurements serviceMeasurements,
                                  ModelMapper modelMapper,
                                  MeasurementsValidator measurementsValidator) {
        this.serviceMeasurements = serviceMeasurements;
        this.modelMapper = modelMapper;
        this.measurementsValidator = measurementsValidator;
    }

//    @GetMapping()
////    public List<MeasurementsDTO> ListMeasurements(){
////        return serviceMeasurements.findAll()
////                .stream().map(this::convertToMeasurementsInMeasurementsDTO)
////                .collect(Collectors.toList());
////    }
    @GetMapping()
    public MeasurementsResponse ListMeasurements(){
        return new MeasurementsResponse(serviceMeasurements.findAll()
                .stream().map(this::convertToMeasurementsInMeasurementsDTO)
                .collect(Collectors.toList()));
    }

//    @GetMapping("/rainyDaysCount")
//    public int listRainyDays(){
//        return serviceMeasurements.findAllByRainingIs().size();
//    }

    @GetMapping("/rainyDaysCount")
    public Long getRainyDaysCount() {
        return serviceMeasurements.findAll().stream().filter(Measurements::isRaining).count();
    }
    private MeasurementsDTO convertToMeasurementsInMeasurementsDTO(Measurements measurements){
        return modelMapper.map(measurements, MeasurementsDTO.class);
    }

    @PostMapping("/add") // добавление измерений в БД
    public ResponseEntity<HttpStatus> addMeasurementsSensor(@RequestBody @Valid MeasurementsDTO measurementsDTO,
                                                            BindingResult bindingResult){
        Measurements measurements = convertToMeasurementsDTOInMeasurements(measurementsDTO);
        measurementsValidator.validate(measurements, bindingResult);
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
            throw new MeasurementsError(errorMsg.toString());
        }
        // конвертация должна работать только на уровне контроллера!
        serviceMeasurements.add(measurements);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    private Measurements convertToMeasurementsDTOInMeasurements(MeasurementsDTO measurementsDTO){
        return modelMapper.map(measurementsDTO, Measurements.class);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handlerException(MeasurementsError e){
        ErrorResponse response = new ErrorResponse(
                e.getMessage(), System.currentTimeMillis()
        );
        // в Http ответе тело ответа(response) и статус в заголовке
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); // NOT_FOUND - 404 статус
    }

}

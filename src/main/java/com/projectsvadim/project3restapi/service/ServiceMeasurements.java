package com.projectsvadim.project3restapi.service;

import com.projectsvadim.project3restapi.models.Measurements;
import com.projectsvadim.project3restapi.repository.MeasurementsRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ServiceMeasurements {

    final MeasurementsRepository measurementsRepository;
    final ServiceSensors serviceSensors;

    @Autowired
    public ServiceMeasurements(MeasurementsRepository measurementsRepository, ServiceSensors serviceSensors) {
        this.measurementsRepository = measurementsRepository;
        this.serviceSensors = serviceSensors;
    }

    public List<Measurements> findAll(){
        return measurementsRepository.findAll();
    }

//    public List<Measurements> findAllByRainingIs(){
//        return measurementsRepository.findAllByRainingIsTrue();
//    }


    @Transactional
    public void add(Measurements measurements){
        updateMeasurements(measurements);
        measurementsRepository.save(measurements);
    }
    public void updateMeasurements(Measurements measurements){
        // для Hibernate persistence context'а
        measurements.setSensor(serviceSensors.findByName(measurements.getSensor().getName()).get());
        measurements.setDateCreatedAt(LocalDateTime.now());
    }
}

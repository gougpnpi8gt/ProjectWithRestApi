package com.projectsvadim.project3restapi.service;

import com.projectsvadim.project3restapi.models.Measurements;
import com.projectsvadim.project3restapi.repository.MeasurementsRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ServiceMeasurements {

    final MeasurementsRepository measurementsRepository;

    @Autowired
    public ServiceMeasurements(MeasurementsRepository measurementsRepository) {
        this.measurementsRepository = measurementsRepository;
    }

    public List<Measurements> findAll(){
        return measurementsRepository.findAll();
    }

    public List<Measurements> findAllByRainingIs(){
        return measurementsRepository.findAllByRainingIsTrue();
    }


    @Transactional
    public void add(Measurements measurements){
        measurements.setDateCreatedAt(LocalDateTime.now());
        measurementsRepository.save(measurements);
    }

}

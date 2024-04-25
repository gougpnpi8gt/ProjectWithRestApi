package com.projectsvadim.project3restapi.service;

import com.projectsvadim.project3restapi.models.Sensor;
import com.projectsvadim.project3restapi.repository.SensorsRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@Transactional(readOnly = true)
public class ServiceSensors {

    final SensorsRepository sensorsRepository;

    @Autowired
    public ServiceSensors(SensorsRepository sensorsRepository) {
        this.sensorsRepository = sensorsRepository;
    }

    public Optional<Sensor> findByName(String name){
        Optional<Sensor> sensor = sensorsRepository.findByName(name);
        return sensor;
    }

    @Transactional
    public void save(Sensor sensor){
        sensorsRepository.save(sensor);
    }
}

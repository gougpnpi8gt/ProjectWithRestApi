package com.projectsvadim.project3restapi.repository;

import com.projectsvadim.project3restapi.models.Measurements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeasurementsRepository extends JpaRepository<Measurements, Integer> {
    List<Measurements> findAllByRainingIsTrue();
}

package com.projectsvadim.project3restapi.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MeasurementsResponse {

    private List<MeasurementsDTO> measurements;

    public MeasurementsResponse(List<MeasurementsDTO> measurements) {
        this.measurements = measurements;
    }
}

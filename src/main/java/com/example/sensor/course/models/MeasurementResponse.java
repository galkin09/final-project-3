package com.example.sensor.course.models;

import com.example.sensor.course.dto.MeasurementDTO;

import java.util.List;

public class MeasurementResponse {
    private List<MeasurementDTO> measurements;

    public MeasurementResponse(List<MeasurementDTO> measurements) {
        this.measurements = measurements;
    }

    public List<MeasurementDTO> getMeasurements() {
        return measurements;
    }
    public void setMeasurements(List<MeasurementDTO> measurements) {
        this.measurements = measurements;
    }
}

package com.example.sensor.course.controllers;

import com.example.sensor.course.dto.MeasurementDTO;
import com.example.sensor.course.dto.SensorDTO;
import com.example.sensor.course.models.Measurement;
import com.example.sensor.course.models.MeasurementResponse;
import com.example.sensor.course.models.Sensor;
import com.example.sensor.course.services.MeasurementService;
import com.example.sensor.course.util.MeasurementException;
import com.example.sensor.course.util.MeasurementResponseError;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.sensor.course.util.ErrorsUtil.returnErrorsToClient;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    private final MeasurementService measurementService;
    private final ModelMapper modelMapper;

    @Autowired
    public MeasurementController(MeasurementService measurementService, ModelMapper modelMapper) {
        this.measurementService = measurementService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public MeasurementResponse getMeasurements() {
        return new MeasurementResponse(measurementService.getMeasurements().stream().map(this::convertToMeasurementDTO).collect(Collectors.toList()));
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO,
                                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            returnErrorsToClient(bindingResult);
        }

        measurementService.addMeasurement(convertToMeasurement(measurementDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/rainyDayCount")
    public Long getRainyDayCount() {
        return measurementService.getMeasurements().stream().filter(Measurement::isRaining).count();
    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementResponseError> handleException(MeasurementException e) {
        MeasurementResponseError response = new MeasurementResponseError(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}

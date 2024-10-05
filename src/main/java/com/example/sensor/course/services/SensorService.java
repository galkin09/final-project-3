package com.example.sensor.course.services;

import com.example.sensor.course.models.Sensor;
import com.example.sensor.course.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SensorService {

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public List<Sensor> findAll() {
        return sensorRepository.findAll();
    }

    public Optional<Sensor> findByName(String name) {
        return sensorRepository.findByName(name);
    }

    public void register(Sensor sensor) {
        sensorRepository.save(sensor);
    }
}

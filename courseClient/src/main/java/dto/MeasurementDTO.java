package dto;

public class MeasurementDTO {

    private Double value;
    private Boolean raining;
    private SensorDTO sensorD;


    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Boolean getRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensorD() {
        return sensorD;
    }

    public void setSensorD(SensorDTO sensorD) {
        this.sensorD = sensorD;
    }
}

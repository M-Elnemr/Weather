package com.example.weather.model;

public class Model {

    private String cityName;
    private String temperature;
    private String windSpeed;
    private String windDirection;

    public Model(){}
    public Model(String cityName, String temperature, String windSpeed, String windDirection) {
        this.cityName = cityName;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getCityName() {
        return cityName;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getWindDirection() {
        return windDirection;
    }
}

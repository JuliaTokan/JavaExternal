package org.java.observer.view;

import org.java.observer.model.Subject;

import java.time.LocalDateTime;
import java.util.Map;

public class CurrentConditionsDisplay implements Observer, DisplayElement {
    private double temperature;
    private double humidity;
    private double pressure;

    private static final String TEMP = "temp";
    private static final String HUMIDITY = "humidity";
    private static final String PRESSURE = "pressure";

    private Subject weatherData;

    public CurrentConditionsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println("Current conditions: ");
        System.out.println("    temperature: " + temperature + " C");
        System.out.println("    humidity: " + humidity + " %");
        System.out.println("    pressure: " + pressure + " hPa");
    }

    @Override
    public void update(Map<String, Double> currentWeatherInfo, Map<LocalDateTime, Map<String, Double>> forecastWeatherInfo) {
        this.temperature = currentWeatherInfo.get(TEMP);
        this.humidity = currentWeatherInfo.get(HUMIDITY);
        this.pressure = currentWeatherInfo.get(PRESSURE);
        display();
    }
}


package org.java.observer.view;

import org.java.observer.model.Subject;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

public class ForecastDisplay implements Observer, DisplayElement {
    private Map<LocalDateTime, Map<String, Double>> timeWeatherInfo;

    private static final String TEMP = "temp";
    private static final String HUMIDITY = "humidity";
    private static final String PRESSURE = "pressure";

    private Subject weatherData;

    public ForecastDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
        timeWeatherInfo = new LinkedHashMap<>();
    }

    @Override
    public void display() {
        System.out.println("Forecast for every 3 hours:");
        for (LocalDateTime time : timeWeatherInfo.keySet()) {
            System.out.println("   Time: " + time);
            Map<String, Double> weatherInfo = timeWeatherInfo.get(time);
            System.out.println("       temperature: " + weatherInfo.get(TEMP) + " C");
            System.out.println("       humidity: " + weatherInfo.get(HUMIDITY) + " %");
            System.out.println("       pressure: " + weatherInfo.get(PRESSURE) + " hPa");
        }
    }

    @Override
    public void update(Map<String, Double> currentWeatherInfo,
                       Map<LocalDateTime, Map<String, Double>> forecastWeatherInfo) {
        this.timeWeatherInfo = forecastWeatherInfo;
        display();
    }
}

package org.java.observer.model;

import org.java.observer.view.Observer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WeatherData implements Subject {
    private List observers;
    private Map<String, Double> currentWeatherInfo;
    private Map<LocalDateTime, Map<String, Double>> forecastWeatherInfo;

    public WeatherData() {
        this.observers = new ArrayList();
        currentWeatherInfo = new LinkedHashMap<>();
        forecastWeatherInfo = new LinkedHashMap<>();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        int i = observers.indexOf(o);
        if (i >= 0)
            observers.remove(i);
    }

    @Override
    public void notifyObservers() {
        for (int i = 0; i < observers.size(); i++) {
            Observer observer = (Observer) observers.get(i);
            observer.update(currentWeatherInfo, forecastWeatherInfo);
        }
    }

    public void measurementsChanged(){
        notifyObservers();
    }

    public void setMeasurements(Map<String, Double> currentWeatherInfo,
            Map<LocalDateTime, Map<String, Double>> forecastWeatherInfo) {
        this.currentWeatherInfo = currentWeatherInfo;
        this.forecastWeatherInfo = forecastWeatherInfo;
        measurementsChanged();
    }
}

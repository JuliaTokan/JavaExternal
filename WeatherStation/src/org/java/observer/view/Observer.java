package org.java.observer.view;

import java.time.LocalDateTime;
import java.util.Map;

public interface Observer {
    public void update(/*double temp, double humidity, double pressure, Map<LocalDateTime, Map<String, Double>> timeWeatherInfo*/Map<String, Double> currentWeatherInfo,
                                                                                                                                 Map<LocalDateTime, Map<String, Double>> forecastWeatherInfo);
}

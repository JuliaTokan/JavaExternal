package org.java.observer;

import org.java.observer.controller.WeatherStation;
import org.java.observer.model.WeatherData;
import org.java.observer.view.CurrentConditionsDisplay;
import org.java.observer.view.ForecastDisplay;

public class WeatherDemo {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();

        CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay(weatherData);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);

        WeatherStation weatherStation = new WeatherStation();
        weatherStation.startWeatherForecast(weatherData);
    }
}

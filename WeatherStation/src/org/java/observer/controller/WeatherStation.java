package org.java.observer.controller;

import org.java.observer.model.WeatherData;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WeatherStation {
    private String apiBase = "http://api.openweathermap.org/data/2.5/weather?q=";
    private String apiForecast = "http://api.openweathermap.org/data/2.5/forecast?q=";
    private String apiKey = "d13d53ade175d471bd6e3b848feb842b";

    private static final String TEMP = "temp";
    private static final String HUMIDITY = "humidity";
    private static final String PRESSURE = "pressure";

    public void startWeatherForecast(WeatherData weatherData){
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    Map<String,Double> currWeather = getCurrentWeather("Kiev");
                    Map<LocalDateTime, Map<String, Double>> forecastWeatherInfo = getForecastWeather("Kiev");
                    weatherData.setMeasurements(currWeather,forecastWeatherInfo);
                }
                catch (Exception e){
                    System.out.println("Problem with connection!");
                }
            }
        }, 0, 1, TimeUnit.MINUTES);
    }

    public Map<String,Double> getCurrentWeather(String city) throws Exception {
        String url = apiBase+city+"&units=metric&appid=" + apiKey;
        String info = getInfoFromURL(url);
        return parseCurrentWeatherInfo(info);
    }

    public Map<LocalDateTime, Map<String, Double>> getForecastWeather(String city) throws Exception {
        String url = apiForecast+city+"&units=metric&appid=" + apiKey;
        String info = getInfoFromURL(url);
        return parseForecastWeatherInfo(info);
    }

    private String getInfoFromURL(String url)throws Exception{
        URL website = new URL(url);
        URLConnection connection = website.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        connection.getInputStream()));

        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null)
            response.append(inputLine);

        in.close();

        return response.toString();
    }

    private Map<String,Double> parseCurrentWeatherInfo(String info){
        JSONObject jsonWeather = new JSONObject(info).getJSONObject("main");

        Map<String,Double> weather = new HashMap<>();

        weather.put(TEMP, jsonWeather.getDouble(TEMP));
        weather.put(HUMIDITY, jsonWeather.getDouble(HUMIDITY));
        weather.put(PRESSURE, jsonWeather.getDouble(PRESSURE));

        return weather;
    }

    private Map<LocalDateTime, Map<String, Double>> parseForecastWeatherInfo(String info){
        Map<LocalDateTime, Map<String, Double>> forecastInfo = new LinkedHashMap<>();

        JSONArray jsonWeather = new JSONObject(info).getJSONArray("list");
        for(int i =0; i<jsonWeather.length(); i++){
            JSONObject weather = jsonWeather.getJSONObject(i);
            Long timeUTF = weather.getLong("dt");
            LocalDateTime time = LocalDateTime.ofInstant(Instant.ofEpochSecond(timeUTF), ZoneOffset.UTC);

            JSONObject currWeather = weather.getJSONObject("main");

            Map<String,Double> weatherInfo = new LinkedHashMap<>();

            weatherInfo.put(TEMP, currWeather.getDouble(TEMP));
            weatherInfo.put(HUMIDITY, currWeather.getDouble(HUMIDITY));
            weatherInfo.put(PRESSURE, currWeather.getDouble(PRESSURE));

            forecastInfo.put(time, weatherInfo);
        }

        return forecastInfo;
    }
}

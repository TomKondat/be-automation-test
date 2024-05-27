package com.tomkondat5.app;

public class App {
    public static void main(String[] args) throws IllegalArgumentException, InterruptedException {

        FetchWeather fetchWeather = new FetchWeather();

        fetchWeather.printData();
        fetchWeather.printResponseCode();
        fetchWeather.printCountry();
        fetchWeather.printTemperature();
        fetchWeather.verifyCountry();
    }
}

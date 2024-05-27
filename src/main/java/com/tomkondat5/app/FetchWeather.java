package com.tomkondat5.app;

import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

import io.github.cdimascio.dotenv.Dotenv;

public class FetchWeather {
    final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=%s&APPID=%s&units=%s";
    JSONObject londonData;
    JSONObject nyData;
    JSONObject tlvData;

    public FetchWeather() throws IllegalArgumentException, InterruptedException {
        Dotenv dotenv = null;
        dotenv = Dotenv.configure().load();

        try {
            // Get the data from the URL
            System.out.println("Fetching data from the URL...");
            londonData = JsonReader
                    .readJsonFromUrl(String.format(BASE_URL, "London,uk", dotenv.get("API_KEY"), "imperial"));
            Thread.sleep(3000);
            nyData = JsonReader
                    .readJsonFromUrl(String.format(BASE_URL, "New York City,us", dotenv.get("API_KEY"), "imperial"));
            Thread.sleep(3000);
            tlvData = JsonReader
                    .readJsonFromUrl(String.format(BASE_URL, "Tel Aviv,il", dotenv.get("API_KEY"), "metric"));
        } catch (IOException | JSONException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void printData() {
        System.out.println("Data of London:\n" + londonData.toString());
        System.out.println("Data of New York:\n" + nyData.toString());
        System.out.println("Data of Tel Aviv:\n" + tlvData.toString());
    }

    public void printResponseCode() {
        int response = londonData.getInt("cod");
        System.out.println("Response code: " + response);

        if (isHttpOK()) {
            System.out.println("Response code is equal to '200'");
        }
    }

    private boolean isHttpOK() {
        return londonData.getInt("cod") == 200;
    }

    public void printCountry() {
        System.out.println("Country: " + londonData.getJSONObject("sys").getString("country"));
    }

    public void printTemperature() {
        System.out.println("Temperature in Tel Aviv: " + tlvData.getJSONObject("main").getDouble("temp") + "°C");
        System.out.println("Temperature in New York: " + nyData.getJSONObject("main").getDouble("temp") + "°F");
        System.out.println("Temperature in London: " + londonData.getJSONObject("main").getDouble("temp") + "°F");
    }

    public void verifyCountry() {
        String country = tlvData.getJSONObject("sys").getString("country");
        if (country.equals("IL")) {
            System.out.println("Country: " + tlvData.getJSONObject("sys").getString("country"));
        } else {
            System.out.println("Country is not IL");
        }
    }
}

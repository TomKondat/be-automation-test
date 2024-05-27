package com.tomkondat5.app;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import io.github.cdimascio.dotenv.Dotenv;

public class App {
    public static void main(String[] args) throws IllegalArgumentException, InterruptedException {
        Dotenv dotenv = null;
        dotenv = Dotenv.configure().load();

        final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=%s&APPID=%s&units=%s";

        JSONObject londonData = null;
        JSONObject nyData = null;
        JSONObject tlvData = null;

        try {
            // Get the data from the URL
            londonData = JsonReader
                    .readJsonFromUrl(String.format(BASE_URL, "London,uk", dotenv.get("API_KEY"), "imperial"));
            Thread.sleep(1000);
            nyData = JsonReader
                    .readJsonFromUrl(String.format(BASE_URL, "New York City,us", dotenv.get("API_KEY"), "imperial"));
            Thread.sleep(1000);
            tlvData = JsonReader
                    .readJsonFromUrl(String.format(BASE_URL, "Tel Aviv,il", dotenv.get("API_KEY"), "metric"));
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        // print the data
        System.out.println("Data of London:\n" + londonData.toString());
        System.out.println("Data of New York:\n" + nyData.toString());
        System.out.println("Data of Tel Aviv:\n" + tlvData.toString());

        // print the response code
        int response = londonData.getInt("cod");
        System.out.println("Response code: " + response);

        if (response == 200) {
            System.out.println("Response code is equal to '200'");
        }

        // gets the country
        System.out.println("Country: " + londonData.getJSONObject("sys").getString("country"));

        // gets the temperature in tel aviv in celsius
        System.out.println("Temperature in Tel Aviv: " + tlvData.getJSONObject("main").getDouble("temp") + "°C");

        // gets the temperature in new york in fahrenheit
        System.out.println("Temperature in New York: " + nyData.getJSONObject("main").getDouble("temp") + "°F");

        // gets the temperature in london in fahrenheit
        System.out.println("Temperature in London: " + londonData.getJSONObject("main").getDouble("temp") + "°F");

        System.out.println("Country: " + tlvData.getJSONObject("sys").getString("country"));
    }
}

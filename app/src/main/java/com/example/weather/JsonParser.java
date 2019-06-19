package com.example.weather;

import android.location.Location;

import com.example.weather.model.Model;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonParser {

    public static Model parsing(String data) throws JSONException {
        JSONObject jsonObject = new JSONObject(data);

        String cityName = jsonObject.getString("name");

        JSONObject mObject = jsonObject.getJSONObject("main");
        String temperature = mObject.getString("temp");

        JSONObject wObject = jsonObject.getJSONObject("wind");
        double speed = wObject.getDouble("speed");
        String windSpeed = String.valueOf(speed);
        int degree = wObject.getInt("deg");

        return new Model(cityName, temperature, windSpeed, windDirection(degree));
    }

    private static String windDirection(int degree){
        if (degree>337.5) return "North";
        if (degree>292.5) return "North West";
        if(degree>247.5) return "'West";
        if(degree>202.5) return "South West";
        if(degree>157.5) return "South";
        if(degree>122.5) return "South East";
        if(degree>67.5) return "East";
        if(degree>22.5)return "North East";
        return "North";
    }
}

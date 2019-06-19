package com.example.weather;

import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonClient {

    public static String BASE_URL= "http://api.openweathermap.org/data/2.5/weather?q=";
    public static String KEY = "&APPID=2d10ee8ccdf7cf440d0c83706b6513da";

    public static String getWeather(String location){

        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        try {
            URL url = new URL(BASE_URL + location + KEY);

            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.connect();

            StringBuffer buffer = new StringBuffer();
            inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = bufferedReader.readLine()) != null){
                buffer.append(line);
            }
            httpURLConnection.disconnect();
            inputStream.close();
            return buffer.toString();

        }catch (Throwable t){
            t.printStackTrace();
        }

        finally {
            try {
                inputStream.close();
                httpURLConnection.disconnect();
            }catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}

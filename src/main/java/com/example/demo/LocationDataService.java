package com.example.demo;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

@Service
public class LocationDataService {
    private String accessKey ="?access_key=6073882684e5bfda26ccd27674089998";
    private String apiAddress ="http://api.ipapi.com/api/";
    public String getDateInTimezone(String timeZone) {
        ZonedDateTime currentDateTime = ZonedDateTime.now();
        ZonedDateTime targetDateTime = currentDateTime.withZoneSameInstant(ZoneId.of(timeZone));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = targetDateTime.format(formatter);
        return formattedDateTime;
    }

    public String getTimezone(LocationData locationData) {
        String cityName = locationData.getCity();
        String continent = locationData.getContinentName();
        ZoneId zoneId = ZoneId.of(TimeZone.getDefault().getID());
        ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);

        String timeZone = zonedDateTime
                .withZoneSameInstant(ZoneId.of(continent+"/"+cityName))
                .getZone()
                .getId();
        return timeZone;
    }

    public String getIpAddress() throws IOException {
        URL url = new URL("http://checkip.dyndns.org/");
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        String ipAddress = br.readLine().replaceAll("[^\\d.]", "");
        return ipAddress;
    }
    public LocationData getLocationInfo(String ipAddress) throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet request2 = new HttpGet(apiAddress+ipAddress+accessKey);

        HttpResponse response = client.execute(request2);
        HttpEntity entity = response.getEntity();
        String jsonResponse = EntityUtils.toString(entity);
        Gson gson = new Gson();
        return gson.fromJson(jsonResponse, LocationData.class);

    }
}

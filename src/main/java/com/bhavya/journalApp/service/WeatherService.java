package com.bhavya.journalApp.service;

import com.bhavya.journalApp.api.response.WeatherResponse;
import com.bhavya.journalApp.cache.AppCache;
import com.bhavya.journalApp.constants.PlaceHolders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    public String APIKEY;

    @Autowired
    private AppCache appCache;


    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city) {
        String finalAPI = appCache.appCache.get("weather_api").replace(PlaceHolders.API_KEY, APIKEY).replace(PlaceHolders.CITY, city);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse body = response.getBody();
        return body;
    }
}

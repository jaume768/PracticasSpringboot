package com.example.ataula_api.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

@Service
public class GooglePlacesService {

    @Value("${google.api.key}")
    private String googleApiKey;

    private final RestTemplate restTemplate;

    public GooglePlacesService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Object obtenerRestaurantes(double latitud, double longitud) {
        String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json";

        Map<String, String> params = new HashMap<>();
        params.put("location", latitud + "," + longitud);
        params.put("radius", "1000");
        params.put("type", "restaurant");
        params.put("keyword", "Delivery");
        params.put("key", googleApiKey);

        return restTemplate.getForObject(url + "?location={location}&radius={radius}&type={type}&key={key}", Object.class, params);
    }
}


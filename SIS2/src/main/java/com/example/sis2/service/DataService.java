package com.example.sis2.service;

import com.example.sis2.repository.DataRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class DataService {

    private final DataRepository repository;
    private final RestTemplate restTemplate;

    public DataService(DataRepository repository) {
        this.repository = repository;
        this.restTemplate = new RestTemplate();
    }

    public Map<String, Object> getExternalData() {

        String url = "https://api.chucknorris.io/jokes/random";

        Map response = restTemplate.getForObject(url, Map.class);

        return Map.of(
                "source", repository.getSourceName(),
                "value", response.get("value")
        );
    }
}

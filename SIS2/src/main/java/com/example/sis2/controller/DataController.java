package com.example.sis2.controller;

import com.example.sis2.service.DataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DataController {

    private final DataService service;

    public DataController(DataService service) {
        this.service = service;
    }

    @GetMapping("/api/data")
    public Map<String, Object> getData() {
        return service.getExternalData();
    }
}

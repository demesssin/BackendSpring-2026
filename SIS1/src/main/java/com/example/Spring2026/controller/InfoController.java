package com.example.Spring2026.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class InfoController {

    @GetMapping("/api/info")
    public Map<String, Object> getInfo() {
        return Map.of(
                "student", "Nurik Demesinov",
                "course", "Spring Framework",
                "week", 1
        );
    }
}

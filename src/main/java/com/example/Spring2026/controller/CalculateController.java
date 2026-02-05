package com.example.Spring2026.controller;

import com.example.Spring2026.dto.CalculateRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class CalculateController {

    @PostMapping("/api/calculate")
    public Map<String, Integer> calculate(@RequestBody CalculateRequest request) {
        int result = request.getA() + request.getB();
        return Map.of("result", result);
    }
}

package com.example.sis2.repository;

import org.springframework.stereotype.Repository;

@Repository
public class DataRepository {

    public String getSourceName() {
        return "external-api";
    }
}

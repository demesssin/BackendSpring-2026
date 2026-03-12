package com.studentmanagement.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Student Management API")
                        .description("Production-ready REST API for managing students with full Swagger documentation")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Backend Team")
                                .email("backend@kbtu.kz")))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Local server")));
    }
}

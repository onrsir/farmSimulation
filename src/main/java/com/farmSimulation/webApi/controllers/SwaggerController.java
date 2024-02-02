package com.farmSimulation.webApi.controllers;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.ResourceLoader;

@RestController
public class SwaggerController {

    private final ResourceLoader resourceLoader;

    public SwaggerController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @GetMapping("/swagger.yaml")
    public ResponseEntity<Resource> getOpenAPIDefinition() {
        Resource resource = resourceLoader.getResource("classpath:/static/openapi.yaml");
        return ResponseEntity.ok().body(resource);
    }
}


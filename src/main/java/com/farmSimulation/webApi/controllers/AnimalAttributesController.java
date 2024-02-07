package com.farmSimulation.webApi.controllers;

import com.farmSimulation.business.requests.CreateAnimalAttributeRequest;
import com.farmSimulation.business.requests.UpdateAnimalAttributeRequest;
import com.farmSimulation.business.responses.GetAnimalAttributeResponse;
import com.farmSimulation.business.services.AnimalAttributeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animal-attributes")
@AllArgsConstructor
public class AnimalAttributesController {

    private final AnimalAttributeService animalAttributeService;

    @GetMapping()
    public ResponseEntity<List<GetAnimalAttributeResponse>> getAllAttributes() {
        List<GetAnimalAttributeResponse> attributes = animalAttributeService.getAllAttributes();
        return ResponseEntity.ok(attributes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetAnimalAttributeResponse> getAttributeById(@PathVariable Long id) {
        GetAnimalAttributeResponse attribute = animalAttributeService.getAttributeById(id);
        return ResponseEntity.ok(attribute);
    }

    @PostMapping()
    public ResponseEntity<Void> addAttribute(@RequestBody CreateAnimalAttributeRequest request) {
        animalAttributeService.addAttribute(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAttribute(@PathVariable Long id, @RequestBody UpdateAnimalAttributeRequest request) {
        request.setId(id);
        animalAttributeService.updateAttribute(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttribute(@PathVariable Long id) {
        animalAttributeService.deleteAttribute(id);
        return ResponseEntity.ok().build();
    }
}

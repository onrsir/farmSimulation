package com.farmSimulation.webApi.controllers;
import com.farmSimulation.business.requests.UpdateAnimalRequest;
import com.farmSimulation.business.responses.GetAnimalCountByTypeResponse;
import com.farmSimulation.business.responses.GetAnimalDetailResponse;
import com.farmSimulation.business.responses.GetByIdAnimalsResponse;
import com.farmSimulation.business.services.AnimalService;
import com.farmSimulation.business.requests.CreateAnimalRequest;
import com.farmSimulation.business.responses.GetAllAnimalResponse;
import com.farmSimulation.entities.concretes.Animal;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animals")
@AllArgsConstructor
public class AnimalsController {
 //  Data access > Business > API
    private AnimalService animalService;

    @GetMapping()
    @Operation(summary = "Get All")
    public List<GetAllAnimalResponse> getAll() {
        return animalService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get By Id")
    public GetByIdAnimalsResponse getById(@PathVariable int id) {
        return animalService.getById(id);
    }

   /* @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(CreateAnimalRequest createAnimalRequest){
        this.animalService.add(createAnimalRequest);
    }
*/
   @PostMapping()
   @ResponseStatus(code = HttpStatus.CREATED)
   @Operation(summary = "Create")
   public void add(@Parameter(description = "Hayvanın yaratılma isteği", required = true)
                   @RequestBody CreateAnimalRequest createAnimalRequest){

       animalService.add(createAnimalRequest);
   }

    @PutMapping()
    @Operation(summary = "Update")
    public void update(@RequestBody UpdateAnimalRequest updateAnimalRequest) {
        this.animalService.update(updateAnimalRequest);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete")
    public void delete(@PathVariable int id) {
        this.animalService.delete(id);
    }

    @GetMapping("/counts")
    @Operation(summary = "Count")
    public List<GetAnimalCountByTypeResponse> getAnimalCountsByType() {
        return animalService.GetAnimalCountByTypeResponse();
    }

    @GetMapping("/by-type/{type}")
    @Operation(summary = "Listing by type")
    public ResponseEntity<List<GetAnimalDetailResponse>> getAnimalsByType(@PathVariable String type) {
        List<GetAnimalDetailResponse> animals = animalService.getAnimalsByType(type);
        return ResponseEntity.ok().body(animals);
    }


}


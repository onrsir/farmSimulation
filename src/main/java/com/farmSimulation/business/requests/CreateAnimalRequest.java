package com.farmSimulation.business.requests;

import com.farmSimulation.entities.concretes.Animal;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAnimalRequest {

    @Schema(description = "Hayvanın adı", example = "Boncuk", required = true)
    private String name;

    @Schema(description = "Hayvan türü", required = true, example = "Keçi", allowableValues = {"Keçi", "Koyun", "Tavuk"})
    private String type;



}

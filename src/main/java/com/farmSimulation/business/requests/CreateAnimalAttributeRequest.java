package com.farmSimulation.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAnimalAttributeRequest {
    private Long animalId;
    private String attributeName;
    private String attributeValue;

}

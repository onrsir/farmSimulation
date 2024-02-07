package com.farmSimulation.business.responses;

import com.farmSimulation.entities.concretes.Animal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAnimalAttributeResponse {

        private Long id;
        private String attributeValue;
        private String attributeName;
        private String animalName;
        private Animal.AnimalType animalType;
}

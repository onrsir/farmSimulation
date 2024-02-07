package com.farmSimulation.business.requests;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAnimalAttributeRequest {
    private Long id;
    private String attributeName;
}

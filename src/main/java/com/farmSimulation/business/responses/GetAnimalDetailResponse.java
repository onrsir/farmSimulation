package com.farmSimulation.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetAnimalDetailResponse {
    private int id;
    private String name;
    private String type;
}

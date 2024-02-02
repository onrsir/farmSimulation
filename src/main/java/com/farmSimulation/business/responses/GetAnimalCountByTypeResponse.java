package com.farmSimulation.business.responses;



import lombok.Data;

@Data
public class GetAnimalCountByTypeResponse {
    private String type;
    private int count;

    public GetAnimalCountByTypeResponse(String type, int count) {
        this.type = type; // Enum'ı String'e çevirir
        this.count = count;
    }
}


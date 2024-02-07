package com.farmSimulation.business.services;

import com.farmSimulation.business.requests.CreateAnimalAttributeRequest;
import com.farmSimulation.business.requests.UpdateAnimalAttributeRequest;
import com.farmSimulation.business.responses.GetAnimalAttributeResponse;

import java.util.List;

public interface AnimalAttributeService {
    List<GetAnimalAttributeResponse> getAllAttributes();
    GetAnimalAttributeResponse getAttributeById(Long id);
    void addAttribute(CreateAnimalAttributeRequest request);
    void updateAttribute(UpdateAnimalAttributeRequest request);
    void deleteAttribute(Long id);
}

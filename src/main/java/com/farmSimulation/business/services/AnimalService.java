package com.farmSimulation.business.services;

import com.farmSimulation.business.requests.CreateAnimalRequest;
import com.farmSimulation.business.requests.UpdateAnimalRequest;
import com.farmSimulation.business.responses.GetAllAnimalResponse;
import com.farmSimulation.business.responses.GetAnimalCountByTypeResponse;
import com.farmSimulation.business.responses.GetAnimalDetailResponse;
import com.farmSimulation.business.responses.GetByIdAnimalsResponse;

import java.util.List;

public interface AnimalService {
    List<GetAllAnimalResponse> getAll();
    void add(CreateAnimalRequest createAnimalRequest);
    GetByIdAnimalsResponse getById(int id);
    void update(UpdateAnimalRequest updateAnimalRequest);
    void delete(int id);
    List<GetAnimalDetailResponse> getAnimalsByType(String type);

    List<GetAnimalCountByTypeResponse> GetAnimalCountByTypeResponse();

}

package com.farmSimulation.business.services;

import com.farmSimulation.business.requests.CreateAnimalRequest;
import com.farmSimulation.business.requests.UpdateAnimalRequest;
import com.farmSimulation.business.responses.GetAllAnimalResponse;
import com.farmSimulation.business.responses.GetAnimalCountByTypeResponse;
import com.farmSimulation.business.responses.GetAnimalDetailResponse;
import com.farmSimulation.business.responses.GetByIdAnimalsResponse;
import com.farmSimulation.core.utilities.mappers.ModelMapperService;
import com.farmSimulation.dataAccess.abstracts.AnimalRepository;
import com.farmSimulation.entities.concretes.Animal;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class AnimalServiceImpl implements AnimalService {

    private AnimalRepository animalRepository;
    private ModelMapperService modelMapperService;


    @Override
    public List<GetAllAnimalResponse> getAll() {

        List<Animal> animals = animalRepository.findAll();

        List<GetAllAnimalResponse> animalResponses = animals.stream()
                .map(animalItem -> this.modelMapperService.forResponse()
                .map(animalItem, GetAllAnimalResponse.class)).collect(Collectors.toList());

        return animalResponses;
    }

    @Override
    public void add(CreateAnimalRequest createAnimalRequest) {
        Animal animal = this.modelMapperService.forRequest().map(createAnimalRequest,Animal.class);
        this.animalRepository.save(animal);
    }

    @Override
    public GetByIdAnimalsResponse getById(int id) {
        Animal animal = this.animalRepository.findById(id).orElseThrow();

        GetByIdAnimalsResponse response = this.modelMapperService.forResponse()
                .map(animal,GetByIdAnimalsResponse.class);

        return response;
    }

    @Override
    public void update(UpdateAnimalRequest updateAnimalRequest) {
        Animal animal = this.modelMapperService.forRequest().map(updateAnimalRequest,Animal.class);
        this.animalRepository.save(animal);

    }

    @Override
    public void delete(int id) {
        this.animalRepository.deleteById(id);

    }

    @Override
    public List<GetAnimalCountByTypeResponse> GetAnimalCountByTypeResponse() {
        return animalRepository.countAnimalsByType().stream()
                .map(result -> new GetAnimalCountByTypeResponse(((Animal.AnimalType) result[0]).name(),
                ((Long) result[1]).intValue()))
                .collect(Collectors.toList());    }

    @Override
    public List<GetAnimalDetailResponse> getAnimalsByType(String type) {
        List<Animal> animals = animalRepository.findByType(Animal.AnimalType.valueOf(type.toUpperCase()));
        return animals.stream()
                .map(animal -> modelMapperService.forResponse()
                .map(animal, GetAnimalDetailResponse.class))
                .collect(Collectors.toList());
    }


}

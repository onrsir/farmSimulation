package com.farmSimulation.business.services;

import com.farmSimulation.business.requests.CreateAnimalAttributeRequest;
import com.farmSimulation.business.requests.UpdateAnimalAttributeRequest;
import com.farmSimulation.business.responses.GetAnimalAttributeResponse;
import com.farmSimulation.core.utilities.mappers.ModelMapperService;
import com.farmSimulation.dataAccess.abstracts.AnimalAttributeRepository;
import com.farmSimulation.entities.concretes.Animal;
import com.farmSimulation.entities.concretes.AnimalAttribute;
import com.farmSimulation.dataAccess.abstracts.AnimalRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AnimalAttributeServiceImpl implements AnimalAttributeService {

    private AnimalRepository animalRepository;
    private final AnimalAttributeRepository animalAttributeRepository;
    private final ModelMapperService modelMapperService;

    @Override
    public List<GetAnimalAttributeResponse> getAllAttributes() {
        List<AnimalAttribute> attributes = animalAttributeRepository.findAll();
        return attributes.stream()
                .map(attribute -> {
                    // ModelMapper ile dönüşüm yapılıyor
                    GetAnimalAttributeResponse response = modelMapperService.forResponse().map(attribute, GetAnimalAttributeResponse.class);
                    // İlişkili Animal'ın bilgileri otomatik olarak dönüştürülebilir. Eğer değilse, burada manuel olarak set edilmelidir.
                    return response;
                })
                .collect(Collectors.toList());
    }
   /* @Override
    public List<GetAnimalAttributeResponse> getAllAttributes() {
        List<AnimalAttribute> attributes = animalAttributeRepository.findAll();
        return attributes.stream()
                .map(attribute -> modelMapperService.forResponse().map(attribute, GetAnimalAttributeResponse.class))
                .collect(Collectors.toList());
    }*/



    @Override
    public GetAnimalAttributeResponse getAttributeById(Long id) {
        AnimalAttribute attribute = animalAttributeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Attribute not found with id: " + id));
        return modelMapperService.forResponse().map(attribute, GetAnimalAttributeResponse.class);
    }

    @Override
    public void addAttribute(CreateAnimalAttributeRequest request) {
        // İlk olarak ilgili Animal nesnesini bul
        Animal animal = animalRepository.findById(Math.toIntExact(request.getAnimalId()))
                .orElseThrow(() -> new RuntimeException("Animal not found with id: " + request.getAnimalId()));

        // request nesnesini AnimalAttribute nesnesine dönüştür
        AnimalAttribute attribute = modelMapperService.forRequest().map(request, AnimalAttribute.class);

        // Bulunan Animal nesnesini AnimalAttribute nesnesine set et
        attribute.setAnimal(animal);

        animalAttributeRepository.save(attribute);
    }


    @Override
    public void updateAttribute(UpdateAnimalAttributeRequest request) {
        AnimalAttribute attribute = modelMapperService.forRequest().map(request, AnimalAttribute.class);
        animalAttributeRepository.save(attribute);
    }

    @Override
    public void deleteAttribute(Long id) {
        animalAttributeRepository.deleteById(id);
    }
}

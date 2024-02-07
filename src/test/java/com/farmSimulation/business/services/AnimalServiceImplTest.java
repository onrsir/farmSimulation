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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AnimalServiceImplTest {

    @Mock
    private AnimalRepository animalRepository;

    @Mock
    private ModelMapperService modelMapperService;

    @InjectMocks
    private AnimalServiceImpl animalService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllAnimalsTest() {
        // Mock repository response
        when(animalRepository.findAll()).thenReturn(Collections.singletonList(new Animal()));

        // Mock model mapper behavior
        when(modelMapperService.forResponse()).thenReturn(new ModelMapper());

        // Call the method under test
        List<GetAllAnimalResponse> responses = animalService.getAll();

        // Assert the results
        assertNotNull(responses);
        assertEquals(1, responses.size());

        // Verify interactions
        verify(animalRepository, times(1)).findAll();
    }

    @Test
    void addAnimalTest() {
        // Given
        CreateAnimalRequest createAnimalRequest = new CreateAnimalRequest();
        createAnimalRequest.setType("Keçi");
        createAnimalRequest.setName("Boncuk");

        Animal animal = new Animal();
        animal.setType(Animal.AnimalType.KEÇİ);
        animal.setName("Boncuk");

        when(modelMapperService.forRequest()).thenReturn(new ModelMapper());

        // When
        animalService.add(createAnimalRequest);

        // Then
        verify(animalRepository).save(any(Animal.class));
    }

    @Test
    void getAnimalByIdTest() {
        // Given
        int animalId = 1;
        Animal animal = new Animal();
        animal.setId(animalId);
        animal.setType(Animal.AnimalType.KEÇİ);
        animal.setName("Boncuk");

        when(animalRepository.findById(animalId)).thenReturn(java.util.Optional.of(animal));
        when(modelMapperService.forResponse()).thenReturn(new ModelMapper());

        // When
        GetByIdAnimalsResponse response = animalService.getById(animalId);

        // Then
        assertNotNull(response);
        assertEquals(animalId, response.getId());
        verify(animalRepository).findById(animalId);
    }

    @Test
    void updateAnimalTest() {
        // Given
        UpdateAnimalRequest updateAnimalRequest = new UpdateAnimalRequest();
        updateAnimalRequest.setId(1);
        updateAnimalRequest.setName("Kara");

        Animal updatedAnimal = new Animal();
        updatedAnimal.setId(1);
        updatedAnimal.setName("Kara");

        when(modelMapperService.forRequest()).thenReturn(new ModelMapper());

        // When
        animalService.update(updateAnimalRequest);

        // Then
        verify(animalRepository).save(any(Animal.class));
    }

    @Test
    void deleteAnimalTest() {
        // Given
        int animalId = 1;

        // When
        animalService.delete(animalId);

        // Then
        verify(animalRepository).deleteById(animalId);
    }

        @Test
        void getAnimalCountByTypeTest() {
            // Mock the repository response
            Object[] cowCount = {Animal.AnimalType.KEÇİ, 10L};
            Object[] chickenCount = {Animal.AnimalType.TAVUK, 5L};

            List<Object[]> mockedResponse = Arrays.asList(cowCount, chickenCount);
            when(animalRepository.countAnimalsByType()).thenReturn(mockedResponse);

            // Call the service method
            List<GetAnimalCountByTypeResponse> responses = animalService.GetAnimalCountByTypeResponse();

            // Assertions
            assertEquals(2, responses.size(), "There should be two types of animals counted.");

            // Assert the first response
            GetAnimalCountByTypeResponse cowResponse = responses.get(0);
            assertEquals("KEÇİ", cowResponse.getType(), "The first type should be Keçi.");
            assertEquals(10, cowResponse.getCount(), "The count of Keçi should be 10.");

            // Assert the second response
            GetAnimalCountByTypeResponse chickenResponse = responses.get(1);
            assertEquals("TAVUK", chickenResponse.getType(), "The second type should be Tavuk.");
            assertEquals(5, chickenResponse.getCount(), "The count of Tavuk should be 5.");
        }
    @Test
    void getAnimalsByTypeTest() {
        // Given
        String type = "TAVUK";
        Animal animal1 = new Animal();
        animal1.setId(1);
        animal1.setType(Animal.AnimalType.fromString(type));
        animal1.setName("Boncuk");
        List<Animal> animalList = Arrays.asList(animal1);

        when(animalRepository.findByType(Animal.AnimalType.fromString(type))).thenReturn(animalList);
        when(modelMapperService.forResponse()).thenReturn(new ModelMapper());

        // When
        List<GetAnimalDetailResponse> responses = animalService.getAnimalsByType(type);

        // Then
        assertEquals(1, responses.size(), "The size of the response list should be 1.");
        GetAnimalDetailResponse response = responses.get(0);
        assertEquals(animal1.getName(), response.getName(), "The name of the animal should match.");
        assertEquals(animal1.getType().name(), response.getType(), "The type of the animal should match.");
    }




}








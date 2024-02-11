package com.farmSimulation.business.services;

import com.farmSimulation.business.responses.GetAnimalAttributeResponse;
import com.farmSimulation.core.utilities.mappers.ModelMapperService;
import com.farmSimulation.dataAccess.abstracts.AnimalAttributeRepository;
import com.farmSimulation.dataAccess.abstracts.AnimalRepository;
import com.farmSimulation.entities.concretes.AnimalAttribute;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AnimalAttributeServiceImplTest {

    @Mock
    private AnimalRepository animalRepository;

    @Mock
    private AnimalAttributeRepository animalAttributeRepository;

    @Mock
    private ModelMapperService modelMapperService;

    @InjectMocks
    private AnimalAttributeServiceImpl animalAttributeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void getAllAttributesTest() {

        AnimalAttribute attribute1 = new AnimalAttribute();
        attribute1.setId(1L);
        AnimalAttribute attribute2 = new AnimalAttribute();
        attribute2.setId(2L);
        List<AnimalAttribute> attributes = Arrays.asList(attribute1, attribute2);

        when(animalAttributeRepository.findAll()).thenReturn(attributes);
        when(modelMapperService.forResponse()).thenReturn(new ModelMapper());

        List<GetAnimalAttributeResponse> responseList = animalAttributeService.getAllAttributes();

        assertEquals(2, responseList.size());
        verify(animalAttributeRepository).findAll();
    }

    @Test
    void getAttributeByIdTest() {

        Long attributeId = 1L;
        AnimalAttribute attribute = new AnimalAttribute();
        attribute.setId(attributeId);

        when(animalAttributeRepository.findById(attributeId)).thenReturn(Optional.of(attribute));
        when(modelMapperService.forResponse()).thenReturn(new ModelMapper());

        GetAnimalAttributeResponse response = animalAttributeService.getAttributeById(attributeId);

        assertEquals(attributeId, response.getId());
        verify(animalAttributeRepository).findById(attributeId);
    }




}

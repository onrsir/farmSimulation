package com.farmSimulation.core.utilities.mappers;

import com.farmSimulation.business.responses.GetByIdAnimalsResponse;
import com.farmSimulation.entities.concretes.Animal;
import org.modelmapper.ModelMapper;


public interface ModelMapperService {
    ModelMapper forResponse();
    ModelMapper forRequest();

}

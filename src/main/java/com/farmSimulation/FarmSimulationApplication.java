package com.farmSimulation;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.farmSimulation.entities.concretes.Animal.AnimalType;
import com.farmSimulation.business.requests.CreateAnimalRequest;
import com.farmSimulation.entities.concretes.Animal;

@SpringBootApplication
public class FarmSimulationApplication { // abstract kelimesini kaldırdım, bu sınıfın abstract olmaması gerekir.

	public static void main(String[] args) {
		SpringApplication.run(FarmSimulationApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		Converter<String, AnimalType> stringToAnimalTypeConverter = new Converter<String, AnimalType>() {
			@Override
			public AnimalType convert(MappingContext<String, AnimalType> context) {
				return AnimalType.fromString(context.getSource());
			}
		};
		modelMapper.typeMap(CreateAnimalRequest.class, Animal.class).addMappings(mapper ->
				mapper.using(stringToAnimalTypeConverter)
						.map(CreateAnimalRequest::getType, Animal::setType));
		return modelMapper;
	}
}

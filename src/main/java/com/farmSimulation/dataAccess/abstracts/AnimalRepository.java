package com.farmSimulation.dataAccess.abstracts;

import com.farmSimulation.entities.concretes.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface AnimalRepository extends JpaRepository<Animal, Integer> {
    @Query("SELECT a.type, COUNT(a) FROM Animal a GROUP BY a.type")
    List<Object[]> countAnimalsByType();

    List<Animal> findByType(Animal.AnimalType type);

}

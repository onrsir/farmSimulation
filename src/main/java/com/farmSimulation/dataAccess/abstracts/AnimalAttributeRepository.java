package com.farmSimulation.dataAccess.abstracts;

import com.farmSimulation.entities.concretes.AnimalAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalAttributeRepository extends JpaRepository<AnimalAttribute, Long> {
}

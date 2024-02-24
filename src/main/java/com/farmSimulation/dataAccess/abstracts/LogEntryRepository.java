package com.farmSimulation.dataAccess.abstracts;

import com.farmSimulation.entities.concretes.LogEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogEntryRepository extends JpaRepository<LogEntry, Long> {
}

package com.farmSimulation.business.services;

import com.farmSimulation.entities.concretes.LogEntry;
import com.farmSimulation.dataAccess.abstracts.LogEntryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LogEntryServiceImpl implements LogEntryService {
    private final LogEntryRepository logEntryRepository;

    @Override
    public void saveLogEntry(LogEntry logEntry) {
        logEntryRepository.save(logEntry);
    }
}


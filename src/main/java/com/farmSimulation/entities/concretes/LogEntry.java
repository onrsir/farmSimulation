package com.farmSimulation.entities.concretes;
import jakarta.persistence.*;
import org.springframework.boot.logging.LogLevel;

@Entity
@Table(name = "log_entry")

public class LogEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private LogLevel level;

    @Column(length = 2000)
    private String message;

    public LogEntry(String message, LogLevel level) {
        this.message = message;
        this.level = level;
    }

    public LogEntry() {

    }

}

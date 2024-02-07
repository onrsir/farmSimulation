package com.farmSimulation.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Table(name="animals")
@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private AnimalType type;
    public enum AnimalType {
        KEÇİ, KOYUN, TAVUK;

        public static AnimalType fromString(String text) {
            for (AnimalType b : AnimalType.values()) {
                if (b.name().equalsIgnoreCase(text)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("No constant with text " + text + " found");
        }
    }

    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<AnimalAttribute> attributes;


}

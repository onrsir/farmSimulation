package com.farmSimulation.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="animals")
@Data // get set boş constuctor
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



    @Enumerated(EnumType.STRING) // Enum tipini belirtiyoruz
    @Column(name = "type")
    private AnimalType type;

    public enum AnimalType {
        Keçi, Koyun, Tavuk,
        keçi, koyun, tavuk
    }

}

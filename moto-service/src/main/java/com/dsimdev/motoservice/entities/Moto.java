package com.dsimdev.motoservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



@Entity
@Getter
@Setter
@ToString
public class Moto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String marca;

    private String modelo;

    private int usuarioId;

}

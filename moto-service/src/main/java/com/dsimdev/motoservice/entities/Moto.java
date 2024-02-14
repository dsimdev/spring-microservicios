package com.dsimdev.motoservice.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

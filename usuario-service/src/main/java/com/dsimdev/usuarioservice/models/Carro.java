package com.dsimdev.usuarioservice.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Carro {

    private String marca;
    private String modelo;
    private int usuarioId;
}

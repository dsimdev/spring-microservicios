package com.dsimdev.carroservice.controllers;

import com.dsimdev.carroservice.entities.Carro;
import com.dsimdev.carroservice.services.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carros")
public class CarroController {

    @Autowired
    private CarroService carroService;

    @GetMapping
    public ResponseEntity<List<Carro>> listCarros () {
        List<Carro> carros = carroService.getAllCarros();
        return ResponseEntity.ok(carros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carro> getCarro (@PathVariable ("id") int carroId){
        Carro carro = carroService.getCarroById(carroId);
        return ResponseEntity.ok(carro);
    }

    @PostMapping
    public ResponseEntity<Carro> saveCarro (@RequestBody Carro carro) {
        Carro newCarro = carroService.saveCarro(carro);
        return ResponseEntity.ok(newCarro);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Carro>> listCarrosByUsuarioId (@PathVariable ("usuarioId") int usuarioId){
        List<Carro> carros = carroService.getCarrosByUsuarioId(usuarioId);
        if(carros.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(carros);
    }


}

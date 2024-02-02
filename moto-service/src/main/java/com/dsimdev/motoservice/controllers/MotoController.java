package com.dsimdev.motoservice.controllers;

import com.dsimdev.motoservice.entities.Moto;
import com.dsimdev.motoservice.services.MotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/motos")
public class MotoController {

    @Autowired
    private MotoService motoService;

    @GetMapping
    public ResponseEntity<List<Moto>> listMotos () {
        List<Moto> motos = motoService.getAllMotos();
        return ResponseEntity.ok(motos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Moto> getMoto (@PathVariable("id") int motoId){
        Moto moto = motoService.getMotoById(motoId);
        return ResponseEntity.ok(moto);
    }

    @PostMapping
    public ResponseEntity<Moto> saveMoto (@RequestBody Moto moto) {
        Moto newMoto = motoService.saveMoto(moto);
        return ResponseEntity.ok(newMoto);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Moto>> listMotosByUsuarioId (@PathVariable ("usuarioId") int usuarioId){
        List<Moto> motos = motoService.getMotosByUsuarioId(usuarioId);
        if(motos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(motos);
    }

}

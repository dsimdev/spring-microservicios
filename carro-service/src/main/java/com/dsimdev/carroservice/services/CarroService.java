package com.dsimdev.carroservice.services;

import com.dsimdev.carroservice.entities.Carro;
import com.dsimdev.carroservice.repositories.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepo;

    public List<Carro> getAllCarros (){
        return carroRepo.findAll();
    }

    public Carro getCarroById (int carroId) {
        return carroRepo.findById(carroId).orElse(null);
    }

    public Carro saveCarro (Carro carro){
        Carro newCarro = carroRepo.save(carro);
        return newCarro;
    }

    public List<Carro> getCarrosByUsuarioId (int usuarioId){
        return carroRepo.findByUsuarioId(usuarioId);
    }

}

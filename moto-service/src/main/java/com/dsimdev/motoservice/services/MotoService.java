package com.dsimdev.motoservice.services;

import com.dsimdev.motoservice.entities.Moto;
import com.dsimdev.motoservice.repositories.MotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoService {

    @Autowired
    private MotoRepository motoRepo;

    public List<Moto> getAllMotos (){
        return motoRepo.findAll();
    }

    public Moto getMotoById (int motoId){
        return motoRepo.findById(motoId).orElse(null);
    }

    public Moto saveMoto (Moto moto){
        Moto newMoto = motoRepo.save(moto);
        return newMoto;
    }

    public List<Moto> getMotosByUsuarioId(int usuarioId){
        return motoRepo.findByUsuarioId(usuarioId);
    }

}

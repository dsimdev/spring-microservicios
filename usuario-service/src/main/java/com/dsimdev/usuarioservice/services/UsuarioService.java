package com.dsimdev.usuarioservice.services;

import com.dsimdev.usuarioservice.entities.Usuario;
import com.dsimdev.usuarioservice.feignclients.CarroFeignClient;
import com.dsimdev.usuarioservice.feignclients.MotoFeignClient;
import com.dsimdev.usuarioservice.models.Carro;
import com.dsimdev.usuarioservice.models.Moto;
import com.dsimdev.usuarioservice.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository userRepo;

    @Autowired
    private CarroFeignClient carroFeignClient;

    @Autowired
    MotoFeignClient motoFeignClient;

    public List<Usuario> getAll() {
        return userRepo.findAll();
    }

    public Usuario getUserById(int id) {
        return userRepo.findById(id).orElse(null);
    }

    public Usuario save(Usuario usuario) {
        Usuario newUsuario = userRepo.save(usuario);
        return newUsuario;
    }

    // FEIGN CLIENTS
    public Carro saveCarro(int usuarioId, Carro carro) {
        carro.setUsuarioId(usuarioId);
        Carro newCarro = carroFeignClient.save(carro);
        return newCarro;
    }

    public List<Carro> getCarros(int usuarioId) {
        List<Carro> carros = carroFeignClient.getAllCarros(usuarioId);
        return carros;
    }

    public Moto saveMoto(int usuarioId, Moto moto) {
        moto.setUsuarioId(usuarioId);
        Moto newMoto = motoFeignClient.save(moto);
        return newMoto;
    }

    public List<Moto> getMotos(int usuarioId) {
        List<Moto> motos = motoFeignClient.getAllMotos(usuarioId);
        return motos;
    }

    public Map<String, Object> getUsuarioVehiculos(int usuarioId) {
        Map<String, Object> result = new HashMap<>();
        Usuario usuario = userRepo.findById(usuarioId).orElse(null);

        if (usuario == null) {
            result.put("Mensaje", "El usuario no existe");
            return result;
        }

        result.put("Usuario", usuario);

        List<Carro> carros = carroFeignClient.getAllCarros(usuarioId);
        if (carros == null) {
            result.put("Carros", "No se pudieron recuperar los carros del usuario");
        } else if (carros.isEmpty()) {
            result.put("Carros", "El usuario no tiene carros");
        } else {
            result.put("Carros", carros);
        }

        List<Moto> motos = motoFeignClient.getAllMotos(usuarioId);
        if (motos == null) {
            result.put("Motos", "No se pudieron recuperar las motos del usuario");
        } else if (motos.isEmpty()) {
            result.put("Motos", "El usuario no tiene motos");
        } else {
            result.put("Motos", motos);
        }

        return result;
    }
}

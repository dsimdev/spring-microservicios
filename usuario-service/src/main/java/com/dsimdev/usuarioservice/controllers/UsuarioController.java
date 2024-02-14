package com.dsimdev.usuarioservice.controllers;

import com.dsimdev.usuarioservice.entities.Usuario;
import com.dsimdev.usuarioservice.models.Carro;
import com.dsimdev.usuarioservice.models.Moto;
import com.dsimdev.usuarioservice.services.UsuarioService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> listUsuarios() {
        List<Usuario> usuarios = usuarioService.getAll();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable("id") int id) {
        Usuario usuario = usuarioService.getUserById(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<Usuario> saveUsuario(@RequestBody Usuario usuario) {
        Usuario newUsuario = usuarioService.save(usuario);
        return ResponseEntity.ok(newUsuario);
    }


    //FEIGN CLIENT
    @CircuitBreaker(name = "carrosCB", fallbackMethod = "fallBackSaveCarro")
    @PostMapping("/carros/{usuarioId}")
    public ResponseEntity<Carro> saveCarro(@PathVariable("usuarioId") int usuarioId, @RequestBody Carro carro) {
        Carro newCarro = usuarioService.saveCarro(usuarioId, carro);
        return ResponseEntity.ok(newCarro);
    }

    @CircuitBreaker(name = "carrosCB", fallbackMethod = "fallBackGetCarros")
    @GetMapping("/carros/{usuarioId}")
    public ResponseEntity<List<Carro>> getCarros(@PathVariable("usuarioId") int usuarioId) {
        List<Carro> carros = usuarioService.getCarros(usuarioId);
        return ResponseEntity.ok(carros);
    }

    @CircuitBreaker(name = "motosCB", fallbackMethod = "fallBackSaveMoto")
    @PostMapping("/motos/{usuarioId}")
    public ResponseEntity<Moto> saveMoto(@PathVariable("usuarioId") int usuarioId, @RequestBody Moto moto) {
        Moto newMoto = usuarioService.saveMoto(usuarioId, moto);
        return ResponseEntity.ok(newMoto);
    }

    @CircuitBreaker(name = "motosCB", fallbackMethod = "fallBackGetMotos")
    @GetMapping("/motos/{usuarioId}")
    public ResponseEntity<List<Moto>> getMotos(@PathVariable("usuarioId") int usuarioId) {
        List<Moto> motos = usuarioService.getMotos(usuarioId);
        return ResponseEntity.ok(motos);
    }


    @CircuitBreaker(name = "todosCB", fallbackMethod = "fallBackGetVehiculos")
    @GetMapping("/vehiculos/{usuarioId}")
    public ResponseEntity<Map<String, Object>> getAllVehicles(@PathVariable("usuarioId") int usuarioId) {
        Map<String, Object> result = usuarioService.getUsuarioVehiculos(usuarioId);
        return ResponseEntity.ok(result);
    }

    private ResponseEntity<List<Carro>> fallBackGetCarros(@PathVariable("usuarioId") int usuarioId, RuntimeException ex) {
        return new ResponseEntity("El usuario: " + usuarioId + "tiene los autos en el taller", HttpStatus.OK);
    }

    private ResponseEntity<List<Carro>> fallBackSaveCarro(@PathVariable("usuarioId") int usuarioId, @RequestBody Carro carro, RuntimeException ex) {
        return new ResponseEntity("El usuario: " + usuarioId + " no tiene dinero para los autos", HttpStatus.OK);
    }

    private ResponseEntity<List<Moto>> fallBackGetMotos(@PathVariable("usuarioId") int usuarioId, RuntimeException ex) {
        return new ResponseEntity("El usuario: " + usuarioId + "tiene las motos en el taller", HttpStatus.OK);
    }

    private ResponseEntity<List<Moto>> fallBackSaveMoto(@PathVariable("usuarioId") int usuarioId, @RequestBody Moto moto, RuntimeException ex) {
        return new ResponseEntity("El usuario: " + usuarioId + " no tiene dinero para las motos", HttpStatus.OK);
    }

    private ResponseEntity<Map<String, Object>> fallBackGetVehiculos(@PathVariable("usuarioId") int usuarioId, RuntimeException ex) {
        return new ResponseEntity("El usuario: " + usuarioId + "tiene todos los vehiculos en el taller", HttpStatus.OK);
    }
}

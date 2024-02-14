package com.dsimdev.usuarioservice.feignclients;

import com.dsimdev.usuarioservice.models.Carro;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "carro-service")
public interface CarroFeignClient {

    @PostMapping("/carros")
    public Carro save(@RequestBody Carro carro);

    @GetMapping("/carros/usuario/{usuarioId}")
    public List<Carro> getAllCarros(@PathVariable("usuarioId") int usuarioId);
}

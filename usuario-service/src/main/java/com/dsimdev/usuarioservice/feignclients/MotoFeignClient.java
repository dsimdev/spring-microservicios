package com.dsimdev.usuarioservice.feignclients;

import com.dsimdev.usuarioservice.models.Moto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "moto-service")
public interface MotoFeignClient {

    @PostMapping("/motos")
    public Moto save(@RequestBody Moto moto);

    @GetMapping("/motos/usuario/{usuarioId}")
    public List<Moto> getAllMotos(@PathVariable("usuarioId") int usuarioId);
}

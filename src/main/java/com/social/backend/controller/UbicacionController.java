package com.social.backend.controller;

import com.social.backend.model.Ubicacion;
import com.social.backend.service.UbicacionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ubicaciones")
public class UbicacionController {

    private final UbicacionService ubicacionService;

    public UbicacionController(UbicacionService ubicacionService) {
        this.ubicacionService = ubicacionService;
    }

    @GetMapping("/por-ubi-codubica")
    public List<Ubicacion> obtenerPorUbiCodUbica(@RequestParam String ubiCodUbica) {
        return ubicacionService.obtenerPorUbiCodUbica(ubiCodUbica);
    }

}


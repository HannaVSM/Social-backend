package com.social.backend.controller;

import com.social.backend.dto.GrupoCrearDTO;
import com.social.backend.model.Grupo;
import com.social.backend.service.GrupoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grupos")
public class GrupoController {

    private final GrupoService grupoService;

    public GrupoController(GrupoService grupoService) {
        this.grupoService = grupoService;
    }

    @GetMapping("/usuario/{consecUser}")
    public List<Grupo> obtenerGruposPorUsuario(@PathVariable String consecUser) {
        return grupoService.obtenerGruposPorUsuario(consecUser);
    }

    @PostMapping("/crear")
    public ResponseEntity<String> crearGrupo(@RequestBody GrupoCrearDTO dto) {
        grupoService.crearGrupo(dto);
        return ResponseEntity.ok("Grupo creado correctamente.");
    }
}


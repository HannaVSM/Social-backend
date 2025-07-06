package com.social.backend.controller;

import com.social.backend.model.Amigo;
import com.social.backend.service.AmigoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/amigos")
public class AmigoController {

    @Autowired
    private AmigoService amigoService;

    @GetMapping("/usuario/{consecUser}")
    public ResponseEntity<List<Amigo>> obtenerAmigos(@PathVariable String consecUser) {
        List<Amigo> amigos = amigoService.obtenerAmigosDe(consecUser);
        return ResponseEntity.ok(amigos);
    }

    @PostMapping
    public ResponseEntity<String> crearAmigo(@RequestBody Amigo amigo) {
        amigoService.crearAmigo(amigo.getConsecUser(), amigo.getConsecUser2());
        return ResponseEntity.ok("Amigo creado correctamente.");
    }
}

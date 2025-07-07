package com.social.backend.controller;

import com.social.backend.model.Mensaje;
import com.social.backend.service.MensajeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mensajes")
public class MensajeController {

    private final MensajeService mensajeService;

    public MensajeController(MensajeService mensajeService) {
        this.mensajeService = mensajeService;
    }

    @GetMapping("/{user1}/{user2}")
    public ResponseEntity<List<Mensaje>> obtenerMensajesEntreUsuarios(
            @PathVariable String user1,
            @PathVariable String user2) {
        List<Mensaje> mensajes = mensajeService.obtenerMensajesEntre(user1, user2);
        return ResponseEntity.ok(mensajes);
    }

    @PostMapping
    public ResponseEntity<Mensaje> crearMensaje(@RequestBody Mensaje mensaje) {
        Mensaje nuevo = mensajeService.guardarMensaje(mensaje);
        return ResponseEntity.ok(nuevo);
    }
}


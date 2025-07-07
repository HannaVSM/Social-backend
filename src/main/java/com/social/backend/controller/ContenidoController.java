package com.social.backend.controller;

import com.social.backend.dto.MensajeConContenidoDTO;
import com.social.backend.model.Contenido;
import com.social.backend.service.ContenidoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contenidos")
public class ContenidoController {

    private final ContenidoService contenidoService;

    public ContenidoController(ContenidoService contenidoService) {
        this.contenidoService = contenidoService;
    }

    @GetMapping("/por-mensaje")
    public List<Contenido> obtenerPorMensaje(
            @RequestParam String consecUser,
            @RequestParam String usuConsecUser,
            @RequestParam Long consMesaje
    ) {
        return contenidoService.obtenerContenidosPorMensaje(consecUser, usuConsecUser, consMesaje);
    }

    @GetMapping("/mensaje-con-contenido")
    public MensajeConContenidoDTO obtenerMensajeConContenido(
            @RequestParam String consecUser,
            @RequestParam String usuConsecUser,
            @RequestParam Long consMesaje
    ) {
        return contenidoService.obtenerMensajeConContenido(consecUser, usuConsecUser, consMesaje);
    }
    
    @GetMapping("/conversacion")
    public List<MensajeConContenidoDTO> obtenerConversacion(
            @RequestParam String user1,
            @RequestParam String user2
    ) {
        return contenidoService.obtenerConversacion(user1, user2);
    }

}


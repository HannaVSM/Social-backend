package com.social.backend.service;

import com.social.backend.model.Mensaje;
import com.social.backend.repository.MensajeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MensajeService {

    private final MensajeRepository mensajeRepository;

    public MensajeService(MensajeRepository mensajeRepository) {
        this.mensajeRepository = mensajeRepository;
    }

    public List<Mensaje> obtenerMensajesEntre(String user1, String user2) {
        return mensajeRepository.findMensajesEntreUsuarios(user1, user2);
    }

    public Mensaje guardarMensaje(Mensaje mensaje) {
        return mensajeRepository.save(mensaje);
    }
}


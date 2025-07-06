package com.social.backend.service;

import com.social.backend.model.Usuario;
import com.social.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario guardar(Usuario usuario) {
        String email = usuario.getEmail().toLowerCase();
        String celular = usuario.getCelular();

        if (usuarioRepository.existsByEmailIgnoreCase(email)) {
            throw new IllegalArgumentException("Ya existe un usuario con ese email.");
        }

        if (usuarioRepository.existsByCelular(celular)) {
            throw new IllegalArgumentException("Ya existe un usuario con ese celular.");
        }

        usuario.setEmail(email); // se guarda ya en minúsculas
        usuario.setConsecUser(generarNuevoId());
        return usuarioRepository.save(usuario);
    }


    private String generarNuevoId() {
        Optional<Usuario> ultimo = usuarioRepository.findTopByOrderByConsecUserDesc();
        if (ultimo.isPresent()) {
            try {
                int num = Integer.parseInt(ultimo.get().getConsecUser());
                return String.format("%03d", num + 1);
            } catch (NumberFormatException e) {
                throw new RuntimeException("El ID actual no es numérico");
            }
        } else {
            return "001";
        }
    }
}

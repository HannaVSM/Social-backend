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
        String nuevoId = generarNuevoId();
        usuario.setConsecUser(nuevoId);
        return usuarioRepository.save(usuario);
    }

    private String generarNuevoId() {
        Optional<Usuario> ultimo = usuarioRepository.findTopByOrderByConsecUserDesc();
        if (ultimo.isPresent()) {
            try {
                int num = Integer.parseInt(ultimo.get().getConsecUser());
                return String.valueOf(num + 1);
            } catch (NumberFormatException e) {
                throw new RuntimeException("El ID actual no es numérico");
            }
        } else {
            return "1";
        }
    }
}

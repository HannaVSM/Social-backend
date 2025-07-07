package com.social.backend.service;

import com.social.backend.model.Usuario;
import com.social.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
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

        if (!usuarioRepository.buscarPorEmail(usuario.getEmail().toLowerCase()).isEmpty()) {
            throw new IllegalArgumentException("Ya existe un usuario con ese email.");
        }

        if (!usuarioRepository.buscarPorCelular(usuario.getCelular()).isEmpty()) {
            throw new IllegalArgumentException("Ya existe un usuario con ese celular.");
        }

        usuario.setEmail(email);
        usuario.setConsecUser(generarNuevoId());
        return usuarioRepository.save(usuario);
    }

    private String generarNuevoId() {
        Optional<Usuario> ultimo = usuarioRepository.buscarUltimoUsuario();
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

    public Usuario loginPorEmailYCelular(String email, String celular) {
        return usuarioRepository
                .buscarPorEmailYCelular(email.toLowerCase(), celular)
                .orElseThrow(() -> new IllegalArgumentException("Credenciales inválidas."));
    }

    public Usuario obtenerPorCelular(String celular) {
        return usuarioRepository.buscarUsuarioPorCelular(celular)
                .orElseThrow(() -> new NoSuchElementException("No se encontró un usuario con ese celular."));
    }

    public List<Usuario> obtenerAmigos(String consecUser) {
        return usuarioRepository.buscarAmigosDeUsuario(consecUser);
    }


}

package com.social.backend.repository;

import com.social.backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Optional<Usuario> findTopByOrderByConsecUserDesc();
    boolean existsByEmailIgnoreCase(String email);

    boolean existsByCelular(String celular);

    Optional<Usuario> findByEmailIgnoreCaseAndCelular(String email, String celular);
}


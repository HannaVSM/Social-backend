package com.social.backend.repository;

import com.social.backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    // Último ID (Oracle: FETCH FIRST 1 ROWS ONLY)
    @Query(value = "SELECT * FROM usuario ORDER BY consecuser DESC FETCH FIRST 1 ROWS ONLY", nativeQuery = true)
    Optional<Usuario> buscarUltimoUsuario();

    // Verificar si ya hay un email
    @Query(value = "SELECT * FROM usuario WHERE LOWER(email) = LOWER(:email)", nativeQuery = true)
    List<Usuario> buscarPorEmail(@Param("email") String email);


    // Verificar si ya hay un celular
    @Query(value = "SELECT * FROM usuario WHERE celular = :celular", nativeQuery = true)
    List<Usuario> buscarPorCelular(@Param("celular") String celular);

    // Login por email y celular
    @Query(value = "SELECT * FROM usuario WHERE LOWER(email) = LOWER(:email) AND celular = :celular", nativeQuery = true)
    Optional<Usuario> buscarPorEmailYCelular(@Param("email") String email, @Param("celular") String celular);

    @Query(value = "SELECT * FROM usuario WHERE celular = :celular", nativeQuery = true)
    Optional<Usuario> buscarUsuarioPorCelular(@Param("celular") String celular);

    @Query(value = """
    SELECT DISTINCT u.*
    FROM usuario u
    WHERE u.consecuser IN (
        SELECT CASE
                 WHEN a.consecuser = :id THEN a.consecuser2
                 ELSE a.consecuser
               END
        FROM amigo a
        WHERE a.consecuser = :id OR a.consecuser2 = :id
    )
""", nativeQuery = true)
    List<Usuario> buscarAmigosDeUsuario(@Param("id") String consecUser);

}


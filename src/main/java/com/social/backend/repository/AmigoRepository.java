package com.social.backend.repository;

import com.social.backend.model.Amigo;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmigoRepository extends JpaRepository<Amigo, Long> {

    // Obtener los amigos de un usuario
    @Query(value = "SELECT * FROM amigo WHERE consecuser = :id", nativeQuery = true)
    List<Amigo> buscarAmigosDe(@Param("id") String consecUser);

    // Insertar un nuevo amigo
    @Modifying
    @Query(value = "INSERT INTO amigo (consecuser, consecuser2) VALUES (:usuario, :amigo)", nativeQuery = true)
    void insertarAmigo(@Param("usuario") String consecUser, @Param("amigo") String consecUser2);
}


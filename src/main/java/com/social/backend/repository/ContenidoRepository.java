package com.social.backend.repository;

import com.social.backend.model.Contenido;
import com.social.backend.model.ContenidoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContenidoRepository extends JpaRepository<Contenido, ContenidoId> {

    @Query(value = """
        SELECT c FROM Contenido c 
        WHERE c.id.consecUser = :consecUser 
          AND c.id.usuConsecUser = :usuConsecUser 
          AND c.id.consMesaje = :consMesaje
    """)
    List<Contenido> findByMensaje(String consecUser, String usuConsecUser, Long consMesaje);
}


package com.social.backend.repository;

import com.social.backend.model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {

    @Query(value = """
        SELECT g.*
        FROM grupo g
        JOIN pertenece p ON g.codgrupo = p.codgrupo2
        WHERE p.consecuser2 = :consecUser
    """, nativeQuery = true)
    List<Grupo> findGruposByUsuario(@Param("consecUser") String consecUser);

    @Query("SELECT MAX(g.codGrupo) FROM Grupo g")
    Optional<Long> findMaxCodGrupo();
}

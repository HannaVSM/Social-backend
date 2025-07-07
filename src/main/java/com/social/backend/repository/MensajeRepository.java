package com.social.backend.repository;

import com.social.backend.model.Mensaje;
import com.social.backend.model.MensajeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MensajeRepository extends JpaRepository<Mensaje, MensajeId> {

    @Query(value = """
        SELECT * FROM mensaje m 
        WHERE (m.consecuser = :user1 AND m.usu_consecuser = :user2) 
           OR (m.consecuser = :user2 AND m.usu_consecuser = :user1)
        ORDER BY m.fecharegmen
    """, nativeQuery = true)
    List<Mensaje> findMensajesEntreUsuarios(String user1, String user2);

}


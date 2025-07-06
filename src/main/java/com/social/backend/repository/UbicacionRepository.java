package com.social.backend.repository;

import com.social.backend.model.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UbicacionRepository extends JpaRepository<Ubicacion, String> {

    @Query(value = "SELECT * FROM ubicacion WHERE ubi_codubica = :ubiCodUbica", nativeQuery = true)
    List<Ubicacion> buscarPorUbiCodUbica(@Param("ubiCodUbica") String ubiCodUbica);

}

package com.social.backend.repository;

import com.social.backend.model.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UbicacionRepository extends JpaRepository<Ubicacion, String> {
    List<Ubicacion> findByUbiCodUbica(String ubiCodUbica);
}

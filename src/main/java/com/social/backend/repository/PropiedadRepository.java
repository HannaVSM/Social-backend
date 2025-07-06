package com.social.backend.repository;

import com.social.backend.model.Propiedad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropiedadRepository extends JpaRepository<Propiedad, String> {
}

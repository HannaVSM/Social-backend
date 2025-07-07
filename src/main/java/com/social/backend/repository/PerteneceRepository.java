package com.social.backend.repository;

import com.social.backend.model.Pertenece;
import com.social.backend.model.PerteneceId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerteneceRepository extends JpaRepository<Pertenece, PerteneceId> {}


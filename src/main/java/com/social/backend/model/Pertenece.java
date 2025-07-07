package com.social.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pertenece")
public class Pertenece {

    @EmbeddedId
    private PerteneceId id;

    // Getters y Setters
    public PerteneceId getId() {
        return id;
    }

    public void setId(PerteneceId id) {
        this.id = id;
    }
}


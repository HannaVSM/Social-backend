package com.social.backend.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MensajeId implements Serializable {

    @Column(name = "consecuser", nullable = false, length = 5)
    private String consecUser;

    @Column(name = "usu_consecuser", nullable = false, length = 5)
    private String usuConsecUser;

    @Column(name = "consmesaje", nullable = false)
    private Long consMesaje;

    // Getters y setters

    public String getConsecUser() {
        return consecUser;
    }

    public void setConsecUser(String consecUser) {
        this.consecUser = consecUser;
    }

    public String getUsuConsecUser() {
        return usuConsecUser;
    }

    public void setUsuConsecUser(String usuConsecUser) {
        this.usuConsecUser = usuConsecUser;
    }

    public Long getConsMesaje() {
        return consMesaje;
    }

    public void setConsMesaje(Long consMesaje) {
        this.consMesaje = consMesaje;
    }

    // equals y hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MensajeId that)) return false;
        return Objects.equals(consecUser, that.consecUser)
                && Objects.equals(usuConsecUser, that.usuConsecUser)
                && Objects.equals(consMesaje, that.consMesaje);
    }

    @Override
    public int hashCode() {
        return Objects.hash(consecUser, usuConsecUser, consMesaje);
    }
}


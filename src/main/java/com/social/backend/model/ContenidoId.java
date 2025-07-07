package com.social.backend.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ContenidoId implements Serializable {

    @Column(name = "consecuser", length = 5, nullable = false)
    private String consecUser;

    @Column(name = "usu_consecuser", length = 5, nullable = false)
    private String usuConsecUser;

    @Column(name = "consmesaje", nullable = false)
    private Long consMesaje;

    @Column(name = "consecontenido", nullable = false)
    private Long conseContenido;

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

    public Long getConseContenido() {
        return conseContenido;
    }

    public void setConseContenido(Long conseContenido) {
        this.conseContenido = conseContenido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContenidoId that)) return false;
        return Objects.equals(consecUser, that.consecUser)
                && Objects.equals(usuConsecUser, that.usuConsecUser)
                && Objects.equals(consMesaje, that.consMesaje)
                && Objects.equals(conseContenido, that.conseContenido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(consecUser, usuConsecUser, consMesaje, conseContenido);
    }
}

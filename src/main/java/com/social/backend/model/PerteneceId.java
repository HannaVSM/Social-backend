package com.social.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PerteneceId implements Serializable {

    @Column(name = "codgrupo2", nullable = false)
    private Long codGrupo;

    @Column(name = "consecuser2", nullable = false, length = 5)
    private String consecUser;

    public Long getCodGrupo() { return codGrupo; }
    public void setCodGrupo(Long codGrupo) { this.codGrupo = codGrupo; }

    public String getConsecUser() { return consecUser; }
    public void setConsecUser(String consecUser) { this.consecUser = consecUser; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PerteneceId that)) return false;
        return Objects.equals(codGrupo, that.codGrupo) &&
                Objects.equals(consecUser, that.consecUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codGrupo, consecUser);
    }
}


package com.social.backend.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "grupo")
public class Grupo {

    @Id
    @Column(name = "codgrupo")
    private Long codGrupo;

    @Column(name = "gru_codgrupo")
    private Long gruCodGrupo;

    @Column(name = "consecuser", length = 5)
    private String consecUser;

    @Column(name = "nomgrupo", length = 30)
    private String nomGrupo;

    @Temporal(TemporalType.DATE)
    @Column(name = "fechareggrupo", nullable = false)
    private Date fechaRegGrupo;

    @Lob
    @Column(name = "imaggrupo", nullable = false)
    private byte[] imagGrupo;

    // Getters y Setters

    public Long getCodGrupo() {
        return codGrupo;
    }

    public void setCodGrupo(Long codGrupo) {
        this.codGrupo = codGrupo;
    }

    public Long getGruCodGrupo() {
        return gruCodGrupo;
    }

    public void setGruCodGrupo(Long gruCodGrupo) {
        this.gruCodGrupo = gruCodGrupo;
    }

    public String getConsecUser() {
        return consecUser;
    }

    public void setConsecUser(String consecUser) {
        this.consecUser = consecUser;
    }

    public String getNomGrupo() {
        return nomGrupo;
    }

    public void setNomGrupo(String nomGrupo) {
        this.nomGrupo = nomGrupo;
    }

    public Date getFechaRegGrupo() {
        return fechaRegGrupo;
    }

    public void setFechaRegGrupo(Date fechaRegGrupo) {
        this.fechaRegGrupo = fechaRegGrupo;
    }

    public byte[] getImagGrupo() {
        return imagGrupo;
    }

    public void setImagGrupo(byte[] imagGrupo) {
        this.imagGrupo = imagGrupo;
    }
}


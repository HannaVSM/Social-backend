package com.social.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class MensajeConContenidoDTO {
    @JsonProperty("remitente")
    private String consecUser;

    @JsonProperty("destinatario")
    private String usuConsecUser;
    private Long consMensaje;
    private Long codGrupo;
    private Long menConMensaje;
    private Date fechaRegMen;
    private List<ContenidoDTO> contenidos;

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

    public Long getConsMensaje() {
        return consMensaje;
    }

    public void setConsMensaje(Long consMensaje) {
        this.consMensaje = consMensaje;
    }

    public Long getCodGrupo() {
        return codGrupo;
    }

    public void setCodGrupo(Long codGrupo) {
        this.codGrupo = codGrupo;
    }

    public Long getMenConMensaje() {
        return menConMensaje;
    }

    public void setMenConMensaje(Long menConMensaje) {
        this.menConMensaje = menConMensaje;
    }

    public Date getFechaRegMen() {
        return fechaRegMen;
    }

    public void setFechaRegMen(Date fechaRegMen) {
        this.fechaRegMen = fechaRegMen;
    }

    public List<ContenidoDTO> getContenidos() {
        return contenidos;
    }

    public void setContenidos(List<ContenidoDTO> contenidos) {
        this.contenidos = contenidos;
    }
}


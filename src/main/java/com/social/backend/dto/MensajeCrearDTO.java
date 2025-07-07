package com.social.backend.dto;

public class MensajeCrearDTO {
    private String consecUser;
    private String usuConsecUser;
    private Long codGrupo; // Puede ser null
    private String idTipoContenido;
    private String idTipoArchivo;
    private String localizaContenido;

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

    public String getIdTipoContenido() {
        return idTipoContenido;
    }

    public void setIdTipoContenido(String idTipoContenido) {
        this.idTipoContenido = idTipoContenido;
    }

    public Long getCodGrupo() {
        return codGrupo;
    }

    public void setCodGrupo(Long codGrupo) {
        this.codGrupo = codGrupo;
    }

    public String getIdTipoArchivo() {
        return idTipoArchivo;
    }

    public void setIdTipoArchivo(String idTipoArchivo) {
        this.idTipoArchivo = idTipoArchivo;
    }

    public String getLocalizaContenido() {
        return localizaContenido;
    }

    public void setLocalizaContenido(String localizaContenido) {
        this.localizaContenido = localizaContenido;
    }
}


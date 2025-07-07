package com.social.backend.dto;

public class ContenidoDTO {
    private Long conseContenido;
    private String idTipoContenido;
    private String idTipoArchivo;
    private String localizaContenido;

    // Getters y setters

    public Long getConseContenido() {
        return conseContenido;
    }

    public void setConseContenido(Long conseContenido) {
        this.conseContenido = conseContenido;
    }

    public String getIdTipoContenido() {
        return idTipoContenido;
    }

    public void setIdTipoContenido(String idTipoContenido) {
        this.idTipoContenido = idTipoContenido;
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


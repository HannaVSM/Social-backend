package com.social.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "contenido")
public class Contenido {

    @EmbeddedId
    private ContenidoId id;

    @Column(name = "idtipocontenido", length = 2)
    private String idTipoContenido;

    @Column(name = "idtipoarchivo", length = 2)
    private String idTipoArchivo;

    @Lob
    @Column(name = "contenidoimag", nullable = false)
    private byte[] contenidoImag;

    @Column(name = "localizacontenido", length = 255)
    private String localizaContenido;

    // Getters y setters

    public ContenidoId getId() {
        return id;
    }

    public void setId(ContenidoId id) {
        this.id = id;
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

    public byte[] getContenidoImag() {
        return contenidoImag;
    }

    public void setContenidoImag(byte[] contenidoImag) {
        this.contenidoImag = contenidoImag;
    }

    public String getLocalizaContenido() {
        return localizaContenido;
    }

    public void setLocalizaContenido(String localizaContenido) {
        this.localizaContenido = localizaContenido;
    }
}


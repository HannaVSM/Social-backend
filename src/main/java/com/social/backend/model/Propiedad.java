package com.social.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "PROPIEDAD")
public class Propiedad {

    @Id
    @Column(name = "IDPROPIEDAD")
    private String idPropiedad;

    @Column(name = "PRO_IDPROPIEDAD")
    private String proIdPropiedad;

    @Column(name = "DESCPROPIEDAD")
    private String descPropiedad;

    @Column(name = "VALORDEFECTO")
    private Long valorDefecto;

    @Column(name = "VALORPROPIEDAD")
    private String valorPropiedad;

    // Getters y Setters

    public String getIdPropiedad() {
        return idPropiedad;
    }

    public void setIdPropiedad(String idPropiedad) {
        this.idPropiedad = idPropiedad;
    }

    public String getProIdPropiedad() {
        return proIdPropiedad;
    }

    public void setProIdPropiedad(String proIdPropiedad) {
        this.proIdPropiedad = proIdPropiedad;
    }

    public String getDescPropiedad() {
        return descPropiedad;
    }

    public void setDescPropiedad(String descPropiedad) {
        this.descPropiedad = descPropiedad;
    }

    public Long getValorDefecto() {
        return valorDefecto;
    }

    public void setValorDefecto(Long valorDefecto) {
        this.valorDefecto = valorDefecto;
    }

    public String getValorPropiedad() {
        return valorPropiedad;
    }

    public void setValorPropiedad(String valorPropiedad) {
        this.valorPropiedad = valorPropiedad;
    }
}



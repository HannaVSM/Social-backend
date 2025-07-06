package com.social.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "UBICACION")
public class Ubicacion {

    @Id
    @Column(name = "CODUBICA", length = 4, nullable = false)
    private String codUbica;

    @Column(name = "CODTIPOUBICA", length = 3, nullable = false)
    private String codTipoUbica;

    @Column(name = "UBI_CODUBICA", length = 4)
    private String ubiCodUbica;

    @Column(name = "NOMUBICA", length = 30, nullable = false)
    private String nomUbica;

    // Getters y setters
    public String getCodUbica() {
        return codUbica;
    }

    public void setCodUbica(String codUbica) {
        this.codUbica = codUbica;
    }

    public String getCodTipoUbica() {
        return codTipoUbica;
    }

    public void setCodTipoUbica(String codTipoUbica) {
        this.codTipoUbica = codTipoUbica;
    }

    public String getUbiCodUbica() {
        return ubiCodUbica;
    }

    public void setUbiCodUbica(String ubiCodUbica) {
        this.ubiCodUbica = ubiCodUbica;
    }

    public String getNomUbica() {
        return nomUbica;
    }

    public void setNomUbica(String nomUbica) {
        this.nomUbica = nomUbica;
    }
}



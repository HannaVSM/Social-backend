package com.social.backend.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "mensaje")
public class Mensaje {

    @EmbeddedId
    private MensajeId id;

    @Column(name = "codgrupo")
    private Long codGrupo;

    @Column(name = "men_conmesaje")
    private Long menConMesaje;

    @Column(name = "fecharegmen", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaRegMen;

    public MensajeId getId() {
        return id;
    }

    public void setId(MensajeId id) {
        this.id = id;
    }

    public Long getCodGrupo() {
        return codGrupo;
    }

    public void setCodGrupo(Long codGrupo) {
        this.codGrupo = codGrupo;
    }

    public Long getMenConMesaje() {
        return menConMesaje;
    }

    public void setMenConMesaje(Long menConMesaje) {
        this.menConMesaje = menConMesaje;
    }

    public Date getFechaRegMen() {
        return fechaRegMen;
    }

    public void setFechaRegMen(Date fechaRegMen) {
        this.fechaRegMen = fechaRegMen;
    }

    @OneToMany
    @JoinColumns({
            @JoinColumn(name = "consecuser", referencedColumnName = "consecuser"),
            @JoinColumn(name = "usu_consecuser", referencedColumnName = "usu_consecuser"),
            @JoinColumn(name = "consmesaje", referencedColumnName = "consmesaje")
    })
    private List<Contenido> contenidos;

}



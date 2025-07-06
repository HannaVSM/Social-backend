package com.social.backend.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @Column(name = "consecuser", length = 5, nullable = false)
    private String consecUser;

    @Column(name = "usu_consecuser", length = 5)
    private String usuConsecUser;

    @Column(name = "codubica", length = 4, nullable = false)
    private String codUbica;

    @Column(name = "nombre", length = 25, nullable = false)
    private String nombre;

    @Column(name = "apellido", length = 25, nullable = false)
    private String apellido;

    @Column(name = "username", length = 6, nullable = false)
    private String username;

    @Column(name = "fecharegistro", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;

    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @Column(name = "celular", length = 16, nullable = false)
    private String celular;

    @Lob
    @Column(name = "imageuser")
    private byte[] imageUser;

    @Lob
    @Column(name = "temauser")
    private byte[] temaUser;

    @Lob
    @Column(name = "huellauser")
    private byte[] huellaUser;

    // Getters y setters

    public String getConsecUser() { return consecUser; }
    public void setConsecUser(String consecUser) { this.consecUser = consecUser; }

    public String getUsuConsecUser() { return usuConsecUser; }
    public void setUsuConsecUser(String usuConsecUser) { this.usuConsecUser = usuConsecUser; }

    public String getCodUbica() { return codUbica; }
    public void setCodUbica(String codUbica) { this.codUbica = codUbica; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public Date getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(Date fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCelular() { return celular; }
    public void setCelular(String celular) { this.celular = celular; }

    public byte[] getImageUser() { return imageUser; }
    public void setImageUser(byte[] imageUser) { this.imageUser = imageUser; }

    public byte[] getTemaUser() { return temaUser; }
    public void setTemaUser(byte[] temaUser) { this.temaUser = temaUser; }

    public byte[] getHuellaUser() { return huellaUser; }
    public void setHuellaUser(byte[] huellaUser) { this.huellaUser = huellaUser; }
}


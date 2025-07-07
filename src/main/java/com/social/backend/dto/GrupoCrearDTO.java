package com.social.backend.dto;

import java.util.List;

public class GrupoCrearDTO {
    private String consecUser;
    private String nomGrupo;
    private List<String> usuarios;

    // Getters y Setters
    public String getConsecUser() { return consecUser; }
    public void setConsecUser(String consecUser) { this.consecUser = consecUser; }

    public String getNomGrupo() { return nomGrupo; }
    public void setNomGrupo(String nomGrupo) { this.nomGrupo = nomGrupo; }

    public List<String> getUsuarios() { return usuarios; }
    public void setUsuarios(List<String> usuarios) { this.usuarios = usuarios; }
}


package com.social.backend.service;

import com.social.backend.dto.GrupoCrearDTO;
import com.social.backend.model.Grupo;
import com.social.backend.model.Pertenece;
import com.social.backend.model.PerteneceId;
import com.social.backend.repository.GrupoRepository;
import com.social.backend.repository.PerteneceRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GrupoService {

    private final GrupoRepository grupoRepository;
    private final PerteneceRepository perteneceRepository;

    public GrupoService(GrupoRepository grupoRepository, PerteneceRepository perteneceRepository) {
        this.grupoRepository = grupoRepository;
        this.perteneceRepository = perteneceRepository;
    }

    public void crearGrupo(GrupoCrearDTO dto) {
        // 1. Obtener siguiente código de grupo
        Long nuevoCodGrupo = grupoRepository.findMaxCodGrupo().orElse(0L) + 1;

        // 2. Crear grupo
        Grupo grupo = new Grupo();
        grupo.setCodGrupo(nuevoCodGrupo);
        grupo.setConsecUser(dto.getConsecUser());
        grupo.setNomGrupo(dto.getNomGrupo());
        grupo.setFechaRegGrupo(new Date());
        byte[] dummyImage = new byte[] { 0x01 };
        grupo.setImagGrupo(dummyImage);

        grupoRepository.save(grupo);

        // 3. Insertar en la tabla de rompimiento
        for (String consecUser2 : dto.getUsuarios()) {
            Pertenece pertenece = new Pertenece();
            PerteneceId perteneceId = new PerteneceId();
            perteneceId.setCodGrupo(nuevoCodGrupo);
            perteneceId.setConsecUser(consecUser2);
            pertenece.setId(perteneceId);
            perteneceRepository.save(pertenece);
        }
    }

    public List<Grupo> obtenerGruposPorUsuario(String consecUser) {
        return grupoRepository.findGruposByUsuario(consecUser);
    }
}


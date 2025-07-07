package com.social.backend.service;

import com.social.backend.dto.ContenidoDTO;
import com.social.backend.dto.MensajeConContenidoDTO;
import com.social.backend.model.Contenido;
import com.social.backend.model.Mensaje;
import com.social.backend.model.MensajeId;
import com.social.backend.repository.ContenidoRepository;
import com.social.backend.repository.MensajeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContenidoService {

    private final ContenidoRepository contenidoRepository;
    private final MensajeRepository mensajeRepository;

    public ContenidoService(ContenidoRepository contenidoRepository, MensajeRepository mensajeRepository) {
        this.contenidoRepository = contenidoRepository;
        this.mensajeRepository = mensajeRepository;
    }

    public List<Contenido> obtenerContenidosPorMensaje(String consecUser, String usuConsecUser, Long consMesaje) {
        return contenidoRepository.findByMensaje(consecUser, usuConsecUser, consMesaje);
    }

    public MensajeConContenidoDTO obtenerMensajeConContenido(String consecUser, String usuConsecUser, Long consMesaje) {
        List<Contenido> contenidos = contenidoRepository.findByMensaje(consecUser, usuConsecUser, consMesaje);

        List<ContenidoDTO> contenidoDTOs = contenidos.stream().map(c -> {
            ContenidoDTO dto = new ContenidoDTO();
            dto.setConseContenido(c.getId().getConseContenido());
            dto.setIdTipoContenido(c.getIdTipoContenido());
            dto.setIdTipoArchivo(c.getIdTipoArchivo());
            dto.setLocalizaContenido(c.getLocalizaContenido());
            return dto;
        }).toList();

        // Buscar el mensaje real
        MensajeId id = new MensajeId();
        id.setConsecUser(consecUser);
        id.setUsuConsecUser(usuConsecUser);
        id.setConsMesaje(consMesaje);

        Mensaje mensaje = mensajeRepository.findById(id).orElse(null);

        MensajeConContenidoDTO mensajeDTO = new MensajeConContenidoDTO();
        mensajeDTO.setConsecUser(consecUser);
        mensajeDTO.setUsuConsecUser(usuConsecUser);
        mensajeDTO.setConsMensaje(consMesaje);
        mensajeDTO.setContenidos(contenidoDTOs);

        if (mensaje != null) {
            mensajeDTO.setCodGrupo(mensaje.getCodGrupo());
            mensajeDTO.setMenConMensaje(mensaje.getMenConMesaje());
            mensajeDTO.setFechaRegMen(mensaje.getFechaRegMen()); // Aquí se asigna la fecha
        }

        return mensajeDTO;
    }

    public List<MensajeConContenidoDTO> obtenerConversacion(String user1, String user2) {
        List<Mensaje> mensajes = mensajeRepository.findMensajesEntreUsuarios(user1, user2);

        return mensajes.stream().map(mensaje -> {
            MensajeId id = mensaje.getId();

            List<Contenido> contenidos = contenidoRepository.findByMensaje(
                    id.getConsecUser(), id.getUsuConsecUser(), id.getConsMesaje()
            );

            List<ContenidoDTO> contenidoDTOs = contenidos.stream().map(c -> {
                ContenidoDTO dto = new ContenidoDTO();
                dto.setConseContenido(c.getId().getConseContenido());
                dto.setIdTipoContenido(c.getIdTipoContenido());
                dto.setIdTipoArchivo(c.getIdTipoArchivo());
                dto.setLocalizaContenido(c.getLocalizaContenido());
                return dto;
            }).toList();

            MensajeConContenidoDTO dto = new MensajeConContenidoDTO();
            dto.setConsecUser(id.getConsecUser());
            dto.setUsuConsecUser(id.getUsuConsecUser());
            dto.setConsMensaje(id.getConsMesaje());
            dto.setCodGrupo(mensaje.getCodGrupo());
            dto.setMenConMensaje(mensaje.getMenConMesaje());
            dto.setFechaRegMen(mensaje.getFechaRegMen());
            dto.setContenidos(contenidoDTOs);
            return dto;
        }).toList();
    }

}


package com.social.backend.service;

import com.social.backend.dto.MensajeCrearDTO;
import com.social.backend.model.Contenido;
import com.social.backend.model.ContenidoId;
import com.social.backend.model.Mensaje;
import com.social.backend.model.MensajeId;
import com.social.backend.repository.ContenidoRepository;
import com.social.backend.repository.MensajeRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MensajeService {

    private final MensajeRepository mensajeRepository;
    private final ContenidoRepository contenidoRepository;

    public MensajeService(MensajeRepository mensajeRepository, ContenidoRepository contenidoRepository) {
        this.mensajeRepository = mensajeRepository;
        this.contenidoRepository = contenidoRepository;
    }

    public void crearMensajeConContenido(MensajeCrearDTO dto) {
        // 1. Generar siguiente ID para mensaje
        Long nuevoConsMensaje = mensajeRepository.findMaxConsMensaje()
                .orElse(0L) + 1;

        // 2. Crear mensaje
        Mensaje mensaje = new Mensaje();
        MensajeId mensajeId = new MensajeId();
        mensajeId.setConsecUser(dto.getConsecUser());
        mensajeId.setUsuConsecUser(dto.getUsuConsecUser());
        mensajeId.setConsMesaje(nuevoConsMensaje);

        mensaje.setId(mensajeId);
        mensaje.setCodGrupo(dto.getCodGrupo());
        mensaje.setMenConMesaje(null);
        mensaje.setFechaRegMen(new Date());

        mensajeRepository.save(mensaje);

        // 3. Generar ID para contenido
        Long nuevoConseContenido = contenidoRepository.findMaxConseContenido()
                .orElse(0L) + 1;

        Contenido contenido = new Contenido();
        ContenidoId contenidoId = new ContenidoId();
        contenidoId.setConseContenido(nuevoConseContenido);
        contenidoId.setConsecUser(dto.getConsecUser());
        contenidoId.setUsuConsecUser(dto.getUsuConsecUser());
        contenidoId.setConsMesaje(nuevoConsMensaje);

        contenido.setId(contenidoId);
        contenido.setIdTipoArchivo(dto.getIdTipoArchivo());
        contenido.setIdTipoContenido(dto.getIdTipoContenido());
        contenido.setLocalizaContenido(dto.getLocalizaContenido());
        byte[] dummyImage = new byte[] { 0x01 };
        contenido.setContenidoImag(dummyImage);

        contenidoRepository.save(contenido);
    }

    public List<Mensaje> obtenerMensajesEntre(String user1, String user2) {
        return mensajeRepository.findMensajesEntreUsuarios(user1, user2);
    }

    public Mensaje guardarMensaje(Mensaje mensaje) {
        return mensajeRepository.save(mensaje);
    }
}


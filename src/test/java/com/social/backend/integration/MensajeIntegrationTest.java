package com.social.backend.integration;

import com.social.backend.dto.MensajeCrearDTO;
import com.social.backend.model.Contenido;
import com.social.backend.model.ContenidoId;
import com.social.backend.model.Mensaje;
import com.social.backend.repository.ContenidoRepository;
import com.social.backend.repository.MensajeRepository;
import com.social.backend.service.MensajeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class MensajeIntegrationTest {

    @Autowired
    private MensajeService mensajeService;

    @Autowired
    private MensajeRepository mensajeRepository;

    @Autowired
    private ContenidoRepository contenidoRepository;

    @Test
    void crearMensajeConContenido_PersisteEnBD() {
        MensajeCrearDTO dto = new MensajeCrearDTO();
        dto.setConsecUser("001");
        dto.setUsuConsecUser("002");
        dto.setCodGrupo(null);
        dto.setIdTipoContenido("01");
        dto.setIdTipoArchivo("02");
        dto.setLocalizaContenido("/tmp/file.jpg");

        // Act
        mensajeService.crearMensajeConContenido(dto);

        // Assert: mensaje creado
        List<Mensaje> mensajes = mensajeRepository.findMensajesEntreUsuarios("001", "002");
        assertFalse(mensajes.isEmpty());
        Mensaje saved = mensajes.get(0);
        assertNotNull(saved.getId());
        Integer consMesaje = saved.getId().getConsMesaje();
        assertTrue(consMesaje >= 1L);

        // Assert: contenido creado y vinculado
        List<Contenido> contenidos = contenidoRepository.findByMensaje("001", "002", consMesaje);
        assertFalse(contenidos.isEmpty());
        Contenido c = contenidos.get(0);
        ContenidoId cid = c.getId();
        assertEquals("001", cid.getConsecUser());
        assertEquals("002", cid.getUsuConsecUser());
        assertEquals(consMesaje, cid.getConsMesaje());
        assertArrayEquals(new byte[]{0x01}, c.getContenidoImag());
    }
}


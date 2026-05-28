package com.social.backend.service;

import com.social.backend.dto.MensajeCrearDTO;
import com.social.backend.model.Contenido;
import com.social.backend.model.ContenidoId;
import com.social.backend.model.Mensaje;
import com.social.backend.model.MensajeId;
import com.social.backend.repository.ContenidoRepository;
import com.social.backend.repository.MensajeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MensajeServiceTest {

    @Mock
    private MensajeRepository mensajeRepository;

    @Mock
    private ContenidoRepository contenidoRepository;

    @InjectMocks
    private MensajeService mensajeService;

    @Captor
    ArgumentCaptor<Mensaje> mensajeCaptor;

    @Captor
    ArgumentCaptor<Contenido> contenidoCaptor;

    private MensajeCrearDTO dto;

    @BeforeEach
    void setUp() {
        dto = new MensajeCrearDTO();
        dto.setConsecUser("001");
        dto.setUsuConsecUser("002");
        dto.setCodGrupo(null);
        dto.setIdTipoContenido("01");
        dto.setIdTipoArchivo("02");
        dto.setLocalizaContenido("/tmp/file.jpg");
    }

    @Test
    void crearMensajeConContenido_DeberiaGenerarIdsCuandoHayMaximos() {
        when(mensajeRepository.findMaxConsMensaje())
                .thenReturn(Optional.of(10));
        when(contenidoRepository.findMaxConseContenido())
                .thenReturn(Optional.of((short) 100));

        mensajeService.crearMensajeConContenido(dto);

        verify(mensajeRepository).save(mensajeCaptor.capture());
        verify(contenidoRepository).save(contenidoCaptor.capture());

        Mensaje savedMensaje = mensajeCaptor.getValue();
        assertNotNull(savedMensaje.getId());
        MensajeId mid = savedMensaje.getId();
        assertEquals("001", mid.getConsecUser());
        assertEquals("002", mid.getUsuConsecUser());
        assertEquals(11, mid.getConsMesaje());

        Contenido savedContenido = contenidoCaptor.getValue();
        ContenidoId cid = savedContenido.getId();
        assertEquals(101, cid.getConseContenido());
        assertEquals(11, cid.getConsMesaje());
        assertEquals("001", cid.getConsecUser());
        assertEquals("002", cid.getUsuConsecUser());
        assertArrayEquals(new byte[] {0x01}, savedContenido.getContenidoImag());
    }

    @Test
    void crearMensajeConContenido_DeberiaUsarUnoCuandoNoHayMaximos() {
        when(mensajeRepository.findMaxConsMensaje()).thenReturn(Optional.empty());
        when(contenidoRepository.findMaxConseContenido()).thenReturn(Optional.empty());

        mensajeService.crearMensajeConContenido(dto);

        verify(mensajeRepository).save(mensajeCaptor.capture());
        verify(contenidoRepository).save(contenidoCaptor.capture());

        MensajeId mid = mensajeCaptor.getValue().getId();
        assertEquals(1L, Optional.ofNullable(mid.getConsMesaje()));
        ContenidoId cid = contenidoCaptor.getValue().getId();
        assertEquals(1L, cid.getConseContenido());
    }

    @Test
    void obtenerMensajesEntre_DelegatesToRepository() {
        Mensaje m = new Mensaje();
        when(mensajeRepository.findMensajesEntreUsuarios("001", "002")).thenReturn(List.of(m));

        List<Mensaje> result = mensajeService.obtenerMensajesEntre("001", "002");
        assertEquals(1, result.size());
        verify(mensajeRepository).findMensajesEntreUsuarios("001", "002");
    }

}


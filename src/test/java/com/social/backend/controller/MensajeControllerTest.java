package com.social.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.social.backend.model.Mensaje;
import com.social.backend.service.MensajeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;
import java.util.List;
import com.social.backend.model.MensajeId;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class MensajeControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();
    private MensajeService mensajeService = Mockito.mock(MensajeService.class);

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new MensajeController(mensajeService)).build();
    }

    private Mensaje sampleMensaje() {
        Mensaje m = new Mensaje();
        MensajeId id = new MensajeId();
        id.setConsecUser("001");
        id.setUsuConsecUser("002");
        id.setConsMesaje(1L);
        m.setId(id);
        m.setCodGrupo(null);
        m.setFechaRegMen(new Date());
        return m;
    }

    @Test
    void obtenerMensajesEntre_ReturnsList() throws Exception {
        Mensaje m = sampleMensaje();
        when(mensajeService.obtenerMensajesEntre(eq("001"), eq("002"))).thenReturn(List.of(m));

        mockMvc.perform(get("/api/mensajes/001/002"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void crearMensaje_ReturnsCreated() throws Exception {
        // DTO minimal
        String body = "{\"consecUser\":\"001\",\"usuConsecUser\":\"002\",\"idTipoContenido\":\"01\",\"idTipoArchivo\":\"02\",\"localizaContenido\":\"/tmp\"}";
        doNothing().when(mensajeService).crearMensajeConContenido(any());

        mockMvc.perform(post("/api/mensajes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated());
    }

}


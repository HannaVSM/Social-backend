package com.social.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.social.backend.dto.LoginRequest;
import com.social.backend.model.Usuario;
import com.social.backend.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UsuarioControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();
    private UsuarioService usuarioService = Mockito.mock(UsuarioService.class);

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new UsuarioController(usuarioService)).build();
    }

    private Usuario sampleUsuario() {
        Usuario u = new Usuario();
        u.setConsecUser("001");
        u.setNombre("Test");
        u.setApellido("User");
        u.setEmail("test@example.com");
        u.setCelular("3000000000");
        u.setCodUbica("0001");
        u.setUsername("tusr");
        u.setFechaRegistro(new Date());
        return u;
    }

    @Test
    void crearUsuario_Success() throws Exception {
        Usuario u = sampleUsuario();
        when(usuarioService.guardar(any(Usuario.class))).thenReturn(u);

        mockMvc.perform(post("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(u)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.consecUser").value("001"))
                .andExpect(jsonPath("$.email").value("test@example.com"));
    }

    @Test
    void crearUsuario_Conflict() throws Exception {
        Usuario u = sampleUsuario();
        when(usuarioService.guardar(any(Usuario.class))).thenThrow(new IllegalArgumentException("Ya existe"));

        mockMvc.perform(post("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(u)))
                .andExpect(status().isConflict());
    }

    @Test
    void obtenerTodos_ReturnsList() throws Exception {
        Usuario u = sampleUsuario();
        when(usuarioService.obtenerTodos()).thenReturn(List.of(u));

        mockMvc.perform(get("/api/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].consecUser").value("001"));
    }

    @Test
    void login_Success() throws Exception {
        Usuario u = sampleUsuario();
        LoginRequest req = new LoginRequest();
        req.setEmail("test@example.com");
        req.setCelular("3000000000");

        when(usuarioService.loginPorEmailYCelular(eq("test@example.com"), eq("3000000000"))).thenReturn(u);

        mockMvc.perform(post("/api/usuarios/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.consecUser").value("001"));
    }

    @Test
    void login_Unauthorized() throws Exception {
        LoginRequest req = new LoginRequest();
        req.setEmail("a@b.com");
        req.setCelular("123");

        when(usuarioService.loginPorEmailYCelular(any(), any())).thenThrow(new IllegalArgumentException("Credenciales inválidas."));

        mockMvc.perform(post("/api/usuarios/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void obtenerPorCelular_Success() throws Exception {
        Usuario u = sampleUsuario();
        when(usuarioService.obtenerPorCelular(eq("3000000000"))).thenReturn(u);

        mockMvc.perform(get("/api/usuarios/por-celular/3000000000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.consecUser").value("001"));
    }

    @Test
    void obtenerAmigos_Success() throws Exception {
        Usuario u = sampleUsuario();
        when(usuarioService.obtenerAmigos(eq("001"))).thenReturn(List.of(u));

        mockMvc.perform(get("/api/usuarios/amigos/001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

}


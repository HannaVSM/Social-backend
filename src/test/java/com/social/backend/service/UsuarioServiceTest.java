package com.social.backend.service;

import com.social.backend.model.Usuario;
import com.social.backend.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario base;

    @BeforeEach
    void setUp() {
        base = new Usuario();
        base.setNombre("Test");
        base.setApellido("User");
        base.setEmail("TEST@EXAMPLE.COM");
        base.setCelular("3000000000");
        base.setCodUbica("0001");
        base.setUsername("tuser");
        base.setFechaRegistro(new Date());
    }

    @Test
    void guardar_DeberiaAsignarConsecIncrementalYMinimizarEmail() {
        Usuario ultimo = new Usuario();
        ultimo.setConsecUser("005");

        when(usuarioRepository.buscarPorEmail(anyString())).thenReturn(Collections.emptyList());
        when(usuarioRepository.buscarPorCelular(anyString())).thenReturn(Collections.emptyList());
        when(usuarioRepository.buscarUltimoUsuario()).thenReturn(Optional.of(ultimo));
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(inv -> inv.getArgument(0));

        Usuario creado = usuarioService.guardar(base);

        assertEquals("006", creado.getConsecUser());
        assertEquals("test@example.com", creado.getEmail());
        verify(usuarioRepository).save(any(Usuario.class));
    }

    @Test
    void guardar_Primero_DeberiaRetornar001() {
        when(usuarioRepository.buscarPorEmail(anyString())).thenReturn(Collections.emptyList());
        when(usuarioRepository.buscarPorCelular(anyString())).thenReturn(Collections.emptyList());
        when(usuarioRepository.buscarUltimoUsuario()).thenReturn(Optional.empty());
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(inv -> inv.getArgument(0));

        Usuario creado = usuarioService.guardar(base);

        assertEquals("001", creado.getConsecUser());
    }

    @Test
    void guardar_SiEmailExistente_DeberiaLanzar() {
        when(usuarioRepository.buscarPorEmail(anyString())).thenReturn(List.of(new Usuario()));

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> usuarioService.guardar(base));
        assertTrue(ex.getMessage().contains("email"));
    }

    @Test
    void guardar_SiCelularExistente_DeberiaLanzar() {
        when(usuarioRepository.buscarPorEmail(anyString())).thenReturn(Collections.emptyList());
        when(usuarioRepository.buscarPorCelular(anyString())).thenReturn(List.of(new Usuario()));

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> usuarioService.guardar(base));
        assertTrue(ex.getMessage().contains("celular"));
    }

    @Test
    void loginPorEmailYCelular_Success() {
        Usuario u = new Usuario();
        u.setEmail("test@example.com");
        u.setCelular("3000000000");

        when(usuarioRepository.buscarPorEmailYCelular(anyString(), anyString())).thenReturn(Optional.of(u));

        Usuario result = usuarioService.loginPorEmailYCelular("TEST@EXAMPLE.COM", "3000000000");
        assertNotNull(result);
        assertEquals("test@example.com", result.getEmail());
    }

    @Test
    void loginPorEmailYCelular_Failure() {
        when(usuarioRepository.buscarPorEmailYCelular(anyString(), anyString())).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> usuarioService.loginPorEmailYCelular("a@b.com", "123"));
    }

    @Test
    void obtenerPorCelular_NotFound() {
        when(usuarioRepository.buscarUsuarioPorCelular(anyString())).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> usuarioService.obtenerPorCelular("999"));
    }
}


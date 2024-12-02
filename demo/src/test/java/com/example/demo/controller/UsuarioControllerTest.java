package com.example.demo.controller;

import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        when(usuarioService.cadastrarUsuario(anyString(), anyString(), anyString()))
            .thenReturn("Usuário cadastrado com sucesso!");
        when(usuarioService.login(anyString(), eq("senha1234")))
            .thenReturn("Bem-vindo, João Silva!");
        when(usuarioService.login(anyString(), eq("senhaerrada")))
            .thenReturn("Erro: Email ou senha incorretos.");
    }

    @Test
    void deveCadastrarUsuarioComSucesso() throws Exception {
        String json = "{\"nome\": \"João Silva\", \"email\": \"joao@example.com\", \"senha\": \"senha1234\"}";

        mockMvc.perform(post("/usuarios/cadastro")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("Usuário cadastrado com sucesso!"));
    }

    @Test
    void deveFazerLoginComSucesso() throws Exception {
        String json = "{\"email\": \"joao@example.com\", \"senha\": \"senha1234\"}";

        mockMvc.perform(post("/usuarios/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("Bem-vindo, João Silva!"));
    }

    @Test
    void deveFalharNoLoginComSenhaIncorreta() throws Exception {
        String json = "{\"email\": \"joao@example.com\", \"senha\": \"senhaerrada\"}";

        mockMvc.perform(post("/usuarios/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("Erro: Email ou senha incorretos."));
    }
}

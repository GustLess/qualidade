package com.example.demo.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test
    void deveCriarUsuarioComDadosValidos() {
        Usuario usuario = new Usuario("João Silva", "joao@example.com", "senha1234");

        assertEquals("João Silva", usuario.getNome());
        assertEquals("joao@example.com", usuario.getEmail());
        assertEquals("senha1234", usuario.getSenha());
    }

    @Test
    void devePermitirModificarDadosDoUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNome("Ana Souza");
        usuario.setEmail("ana@example.com");
        usuario.setSenha("senha5678");

        assertEquals("Ana Souza", usuario.getNome());
        assertEquals("ana@example.com", usuario.getEmail());
        assertEquals("senha5678", usuario.getSenha());
    }
}

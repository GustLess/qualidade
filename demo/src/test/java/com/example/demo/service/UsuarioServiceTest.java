package com.example.demo.service;

import com.example.demo.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioServiceTest {

    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        usuarioService = new UsuarioService();
    }

    // Teste 1: Cadastro de usuário com dados válidos
    @Test
    void deveCadastrarUsuarioComDadosValidos() {
        String resultado = usuarioService.cadastrarUsuario("João Silva", "joao@example.com", "senha1234");
        assertEquals("Usuário cadastrado com sucesso!", resultado);
    }

    // Teste 2: Cadastro com email duplicado
    @Test
    void deveRejeitarCadastroComEmailDuplicado() {
        usuarioService.cadastrarUsuario("João Silva", "joao@example.com", "senha1234");
        String resultado = usuarioService.cadastrarUsuario("Maria Souza", "joao@example.com", "senha1234");
        assertEquals("Erro: Email já cadastrado.", resultado);
    }

    // Teste 3: Cadastro com senha curta (menos de 8 caracteres)
    @Test
    void deveRejeitarCadastroComSenhaCurta() {
        String resultado = usuarioService.cadastrarUsuario("Ana Pereira", "ana@example.com", "12345");
        assertEquals("Erro: A senha deve ter pelo menos 8 caracteres e incluir um número.", resultado);
    }

    // Teste 4: Login com credenciais corretas
    @Test
    void devePermitirLoginComCredenciaisCorretas() {
        usuarioService.cadastrarUsuario("João Silva", "joao@example.com", "senha1234");
        String resultado = usuarioService.login("joao@example.com", "senha1234");
        assertEquals("Bem-vindo, João Silva!", resultado);
    }

    // Teste 5: Login com senha incorreta
    @Test
    void deveFalharLoginComSenhaIncorreta() {
        usuarioService.cadastrarUsuario("João Silva", "joao@example.com", "senha1234");
        String resultado = usuarioService.login("joao@example.com", "senhaerrada");
        assertEquals("Erro: Email ou senha incorretos.", resultado);
    }

    // Teste 6: Login com email não cadastrado
    @Test
    void deveFalharLoginComEmailNaoCadastrado() {
        String resultado = usuarioService.login("naoexiste@example.com", "senha1234");
        assertEquals("Erro: Email ou senha incorretos.", resultado);
    }
}

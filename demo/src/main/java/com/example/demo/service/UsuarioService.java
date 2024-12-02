package com.example.demo.service;

import com.example.demo.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.regex.Pattern;

@Service
public class UsuarioService {
    private final HashMap<String, Usuario> usuarios = new HashMap<>();

    public String cadastrarUsuario(String nome, String email, String senha) {
        if (usuarios.containsKey(email)) {
            return "Erro: Email já cadastrado.";
        }
        if (senha.length() < 8 || !Pattern.compile(".*\\d.*").matcher(senha).matches()) {
            return "Erro: A senha deve ter pelo menos 8 caracteres e incluir um número.";
        }
        usuarios.put(email, new Usuario(nome, email, senha));
        return "Usuário cadastrado com sucesso!";
    }

    public String login(String email, String senha) {
        Usuario usuario = usuarios.get(email);
        if (usuario != null && usuario.getSenha().equals(senha)) {
            return "Bem-vindo, " + usuario.getNome() + "!";
        }
        return "Erro: Email ou senha incorretos.";
    }
}


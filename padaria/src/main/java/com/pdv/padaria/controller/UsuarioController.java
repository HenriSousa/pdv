package com.pdv.padaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import com.pdv.padaria.model.Usuario;
import com.pdv.padaria.repository.UsuarioRepository;

@Controller
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ApplicationContext context;

    public boolean autenticar(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email);

        if (usuario != null && usuario.getSenha().equals(senha)) {
            return true;
        }
        return false;
    }

    public ApplicationContext getContext() {
        return context;
    }
}

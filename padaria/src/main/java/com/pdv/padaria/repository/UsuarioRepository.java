package com.pdv.padaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pdv.padaria.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    Usuario findByEmail(String email);    
}

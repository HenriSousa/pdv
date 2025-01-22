package com.pdv.padaria;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.pdv.padaria.model.Usuario;
import com.pdv.padaria.repository.UsuarioRepository;

@SpringBootApplication
public class PadariaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PadariaApplication.class, args);
	}

	@Bean
    public CommandLineRunner initData(UsuarioRepository usuarioRepository) {
        return args -> {
            if (usuarioRepository.findByEmail("admin@pdv.com") == null) {
                Usuario admin = new Usuario();
                admin.setEmail("admin@pdv.com");
                admin.setSenha("123456"); // Para produção, use hash para senhas!
                usuarioRepository.save(admin);
            }
        };
    }
}

package com.techdesk.techdesk.usuarios.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techdesk.techdesk.usuarios.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Optional findByEmail(String email);

}

package com.techdesk.techdesk.security.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techdesk.techdesk.chamados.dto.ChamadoRequestDTO;
import com.techdesk.techdesk.chamados.dto.ChamadoResponseDTO;
import com.techdesk.techdesk.usuarios.dto.UsuarioRequestDTO;
import com.techdesk.techdesk.usuarios.dto.UsuarioResponseDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	
	
	
	
	@PostMapping("/registrar")
	public ResponseEntity<UsuarioResponseDTO> criar(@RequestBody @Valid UsuarioRequestDTO dto){
		
		ChamadoResponseDTO criado = service.criar(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(criado); // 201 Created
	}
	
}

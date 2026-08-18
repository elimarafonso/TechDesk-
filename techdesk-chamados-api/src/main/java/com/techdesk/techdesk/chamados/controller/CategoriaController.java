package com.techdesk.techdesk.chamados.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techdesk.techdesk.chamados.dto.CategoriaRequestDto;
import com.techdesk.techdesk.chamados.dto.CategoriaResponseDTO;
import com.techdesk.techdesk.chamados.service.CategoriaService;

@RestController
@RequestMapping("api/categoria")
public class CategoriaController {

	private final CategoriaService service;

	public CategoriaController(CategoriaService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<CategoriaResponseDTO> criar(@RequestBody CategoriaRequestDto categoria) {
			CategoriaResponseDTO cat = service.criar(categoria);
		return ResponseEntity.status(HttpStatus.CREATED).body(cat);
	}
	
	@GetMapping
	public List<CategoriaResponseDTO> findAll() throws Throwable {
		
		return service.findAll();
	}

	
	


}

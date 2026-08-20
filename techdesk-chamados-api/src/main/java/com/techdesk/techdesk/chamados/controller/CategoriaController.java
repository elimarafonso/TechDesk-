package com.techdesk.techdesk.chamados.controller;

import com.techdesk.techdesk.chamados.repository.CategoriaRepository;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techdesk.techdesk.chamados.dto.CategoriaRequestDto;
import com.techdesk.techdesk.chamados.dto.CategoriaResponseDTO;
import com.techdesk.techdesk.chamados.service.CategoriaService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@Validated
@RequestMapping("api/categoria")
public class CategoriaController {

	private final CategoriaRepository categoriaRepository;
	private final CategoriaService service;

	public CategoriaController(CategoriaService service, CategoriaRepository categoriaRepository) {
		this.service = service;
		this.categoriaRepository = categoriaRepository;
	}

	@PostMapping
	public ResponseEntity<CategoriaResponseDTO> criar(@RequestBody @Valid  CategoriaRequestDto categoria) {
			CategoriaResponseDTO cat = service.criar(categoria);
		return ResponseEntity.status(HttpStatus.CREATED).body(cat);
	}
	
	@GetMapping
	public List<CategoriaResponseDTO> findAll() throws Throwable {
		
		return service.findAll();
	}

	@GetMapping("/{id}")
	public	ResponseEntity<CategoriaResponseDTO> buscaCategoria (@PathVariable @Positive Long id) throws Exception {
		return ResponseEntity.ok(service.buscar(id));
	}
	
	@DeleteMapping("/{id}")
	public	ResponseEntity<Void> deletaCategoria (@PathVariable Long id) throws Exception {
		service.excluirCategoria(id);
		return ResponseEntity.noContent().build();// 204 No Content;
	}

	@PatchMapping("/{id}")
	public ResponseEntity<CategoriaResponseDTO> atualizaCategoria(@Valid @PathVariable Long id, @Valid CategoriaRequestDto categoria){
		service.atualiza(id, categoria);		
		return null;
	}

}

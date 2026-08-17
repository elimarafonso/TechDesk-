package com.techdesk.techdesk.chamados.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techdesk.techdesk.chamados.dto.ChamadoRequestDTO;
import com.techdesk.techdesk.chamados.dto.ChamadoResponseDTO;
import com.techdesk.techdesk.chamados.service.CategoriaService;

@RestController
@RequestMapping("api/categoria")
public class CategoriaController {

	//private final CategoriaService service;

	
	
	/*@PostMapping
	public ResponseEntity<CategoriaDto> criar (@RequestBody categoria){
		
	}*/
	
	
	
	
	/*
	  @PostMapping
 public ResponseEntity<ChamadoResponseDTO> criar(@RequestBody ChamadoRequestDTO dto) throws Throwable {
     ChamadoResponseDTO criado = service.criar(dto);
     return ResponseEntity.status(HttpStatus.CREATED).body(criado); // 201 Created
 }
	 * */
	
}

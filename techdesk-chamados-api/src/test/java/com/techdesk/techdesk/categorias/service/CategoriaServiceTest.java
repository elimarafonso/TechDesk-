package com.techdesk.techdesk.categorias.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.techdesk.techdesk.categorias.dto.CategoriaResponseDTO;
import com.techdesk.techdesk.categorias.entity.Categoria;
import com.techdesk.techdesk.categorias.repository.CategoriaRepository;

@ExtendWith(MockitoExtension.class)
class CategoriaServiceTest {

	@InjectMocks 
	private CategoriaService categoriaService;
	
	@Mock
	private CategoriaRepository categoriaRepository;



	@Test
	@DisplayName("Deve Retornar uma Lista de Categorias")
	public void deveRetornarUmaListaDeCategorias() {
		
		Categoria categoriaManutencao = new Categoria(1L, "Manutencao", null);
		Categoria categoriaRecepcao = new Categoria(1L, "Recepcao",null);
		
		List<Categoria> categoriaList = new ArrayList<>();
		categoriaList.add(categoriaManutencao);
		categoriaList.add(categoriaRecepcao);
		
		
		Mockito.when(categoriaRepository.findAll()).thenReturn(Collections.list(categoriaList));
		List<CategoriaResponseDTO> allCategorias = categoriaService.findAll();
		System.out.println(allCategorias);

	}

}

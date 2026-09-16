package com.techdesk.techdesk.categorias.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		Categoria categoriaRecepcao = new Categoria(2L, "Recepcao", null);

		List<Categoria> categoriaList = new ArrayList<>();
		categoriaList.add(categoriaManutencao);
		categoriaList.add(categoriaRecepcao);

		Mockito.when(categoriaRepository.findAll()).thenReturn(categoriaList);

		List<CategoriaResponseDTO> allCategory = categoriaService.findAll();

		assertNotNull(allCategory);
		assertEquals(2, allCategory.size());

		assertEquals(1L, allCategory.get(0).id());
		assertEquals("Manutencao", allCategory.get(0).nome());

		assertEquals(2L, allCategory.get(1).id());
		assertEquals("Recepcao", allCategory.get(1).nome());

		Mockito.verify(categoriaRepository, Mockito.times(1)).findAll();
	}

	@Test
	@DisplayName("Deve retornar uma lista vazia")
	public void deveRetornarUmaListaVazia() {
		
		Mockito.when(categoriaService.findAll()).thenReturn(List.of());
		
		List<CategoriaResponseDTO> allCategory = categoriaService.findAll();
		
		assertTrue(allCategory.isEmpty());
		Mockito.verify(categoriaRepository).findAll();
	}
	
	
	@Test
	@DisplayName("Deve retornar uma Categoria")
	public void deveRetornarUmaCategoria() {
		
		Categoria categoria = new Categoria(1L, "Desenvolvimento",null);
		
			
		Mockito.when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));
		
		
		CategoriaResponseDTO buscaCategoria =categoriaService.buscar(1L);
		
		assertNotNull(buscaCategoria);
		
		
		assertEquals(1L, buscaCategoria.id());
		assertEquals("Desenvolvimento", buscaCategoria.nome());
		
		Mockito.verify(categoriaRepository, Mockito.times(1)).findById(1L);
		
	}
	
	
	
}










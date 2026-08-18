package com.techdesk.techdesk.chamados.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.techdesk.techdesk.chamados.dto.CategoriaRequestDto;
import com.techdesk.techdesk.chamados.dto.CategoriaResponseDTO;
import com.techdesk.techdesk.chamados.entity.Categoria;
import com.techdesk.techdesk.chamados.exceptions.ChamadoNaoEncontradoException;
import com.techdesk.techdesk.chamados.repository.CategoriaRepository;
@Service 
public class CategoriaService {

	private final CategoriaRepository categoriaRepository;

	public CategoriaService(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}

	public CategoriaResponseDTO criar(CategoriaRequestDto categoriaDto) {

		Categoria cat = new Categoria();
		cat.setNome(categoriaDto.nome());

		return toCategoryDto(categoriaRepository.save(cat));

	}



	public List<CategoriaResponseDTO> findAll() throws Throwable {
		return categoriaRepository.findAll().stream()
	             .map(t -> {
					try {
						return toCategoryDto(t);
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						new ChamadoNaoEncontradoException(e.getMessage());
					}
					return null;
				})
	             .toList();
	}
	
	
	private CategoriaResponseDTO toCategoryDto(Categoria toDto) {
		return new CategoriaResponseDTO(toDto.getId(),toDto.getNome());
	}
}

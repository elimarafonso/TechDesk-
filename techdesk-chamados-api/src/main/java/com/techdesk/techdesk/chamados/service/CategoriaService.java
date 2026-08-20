package com.techdesk.techdesk.chamados.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.techdesk.techdesk.chamados.TechdeskChamadosApiApplication;
import com.techdesk.techdesk.chamados.dto.CategoriaRequestDto;
import com.techdesk.techdesk.chamados.dto.CategoriaResponseDTO;
import com.techdesk.techdesk.chamados.entity.Categoria;
import com.techdesk.techdesk.chamados.exceptions.CategoriaJaExisteException;
import com.techdesk.techdesk.chamados.exceptions.CategoriaNaoEncontradaException;
import com.techdesk.techdesk.chamados.exceptions.CategoriaPossuiChamadosException;
import com.techdesk.techdesk.chamados.exceptions.ChamadoNaoEncontradoException;
import com.techdesk.techdesk.chamados.repository.CategoriaRepository;
import com.techdesk.techdesk.chamados.repository.ChamadoRepository;

@Service
public class CategoriaService {

	private final TechdeskChamadosApiApplication techdeskChamadosApiApplication;
	private final CategoriaRepository categoriaRepository;
	private final ChamadoRepository chamadoRepository;

	public CategoriaService(CategoriaRepository categoriaRepository,
			TechdeskChamadosApiApplication techdeskChamadosApiApplication, ChamadoRepository chamadoRepository) {
		this.categoriaRepository = categoriaRepository;
		this.techdeskChamadosApiApplication = techdeskChamadosApiApplication;
		this.chamadoRepository = chamadoRepository;
	}

	public CategoriaResponseDTO criar(CategoriaRequestDto categoriaDto) throws Throwable {

		categoriaRepository.findByNome(categoriaDto.nome()).ifPresent(categoria -> {
			throw new CategoriaJaExisteException(categoriaDto.nome());
		});

		Categoria cat = new Categoria();
		cat.setNome(categoriaDto.nome());

		return toCategoryDto(categoriaRepository.save(cat));

	}

	public List<CategoriaResponseDTO> findAll() throws Throwable {
		return categoriaRepository.findAll().stream().map(t -> {
			try {
				return toCategoryDto(t);
			} catch (Throwable e) {
				new ChamadoNaoEncontradoException(e.getMessage());
			}
			return null;
		}).toList();
	}

	public CategoriaResponseDTO buscar(Long id) throws Exception {

		Categoria cat = categoriaRepository.findById(id).orElseThrow(() -> new CategoriaNaoEncontradaException(id));
		return toCategoryDto(cat);

	}

	public void excluirCategoria(Long id) throws Exception {
		if (!categoriaRepository.existsById(id)) {
			throw new CategoriaNaoEncontradaException(id);
		}
		if (chamadoRepository.existsByCategoriaId(id)) {
			throw new CategoriaPossuiChamadosException(id);
		}
		
		categoriaRepository.deleteById(id);
	}

	public CategoriaResponseDTO atualiza(Long id, CategoriaRequestDto newCategoria) {

		Categoria oldCategoria = categoriaRepository.findById(id)
				.orElseThrow(() -> new CategoriaNaoEncontradaException(id));
		oldCategoria.setNome(newCategoria.nome());
		categoriaRepository.save(oldCategoria);

		return toCategoryDto(oldCategoria);

	}

	private CategoriaResponseDTO toCategoryDto(Categoria toDto) {
		return new CategoriaResponseDTO(toDto.getId(), toDto.getNome());
	}
}

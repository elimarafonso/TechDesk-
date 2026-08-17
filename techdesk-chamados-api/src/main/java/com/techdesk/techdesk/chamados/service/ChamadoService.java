package com.techdesk.techdesk.chamados.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.techdesk.techdesk.chamados.dto.ChamadoRequestDTO;
import com.techdesk.techdesk.chamados.dto.ChamadoResponseDTO;
import com.techdesk.techdesk.chamados.entity.Categoria;
import com.techdesk.techdesk.chamados.entity.Chamado;
import com.techdesk.techdesk.chamados.entity.StatusChamado;
import com.techdesk.techdesk.chamados.entity.Usuario;
import com.techdesk.techdesk.chamados.exceptions.*;
import com.techdesk.techdesk.chamados.repository.CategoriaRepository;
import com.techdesk.techdesk.chamados.repository.ChamadoRepository;

//📁 techdesk-chamados-api/src/main/java/com/techdesk/chamados/service/ChamadoService.java

@Service // camada de regra de negócio — o Spring gerencia o ciclo de vida desse bean
public class ChamadoService {

 private final ChamadoRepository chamadoRepository;
 private final CategoriaRepository categoriaRepository;

 public ChamadoService(ChamadoRepository chamadoRepository, CategoriaRepository categoriaRepository) {
     this.chamadoRepository = chamadoRepository;
     this.categoriaRepository = categoriaRepository;
 }

 public ChamadoResponseDTO criar(ChamadoRequestDTO dto) throws Throwable {
    Categoria categoria = (Categoria) categoriaRepository.findById(dto.categoriaId())
             .orElseThrow(() -> new CategoriaNaoEncontradaException(dto.categoriaId()));

     Chamado chamado = new Chamado();
     chamado.setTitulo(dto.titulo());
     chamado.setDescricao(dto.descricao());
 //    chamado.setCategoria(categoria);
     chamado.setStatusChamado(StatusChamado.ABERTO);
     chamado.setDataAbertura(LocalDateTime.now());
     //chamado.setUsuario(usuarioLogado);

     Chamado salvo = chamadoRepository.save(chamado);
     return toResponseDTO(salvo);
 }

 public List<ChamadoResponseDTO> listarTodos() throws Throwable {
     return chamadoRepository.findAll().stream()
             .map(t -> {
				try {
					return toResponseDTO(t);
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					new ChamadoNaoEncontradoException(e.getMessage());
				}
				return null;
			})
             .toList();
 }

 public ChamadoResponseDTO buscarPorId(Long id) throws Throwable {
     Chamado chamado = chamadoRepository.findById(id)
             .orElseThrow(() -> new ChamadoNaoEncontradoException(id.toString()));
     
     return toResponseDTO(chamado);
 }

 public ChamadoResponseDTO atualizarStatus(Long id, StatusChamado novoStatus) throws Throwable {
     Chamado chamado = chamadoRepository.findById(id)
             .orElseThrow(() -> new ChamadoNaoEncontradoException(id.toString()));
     chamado.setStatusChamado(novoStatus);
     if (novoStatus == StatusChamado.FECHADO) {
         chamado.setDataFechamento(LocalDateTime.now());
     }
     return toResponseDTO(chamadoRepository.save(chamado));
 }

 public void excluir(Long id) throws Throwable {
     if (!chamadoRepository.existsById(id)) {
         throw new ChamadoNaoEncontradoException(id.toString());
     }
     chamadoRepository.deleteById(id);
 }

 // Método auxiliar privado: converte Entity em DTO — mantém essa conversão
 // num único lugar, em vez de espalhar pelo Controller.
 private ChamadoResponseDTO toResponseDTO(Chamado c) throws Throwable {
     return new ChamadoResponseDTO(
             c.getId(), c.getTitulo(), c.getDescricao(),
             c.getStatusChamado(),  null, c.getDataAbertura()
     );
 }
}
package com.techdesk.techdesk.chamados.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CategoriaNaoEncontradaException.class)
	public ProblemDetail categoriaNaoEncontrada(CategoriaNaoEncontradaException ex) {

		ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);

		problem.setTitle("Categoria não encontrada");
		problem.setDetail(ex.getMessage());
		problem.setProperty("categoriaId", ex.getId());

		return problem;
	}

	@ExceptionHandler(ChamadoNaoEncontradoException.class)
	public ProblemDetail chamadoNaoEncontrado(ChamadoNaoEncontradoException ex) {

		ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
		
		problem.setTitle("Chamando NOT FOUND");
		problem.setDetail(ex.getMessage());
		
		return problem;
	}

	@ExceptionHandler(CategoriaJaExisteException.class)
	public ProblemDetail categoriaJaExisteException(CategoriaJaExisteException ex) {

		ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.CONFLICT);
		
		problem.setTitle("já existe esta categoria");
		problem.setDetail(ex.getMessage());
		
		return problem;
	}
	
	
	@ExceptionHandler(CategoriaPossuiChamadosException.class)
	public ProblemDetail categoriaPossuiChamadosException(CategoriaPossuiChamadosException ex) {

		ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.CONFLICT);
		
		problem.setTitle("Existem chamados para esta categoria.");
		problem.setDetail(ex.getMessage());
		
		return problem;
	}
	
	@ExceptionHandler(StatusChamadoNaoExisteException.class)
	public ProblemDetail statusChamadoNaoExisteException(StatusChamadoNaoExisteException ex) {

		ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
		
		problem.setTitle("Status Invalido.");
		problem.setDetail(ex.getMessage());
		
		return problem;
	}
	
	
	
	
}
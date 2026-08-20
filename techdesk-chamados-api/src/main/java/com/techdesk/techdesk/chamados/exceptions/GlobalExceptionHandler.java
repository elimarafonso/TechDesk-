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

}
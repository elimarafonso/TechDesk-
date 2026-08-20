package com.techdesk.techdesk.chamados.exceptions;

public class CategoriaNaoEncontradaException extends RuntimeException {
	  
	private final Long id;
	
	public CategoriaNaoEncontradaException(Long id) {
	        super("Categoria não encontrada com id: "+ id );
	        this.id =id;
	}

	public Long getId() {
		return id;
	}

	

}

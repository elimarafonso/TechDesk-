package com.techdesk.techdesk.chamados.exceptions;

public class CategoriaNaoEncontradaException extends Exception {
	    public CategoriaNaoEncontradaException(Long id) {
	        super("Categoria não encontrada com id: "+ id );
	    }

}

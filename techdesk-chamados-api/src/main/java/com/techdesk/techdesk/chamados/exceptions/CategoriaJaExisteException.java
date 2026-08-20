package com.techdesk.techdesk.chamados.exceptions;

public class CategoriaJaExisteException extends RuntimeException {
	
	   public CategoriaJaExisteException(String nome) {
	        super("Já existe uma categoria com o nome: " + nome);
	    }

}

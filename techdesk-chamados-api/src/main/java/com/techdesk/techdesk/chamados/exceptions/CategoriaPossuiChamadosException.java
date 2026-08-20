package com.techdesk.techdesk.chamados.exceptions;

public class CategoriaPossuiChamadosException extends RuntimeException{
	
	public CategoriaPossuiChamadosException(Long id) {
		super("Não é possível Excluir a categoria "+id+
				" porque existem chamados associados a ela.");
	}

}

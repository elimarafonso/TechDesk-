package com.techdesk.techdesk.chamados.exceptions;

public class StatusChamadoNaoExisteException extends RuntimeException{

	public StatusChamadoNaoExisteException(String status) {
		super("Status inválido: "+status);
	}

}

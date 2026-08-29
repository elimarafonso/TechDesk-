package com.techdesk.techdesk.chamados.exception;

public class StatusChamadoNaoExisteException extends RuntimeException{

	public StatusChamadoNaoExisteException(String status) {
		super("Status inválido: "+status);
	}

}

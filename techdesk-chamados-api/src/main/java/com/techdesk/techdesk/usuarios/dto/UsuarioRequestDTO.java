package com.techdesk.techdesk.usuarios.dto;

import jakarta.validation.constraints.NotBlank;

public record UsuarioRequestDTO(	 
		 @NotBlank String email,
	     @NotBlank String nome) {

}

package com.techdesk.techdesk.chamados.dto;

import jakarta.validation.constraints.NotNull;

public record CategoriaRequestDto(
			@NotNull String nome
){}

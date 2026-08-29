package com.techdesk.techdesk.chamados.exception;

import java.time.LocalDateTime;

//techdesk-chamados-api/src/main/java/com/techdesk/chamados/dto/ErroResponseDTO.java
public record ErroResponseDTO(String mensagem, int status, LocalDateTime timestamp) {
}
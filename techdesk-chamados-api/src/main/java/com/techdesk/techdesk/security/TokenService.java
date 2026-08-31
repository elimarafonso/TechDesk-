package com.techdesk.techdesk.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.techdesk.techdesk.usuarios.entity.Usuario;

@Service
public class TokenService {

	@Value("${jwt.secret}")
	private String secret;

	// Gera o token assinado, contendo o email do usuario e o prazo de expiração
	public String gerarToken(Usuario usuario) {

		Algorithm algoritmo = Algorithm.HMAC256(secret);

		return JWT.create()
				.withIssuer("techdesck-chamados-api")
				.withSubject(usuario.getEmail())
				.withExpiresAt(Instant.now()
				.plus(2, ChronoUnit.HOURS))
				.sign(algoritmo);
	}

	// Valida o token recebido e retorna o email do dono, se for valido
	public String validarToken(String token) {
		Algorithm algoritmo = Algorithm.HMAC256(secret);
		
		return JWT.require(algoritmo)
				.withIssuer("techdesk-chamados-api")
				.build()
				.verify(token)
				.getSubject();
	}

	
	
	
}




















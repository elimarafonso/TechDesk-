package com.techdesk.techdesk.security;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.techdesk.techdesk.usuarios.entity.Usuario;
import com.techdesk.techdesk.usuarios.repository.UsuarioRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AutenticacaoFilter extends OncePerRequestFilter {

	private final TokenService tokenService;
	private final UsuarioRepository usuarioRepository;

	public AutenticacaoFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
	
			String header = request.getHeader("Authorization");
		if (header != null) {
			String token = header.replace("Bearer ", "");
			String email = tokenService.validarToken(token);
		
			Usuario usuario = (Usuario) usuarioRepository.findByEmail(email).orElse(null);			
			if (usuario != null) {
				var auth = new UsernamePasswordAuthenticationToken(usuario, null, List.of());
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}
		filterChain.doFilter(request, response); // sempre continua a cadeia de filtros

	}

}

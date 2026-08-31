package com.techdesk.techdesk.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.techdesk.techdesk.security.AutenticacaoFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	private final AutenticacaoFilter autenticacaoFilter;

	public SecurityConfig(AutenticacaoFilter autenticacaoFilter) {
		this.autenticacaoFilter = autenticacaoFilter;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	return http
	.csrf(csrf -> csrf.disable()) // desnecessário para API stateless com JWT
	.sessionManagement(s ->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	.authorizeHttpRequests(auth -> auth.requestMatchers("/api/auth/**", "/api/status").permitAll() // login/status liberados
	.anyRequest().authenticated() ).addFilterBefore(autenticacaoFilter, UsernamePasswordAuthenticationFilter.class).build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); // nunca salvar senha em texto puro
	}

}

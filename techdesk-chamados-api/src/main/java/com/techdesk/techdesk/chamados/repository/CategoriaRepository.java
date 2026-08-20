package com.techdesk.techdesk.chamados.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techdesk.techdesk.chamados.entity.Categoria;

import jakarta.validation.Valid;


public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

	List<Categoria> findByNome(String nome);


	

	
}

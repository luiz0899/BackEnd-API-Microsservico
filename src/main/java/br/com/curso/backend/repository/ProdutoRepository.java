package br.com.curso.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.curso.backend.entity.Produto;



@Repository
public interface ProdutoRepository  extends JpaRepository<Produto , Integer> {
	
}

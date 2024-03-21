package br.com.curso.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.curso.backend.entity.Produto;
import br.com.curso.backend.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public List<Produto> obterTodos(){
		// colocar regra 
		return produtoRepository.obterTodos();	
	}
	
	public Optional<Produto> obterPorId(Integer id) {	
		return produtoRepository.obterProdPorId(id);
	}
	
	public Produto adicionar(Produto produto ) {
		return produtoRepository.adicionar(produto);
	}
	
	public void deletar(Integer id ) {
		produtoRepository.deletar(id);
	}
	
	public Produto atualizar(Integer id , Produto produto) {
		
		produto.setId(id);
		return produtoRepository.atualizar(produto );
	}
	

}

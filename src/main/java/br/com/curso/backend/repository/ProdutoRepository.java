package br.com.curso.backend.repository;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

import br.com.curso.backend.entity.Produto;
import br.com.curso.backend.exception.ResourceNotFoundException;

@Repository
public class ProdutoRepository {
	
	// banco teorico. 
	private ArrayList<Produto> produtos = new ArrayList<Produto>();
	private Integer ultimoId = 0 ;
	
	//metodo que retorna uma lista.
	
	public List<Produto> obterTodos() {
		return produtos;
	}
	
	//retorna um produto caso tenha sido encontrado
	
	public Optional<Produto> obterProdPorId (Integer id) {
		return produtos.stream()
			   .filter(produto -> produto.getId() == id)
			   .findFirst();
	}
	
	// metodo para adicionar o produto na lista.
	
	public Produto adicionar(Produto produto) {
		ultimoId ++;
		produto.setId(ultimoId);
		produtos.add(produto);
		
		return produto;
	}
	
	// deleta o produto por id.
	
	public void deletar( Integer id) {
		produtos.removeIf(produto -> produto.getId() == id );
	}
	
	// metodo para atualizar produto na lista.
	public Produto atualizar (Produto produto ) {
		
		//encontra o produto.
		Optional<Produto> produtoEncontrado = obterProdPorId(produto.getId());
		
		if (produtoEncontrado.isEmpty()) {
			throw new ResourceNotFoundException("produto não encontrado !");
		}
		
		//deleta o produto.
		deletar(produto.getId());
		
		//readiciona o produto atualizado com mesmo id.
		produtos.add(produto);
		
		return produto;
		
		
	}
	
	
	
	
	
	
	

}

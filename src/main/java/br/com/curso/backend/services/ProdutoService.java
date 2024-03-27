package br.com.curso.backend.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import br.com.curso.backend.entity.Produto;
import br.com.curso.backend.exception.ResourceNotFoundException;
import br.com.curso.backend.repository.ProdutoRepository;
import br.com.curso.backend.shared.ProdutoDto;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	private ModelMapper modelMapper = new ModelMapper();
		
	public List<ProdutoDto> obterTodos(){
		
		// retorna uma lista de produto do banco (Entity)
		List<Produto> produtos = produtoRepository.findAll();
		
		/* faz a conveção da class Entity Produto para uma class ProdutoDto 
		 * e retorna uma lista de produtoDto*/
		return produtos.stream()
				.map(produto -> modelMapper.map(produto, ProdutoDto.class)) 
				.collect(Collectors.toList());
	}
	
	public Optional<ProdutoDto> obterPorId(Integer id) {
		
		//obtendo o optinal por id
		Optional<Produto> produto =  produtoRepository.findById(id);
		
		//se não ouver produto , lança exception
		if (produto.isEmpty()) {
			throw new ResourceNotFoundException("produto com id " + id + " não encontado!");
		}
		
		//convertendo o meu optional de produto em um produtoDto
		ProdutoDto dto = new ModelMapper().map(produto.get() , ProdutoDto.class);
		return Optional.of(dto);
	}
	
	public ProdutoDto adicionar(ProdutoDto produtoDto ) {
		
		// removendo id do dto
		produtoDto.setId(null);
		//criando objeto de mapeamento.
		ModelMapper mapper =new ModelMapper();
		//convertendo dto para um produto(entity)
		Produto produto = mapper.map(produtoDto, Produto.class);
		//salvar no banco
		produto = produtoRepository.save(produto);
		//retornando o id para o dto do produto salvo
		produtoDto.setId(produto.getId());
		//retornando dto salvo 
		return produtoDto;
	}
	
	public void deletar(Integer id ) {
		
		// verificar se produto existe
		Optional<Produto> produto = produtoRepository.findById(id);
		
		//se não ouver produto , lança exception
		if (produto.isEmpty()) {
			throw new ResourceNotFoundException( "Produto com Id " + id + " não existe !!");
		}
		
		produtoRepository.deleteById(id); 
	}
	
	public ProdutoDto atualizar(Integer id, ProdutoDto produtoAtualizado) {
	    
		// Verificar se o produto com o ID fornecido existe no banco de dados
		produtoAtualizado.setId(id);
		//criar objeto de mapeamento 
	    ModelMapper mapper = new ModelMapper();
	    
	    Produto produto = mapper.map(produtoAtualizado, Produto.class);
	    
	    produtoRepository.save(produto);
	    
	    return produtoAtualizado ;
	    
	}
	

}

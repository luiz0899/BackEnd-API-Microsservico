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
	
	public ProdutoDto adicionar(ProdutoDto produto ) {
		return produtoRepository.save(produto);
	}
	
	public void deletar(Integer id ) {
		produtoRepository.deleteById(id); 
	}
	
	public ProdutoDto atualizar(Integer id, ProdutoDto produtoAtualizado) {
	    
		// Verificar se o produto com o ID fornecido existe no banco de dados
	    Optional<ProdutoDto> produtoExistenteOptional = produtoRepository.findById(id);
	    
	    if (produtoExistenteOptional.isPresent()) {
	        Produto produtoExistente = produtoExistenteOptional.get();
	        
	        // Modificar os atributos do produto existente com base nos dados do produto atualizado
	        produtoExistente.setNome(produtoAtualizado.getNome());
	        produtoExistente.setQuantidade(produtoAtualizado.getQuantidade());
	        produtoExistente.setValor(produtoAtualizado.getValor());
	        produtoExistente.setObservacao(produtoAtualizado.getObservacao());
	        
	        // Salvar as alterações no banco de dados
	        return produtoRepository.save(produtoExistente);
	    } else {
	        // Se o produto com o ID fornecido não existe, você pode lançar uma exceção ou lidar com isso de outra maneira, dependendo da lógica de negócios
	        throw new IllegalArgumentException("Produto com o ID fornecido não encontrado: " + id);
	    }
	}
	

}

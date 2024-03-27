
package br.com.curso.backend.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.curso.backend.entity.Produto;
import br.com.curso.backend.services.ProdutoService;
import br.com.curso.backend.shared.ProdutoDto;
import br.com.curso.backend.view.model.response.ProdutoResponse;
import br.com.curso.backend.view.model.resquest.ProdutoRequest;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping
	public ResponseEntity<List<ProdutoResponse>> obterTodos() {
		
		//listando minha lista de dto no banco 
		List<ProdutoDto> produtos = produtoService.obterTodos();
		//criando meu convertedor de objeto
		ModelMapper mapper = new ModelMapper() ;
		//convertendo minha lista produtoDto em uma lista produtoResponse
		List<ProdutoResponse> resposta = produtos.stream()
				.map(ProdutoDto -> mapper.map(ProdutoDto, ProdutoResponse.class))
				.collect(Collectors.toList());
		
		return new ResponseEntity<>(resposta , HttpStatus.OK)  ;
	}
	
	@GetMapping("/{id}")
	public  ResponseEntity<Optional<ProdutoResponse>>obterProdPorId (@PathVariable Integer id) {
		
		Optional<ProdutoDto> dto = produtoService.obterPorId(id);
		
		ProdutoResponse produtoResponse = new ModelMapper().map(dto.get(), ProdutoResponse.class);
		
		return new ResponseEntity<>(Optional.of(produtoResponse),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ProdutoResponse> adicionar(@RequestBody ProdutoRequest produtoRequest) {
		
		ModelMapper mapper = new ModelMapper(); 
		
		ProdutoDto produtoDto = mapper.map(produtoRequest, ProdutoDto.class);
		
		produtoDto = produtoService.adicionar(produtoDto);
		
		return new ResponseEntity<>(mapper.map(produtoDto, ProdutoResponse.class),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Integer id) {
		produtoService.deletar(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT) ;
	}
	
	@PutMapping("/{id}")
	public  ResponseEntity<ProdutoResponse> atualizar(@RequestBody ProdutoRequest produtoRequest , @PathVariable Integer id ) {
		
		ModelMapper mapper = new ModelMapper(); 
		
		ProdutoDto produtoDto = mapper.map(produtoRequest, ProdutoDto.class);
		
		produtoDto = produtoService.atualizar(id,produtoDto);
		
		return new ResponseEntity<>(mapper.map(produtoDto, ProdutoResponse.class),HttpStatus.OK);
		
		
	}

}

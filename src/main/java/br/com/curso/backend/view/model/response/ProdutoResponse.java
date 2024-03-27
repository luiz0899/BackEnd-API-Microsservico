package br.com.curso.backend.view.model.response;

import lombok.Data;

@Data
public class ProdutoResponse {
	
	private Integer id ;
	
	private String nome;
	
	private Integer quantidade ;
	
	private Double valor ;

}

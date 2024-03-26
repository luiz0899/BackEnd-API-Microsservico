package br.com.curso.backend.view.model.response;

import lombok.Data;

@Data
public class ProdutoResponse {
	
	private String nome;
	
	private Integer quantidade ;
	
	private Double valor ;

}

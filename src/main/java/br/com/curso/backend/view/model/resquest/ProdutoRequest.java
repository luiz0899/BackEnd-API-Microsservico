package br.com.curso.backend.view.model.resquest;

import lombok.Data;

@Data
public class ProdutoRequest {

	
	private String nome;
	
	private Integer quantidade ;
	
	private Double valor ;
	
	private String observacao ;

	
}

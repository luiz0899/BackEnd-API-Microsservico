package br.com.curso.backend.shared;

import lombok.Data;

@Data
public class ProdutoDto {
	
		private Integer id;
		
		private String nome;
		
		private Integer quantidade ;
		
		private Double valor ;
		
		private String observacao ;

	
}

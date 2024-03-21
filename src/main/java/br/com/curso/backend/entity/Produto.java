package br.com.curso.backend.entity;

import jakarta.persistence.Transient;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Produto {
	
	@NotNull(message = "O id do produto é obrigatório !")
	private Integer id;
	
	@NotNull(message = "o nome é obrigatorio .")
	@Max(value = 50 ,message = " O nome deve conter no maximo 50 caractéres .")
	private String nome;
	
	@NotNull(message = "A quantidade é obrigatoria !")
	private Integer quantidade ;
	
	@NotNull(message = "O valor é obrigatorio !")
	private Double valor ;
	
	@NotNull(message = "A observação é obrigatoria !")
	private String observatcao ;
	
	@Transient
	public boolean isAvaliado () {
		return getId() != null && getId() > 0;
		
	}
	
	

}

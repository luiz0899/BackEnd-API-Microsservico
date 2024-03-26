package br.com.curso.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "produto")
@Entity(name = "Produto")
public class Produto {
	
	@Id
	@Column(name = "id_produto")
	@GeneratedValue(strategy= GenerationType.AUTO )
	private Integer id;
	
	@Column(name = "nome")
	@NotNull(message = "o nome é obrigatorio .")
	private String nome;
	
	@Column(name = "quantidade")
	@NotNull(message = "A quantidade é obrigatoria !")
	private Integer quantidade ;
	
	@Column(name = "valor")
	@NotNull(message = "O valor é obrigatorio !")
	private Double valor ;
	
	@Column(name = "observacao")
	@NotNull(message = "A observação é obrigatoria !")
	private String observacao ;
	
	@Transient
	public boolean isAvaliado () {
		return getId() != null && getId() > 0;
		
	}
	
	

}

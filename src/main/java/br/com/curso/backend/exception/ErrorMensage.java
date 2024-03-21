package br.com.curso.backend.exception;

import lombok.Data;

@Data
public class ErrorMensage {
	
	private String titulo ;
	private Integer status ;
	private String mensagem ;
	
	public ErrorMensage(String titulo, Integer status, String mensagem) {
		
		this.titulo = titulo;
		this.status = status;
		this.mensagem = mensagem;
	}
	
	

}

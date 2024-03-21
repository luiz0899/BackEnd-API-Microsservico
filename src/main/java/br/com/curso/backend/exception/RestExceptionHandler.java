package br.com.curso.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleresourceNotFoundException(ResourceNotFoundException ex){
		
		ErrorMensage error =  new ErrorMensage("Not Found" , 404 , ex.getMessage());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		
	}

}

package org.generation.universe.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

//Para indicar que esta clase es un Controlador de excepciones agrego la anotacion ControlerAdvice
@ControllerAdvice
public class EmailNotFoundController {

//La clase recibe anotaciones que nos permite controlar  el metodo de la excepcion y aplicarlo  en metodos de tipo resposeEntity
	
	
	//Metodo para manejar la excepcion  y que retorna un mensaje 
	@ResponseBody
	@ExceptionHandler(EmailNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String userNotFoundHandler(EmailNotFoundException e){
		return e.getMessage();
		
	}
}

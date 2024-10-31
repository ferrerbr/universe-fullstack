package org.generation.universe.exceptions;

public class EmailNotFoundException  extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Constructor  que va a recibir un parametro para poder evaluar y lanxar la excepcion 
	public EmailNotFoundException(String email) {
		super("El usuario con el email:" + email + " no existe.");
	}

}

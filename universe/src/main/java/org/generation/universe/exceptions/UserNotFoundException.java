package org.generation.universe.exceptions;

public class UserNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Constructor  que va a recibir un parametro para poder evaluar y lanxar la excepcion 
	public UserNotFoundException(Long id) {
		super("El usuario con el Id:" + id + " no existe");
		
	}

}

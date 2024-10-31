package org.generation.universe.service;

import java.util.List;

import org.generation.universe.exceptions.UserNotFoundException;
import org.generation.universe.model.User;
import org.generation.universe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	//Atributos
	//Mandamos a llamar UserRepository
	private  UserRepository userRepository;
	
	//Inyeccion de dependencias en el construcrtor
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	//Metodo propios  de la clase
	//Método para obtener todos los usuarios
	public List<User> getAll(){
		return userRepository.findAll(); // el metodo find All es un metodo de JpaRepositori que esta heredando en UserRepository
		
	}
	
	//Metodo para crear un nuevo usuario
	public  User createUser(User newUser) {
		return userRepository.save(newUser);
	}
	
	//Metodo para eliminar un usuario mediante id
	
	public void deleteUser(Long id){
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);
			//Eliminar
			
		}else {
			//exception
			throw new UserNotFoundException(id);
		}
		
	}
	
	//Método para recuperar ususarios por id
	//Metodo creado por mi con un if else
	/*public Optional<User> getUserById(Long id) {
		if (userRepository.existsById(id)) {
			return userRepository.findById(id);
			
		}else {
			//exception
			throw new UserNotFoundException(id);
		}
	}*/
	public User getUserById(Long id) {
		return userRepository.findById(id)
				.orElseThrow(()-> new UserNotFoundException(id));
	}
	
	/*Metodo para recuperar usuarios por Id (sin excepcion de tipo Optional
	 * 
	 * pubic Optional<User> getUserById(Long id) {
		   (userRepository.existsById(id)) {
			return userRepository.findById(id);
	 * */
	
	//Método para recuperar usuarios por email( con excepciones y de tipo User)
	
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email); //este metodo no lo tienen JPA por lo tanto hayq ue crearlo en UserRepository
	}
	
	
	//Metodo para actualizar informacion de usuarios, permitienfo modificar el password
	public User updateUser(User user, Long id) {
		return userRepository.findById(id)
				.map(userMap ->{
					userMap.setPassword(user.getPassword());
					return userRepository.save(userMap);
				})
				.orElseThrow(()-> new UserNotFoundException(id));
		
	}
	
	//Metodo para recuperar ususario por username 
	
	

}

package org.generation.universe.controller;

import java.util.List;

import org.generation.universe.exceptions.EmailNotFoundException;
import org.generation.universe.model.User;
import org.generation.universe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vi")
//CORS
@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.POST})
public class UserController {
	//Atributos
	//Mandamos a llamar a UserService
	private UserService userService;
	
	//Inyeccion de dependencias 
	//Método constructor
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	//Metodos propios de la clase 
	//Mapear metodos 
	@GetMapping("/usuarios") //localhost:8080/api/v1/usuarios
	public List<User> getMappingAll(){
		return userService.getAll();
		}
	
	//Maperar Método ´post que reciba un nuevo objeto y el body del mismo(@RequestBody
	//en postman tengo wue construir el body de la entidad en formato JSON
	@PostMapping
	public User newUser(@RequestBody User user) {
		return userService.createUser(user);
	}
	
	
	//Mapear el método delete que apunte a un id especificp para eso debemos permitir que el Id sera variable   en el endPoint(@PathVariable)
	
	@DeleteMapping("/usuarios/eliminar/{id}") //localhost:8080/api/vi/usuarios/eliminar/8
	public void deleteUser(@PathVariable(name = "id") Long id) {
		userService.deleteUser(id);
	}
	
	//Mapear el método GetById que apunte a un id especifico
	
	@GetMapping("usuarios/usuario/{id}")
	public User userGetById(@PathVariable(name = "id") Long id){
		 return userService.getUserById(id);
		
	}
	
	//Mapear metodo getUserByEmail  aplicando la query (JPQL) y la exception
	//--ResponseEntity<Entity>  clase de Spring que me permite  representar respuestas HTTP personalizables
	//--RquestParam (paramentro)  permite recubur parametros y otros valores 
	//--Creamos  clase EmailNotFound Exception y su aclase Controller EmailNotFoundController
	@GetMapping("/usuarios/usuario/email")
	public ResponseEntity<User> userGetByEmail (@RequestParam(name = "univemail") String email) {
		User userEmail = userService.getUserByEmail(email);
		if (userEmail == null) {
			throw new EmailNotFoundException(email);
		}
		return new ResponseEntity<User>(userEmail, HttpStatus.OK);
	}
	
	
	//Mapear el metodo updateUser  utilizando PUT, necesitamos acceder al usuario mediante id (find byId) y definir el nuevo valor
	
	@PutMapping("/usuarios/usuario/{id}")
	public User updateUser(@RequestBody User user,@PathVariable(name ="id") Long id) {
		return  userService.updateUser(user, id);
		
	}
	
	
}

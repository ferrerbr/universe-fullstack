package org.generation.universe.repository;

import org.generation.universe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//Esta interfaz hereda los métodos de JpaRepository y toma dos parametros , el objetomodel y el tipo de dato de la PK
public interface UserRepository extends JpaRepository<User,Long>{
	//Mas adelante aqui podremos realizar consultas(query):JPQL
	
	@Query("SELECT u FROM User u WHERE u.email = ?1 ")
	User findByEmail(String email);
		
	

}

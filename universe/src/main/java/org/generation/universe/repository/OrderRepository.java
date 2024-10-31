package org.generation.universe.repository;

import org.generation.universe.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
	
	//JPQL

}

package org.generation.universe.service;

import java.util.List;

import org.generation.universe.exceptions.EmailNotFoundException;
import org.generation.universe.model.Order;
import org.generation.universe.model.User;
import org.generation.universe.repository.OrderRepository;
import org.generation.universe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class OrderService {
	
	private OrderRepository orderRepository;
	private UserRepository userRepository;

	
	@Autowired
	public OrderService(OrderRepository orderRepository,UserRepository userRepository) {
		this.orderRepository = orderRepository;
		this.userRepository = userRepository;
	}
	
	
	
	//GetAll
	public List<Order> getAll(){
		return orderRepository.findAll();
	}
	
	
	//Post
	 public Order newOrder (Order order,String email) {
		 User existingUser = userRepository.findByEmail(email);
		 if (existingUser == null) {
			 throw new EmailNotFoundException(email);
		 }
		 
		 //Asocioar la odern con usuario existente
		 
		 order.setUser(existingUser);
		 return orderRepository.save(order);
	 }
	

}

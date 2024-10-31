package org.generation.universe.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.generation.universe.exceptions.EmailNotFoundException;
import org.generation.universe.model.Order;
import org.generation.universe.model.OrderStatus;
import org.generation.universe.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/orders")
public class OrderController {
	private OrderService orderService;

	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@GetMapping("/ordenes")
	public List<Order> getAll(){
		return orderService.getAll();
		}
	
	//Metodo post con una estructura especifica que cumpla el contrato de  la Api, mas una conversion de datos (BLOB)
	@PostMapping
	public ResponseEntity<Order> newOrder (
		
		@RequestParam(name = "email")String email,
		@RequestParam(name ="fechaDeOrden")@DateTimeFormat(pattern ="yyyy-MM-dd'T'HH:mm")Date fechaDeOrden,
		@RequestParam(name = "total") BigDecimal total,
		@RequestParam(name = "estatus")OrderStatus estatus,
		@RequestParam(name = "factura") MultipartFile factura
		) {
		
		try {
			//convertir multipartFile en Byte[] mediante un metodo que me regresa un arreglo de bytes
			byte[] facturaByte = factura.getBytes();
			
			//crear mnueva orden 
			 Order newOrder = new Order();
			 newOrder.setFechadepedido(fechaDeOrden);
			 newOrder.setTotal(total);
			 newOrder.setEstatus(estatus);
			 newOrder.setFactura(facturaByte);
			 // Guardando la orden creada usando el metodo de OrderService 
			 Order createOrder = orderService.newOrder(newOrder, email);
			 
			 //Retornamos una respuesta j ok de la ResponseEntity sorbre la orde creada
			 return ResponseEntity.ok(createOrder);
		
		}catch(EmailNotFoundException e) {
			return ResponseEntity.notFound().build();
			
		}catch(IOException e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	
	
	

}

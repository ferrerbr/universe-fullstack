package org.generation.universe.model;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


@Entity
@Table(name = "orders")
public class Order {
	//atributos
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "fecha_orden", nullable = false, unique =false)
	@Temporal(TemporalType.TIMESTAMP) //Me permite identificar campos del tipo Date  y puedo  definir si trabajo con fecha y hora o algun otro valor 
	private Date fechaDePedido;
	
	@Column(name = "total", length = 15, nullable = false, unique =false)
	private BigDecimal total;
	
	@Column(name = "estatus", length = 15, nullable = false, unique =false)
	@Enumerated(EnumType.STRING)// Para recibir y enviar Enum a la BD en forma  de String
	private OrderStatus estatus;
	
	@Column(name = "factura",columnDefinition = "LONGBLOB", nullable = false, unique =false)
	@Lob //Indica que el tipo de dato es BLOB  y se envia a la base de datos como archivo de gran tamaño
	private byte[] factura;
	
	
	//Definir la relacion entre las entidades(N : 1)ManytoOne
	@ManyToOne
	@JoinColumn(name ="id_user", nullable = false)
	@JsonIgnore
	private User user;
	
	
	//Constructor lleno
	public Order(long id, Date fechadepedido, BigDecimal total, OrderStatus estatus, byte[] fectura) {
		super();
		this.id = id;
		this.fechaDePedido = fechadepedido;
		this.total = total;
		this.estatus = estatus;
		this.factura = fectura;
	}
	
	//Constructor vacio
	public Order() {
		
	}
	
	//Getters y Setters

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getFechadepedido() {
		return fechaDePedido;
	}

	public void setFechadepedido(Date fechadepedido) {
		this.fechaDePedido = fechadepedido;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public OrderStatus getEstatus() {
		return estatus;
	}

	public void setEstatus(OrderStatus estatus) {
		this.estatus = estatus;
	}

	public byte[] getFactura() {
		return factura;
	}

	public void setFactura(byte[] fectura) {
		this.factura = fectura;
	}
	
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	//Metodo to string


	@Override
	public String toString() {
		return "Order [id=" + id + ", fechadepedido=" + fechaDePedido + ", total=" + total + ", estatus=" + estatus
				+ ", factura=" + Arrays.toString(factura) + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(factura);
		result = prime * result + Objects.hash(estatus, fechaDePedido, id, total);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return estatus == other.estatus && Objects.equals(fechaDePedido, other.fechaDePedido)
				&& Arrays.equals(factura, other.factura) && id == other.id && Objects.equals(total, other.total);
	}
	
	
	
	
	
	
	

}

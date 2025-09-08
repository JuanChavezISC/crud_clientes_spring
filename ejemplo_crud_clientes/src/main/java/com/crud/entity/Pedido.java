package com.crud.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedidos", schema = "public")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPedido;
	
	private Date fechaPedido;
	private Double total;
	
	private Cliente cliente;

	
	public Pedido() {
		super();
	}

	public Pedido(Long id, Date fechaPedido, Double total, Cliente cliente) {
		super();
		this.idPedido = id;
		this.fechaPedido = fechaPedido;
		this.total = total;
		this.cliente = cliente;
	}

	public Long getId() {
		return idPedido;
	}

	public void setId(Long id) {
		this.idPedido = id;
	}

	public Date getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	
}

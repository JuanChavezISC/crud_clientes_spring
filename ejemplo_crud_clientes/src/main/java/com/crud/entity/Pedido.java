package com.crud.entity;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
	@ManyToOne
	@JoinColumn(
			name = "id_cliente",
			referencedColumnName = "idCliente")
	private Cliente cliente;

	@ManyToMany(
			cascade = CascadeType.ALL
	)
	@JoinTable(
			name = "pedido_producto_map",
			joinColumns = @JoinColumn(
					name= "id_pedido",
					referencedColumnName = "idPedido"
							),
			inverseJoinColumns = @JoinColumn(
					name = "id_producto",
					referencedColumnName = "idProducto"
								)
	)
	private List<Producto> productoList;
	
	
	public Pedido() {
		super();
	}

	public Pedido(Long id, Date fechaPedido, Double total, Cliente cliente, List<Producto> productoList) {
		super();
		this.idPedido = id;
		this.fechaPedido = fechaPedido;
		this.total = total;
		this.cliente = cliente;
		this.productoList = productoList;
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

	public List<Producto> getProductoList() {
		return productoList;
	}

	public void setProductoList(List<Producto> productoList) {
		this.productoList = productoList;
	}
	
	
	
}

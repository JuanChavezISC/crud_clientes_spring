package com.crud.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "factura", schema = "public")
public class Factura {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idFactura;
	private Date fechaEmision;
	private Double monto;
	@OneToOne
	@JoinColumn(
			name = "id_pedido",
			referencedColumnName = "idPedido")
	private Pedido pedido;

	public Factura() {
		super();
	}

	public Factura(Long idFactura, Date fechaEmision, Double monto, Pedido pedido) {
		super();
		this.idFactura = idFactura;
		this.fechaEmision = fechaEmision;
		this.monto = monto;
		this.pedido = pedido;
	}

	public Long getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(Long idFactura) {
		this.idFactura = idFactura;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	
}

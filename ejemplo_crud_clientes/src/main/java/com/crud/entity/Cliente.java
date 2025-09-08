package com.crud.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name= "clientes", schema = "public")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCliente;
	
	private String nombre;
	private String apellido;
	@Column(unique = true)
	private String email;
	@Column(name = "fecha_creacion")
	private Date createAt;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipoCliente_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private TipoCliente tipoCliente;
	
	public Cliente() {
		super();
	}
	
	public Cliente(Long idCliente, String nombre, String apellido, String email, Date createAt,
			TipoCliente tipoCliente) {
		super();
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.createAt = createAt;
		this.tipoCliente = tipoCliente;
	}
	// Getters & Setters
	
	public TipoCliente getTipoCliente() {
		return tipoCliente;
	}
	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	
	public Long getId() {
		return idCliente;
	}
	public void setId(Long id) {
		this.idCliente = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	
}

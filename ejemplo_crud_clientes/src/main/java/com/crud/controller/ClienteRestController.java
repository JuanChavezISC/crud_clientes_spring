package com.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.crud.entity.Cliente;
import com.crud.service.ClienteService;

@RestController
@RequestMapping("/api")
public class ClienteRestController {

	// Inyeccion de Servicio
	@Autowired
	private ClienteService clienteService;
	
	// Consultar clientes que tenga en mi tabla clientes
	@GetMapping("/clientes")
	@ResponseStatus (HttpStatus.OK)
	public List<Cliente> consulta(){
		return clienteService.findAll();
	}
	
	
}

package com.crud.service;

import java.util.List;

import com.crud.dto.ClienteDto;
import com.crud.entity.Cliente;

public interface IClienteService {

	List<Cliente> findAll();
	
	Cliente findByID(Long id);
	
	Cliente createCliente(ClienteDto cliente);
	
	void delete(Long id);
	
	Cliente actualizarCliente(Long id, ClienteDto cliente);
}

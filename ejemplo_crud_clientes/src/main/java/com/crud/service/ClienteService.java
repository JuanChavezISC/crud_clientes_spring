package com.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crud.repository.IClienteRepository;
import com.crud.dto.ClienteDto;
import com.crud.entity.Cliente;

@Service
public class ClienteService {

	@Autowired
	private IClienteRepository clienteRepository;
	
	// Consulta de todos los clientes
	@Transactional(readOnly = true)
	public List<Cliente> findAll(){
		return (List<Cliente>)clienteRepository.findAll();
	}
	
	// Consulta por Id
	@Transactional(readOnly = true)
	public Cliente findByID(Long id) 
	{
		return (Cliente) clienteRepository.findById(id).orElse(null);
	}
	
	// Crear nuevo cliente
	
	public Cliente createCliente(ClienteDto cliente) 
	{
		Cliente clienteEntity = new Cliente();
		clienteEntity.setNombre(cliente.getNombre());
		clienteEntity.setApellido(cliente.getApellido());
		clienteEntity.setEmail(cliente.getEmail());
		
		return clienteRepository.save(clienteEntity);
	}
	
	// Eliminar cliente
	
	public void delete(Long id) 
	{
		clienteRepository.deleteById(id);
	}
	
	// Integrar metodo Actualizar
	
	public Cliente actualizarCliente(Long id, ClienteDto cliente) {
		Cliente clienteEntity = clienteRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("Cliente no encontrado"));
		clienteEntity.setNombre(cliente.getNombre());
		clienteEntity.setApellido(cliente.getApellido());
		clienteEntity.setEmail(cliente.getEmail());
		
		return clienteRepository.save(clienteEntity);
	}
	
}

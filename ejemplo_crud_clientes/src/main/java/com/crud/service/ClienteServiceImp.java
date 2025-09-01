package com.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crud.dto.ClienteDto;
import com.crud.entity.Cliente;
import com.crud.entity.TipoCliente;
import com.crud.repository.IClienteRepository;
import com.crud.repository.ITipoClienteRepository;

@Service
public class ClienteServiceImp implements IClienteService {

	@Autowired
	private IClienteRepository clienteRepository;
	
	@Autowired
	private ITipoClienteRepository tipoClienteRepository; // Inyeccion
	
	// Consulta de todos los clientes
	@Transactional(readOnly = true)
	public List<Cliente> findAll(){
		System.out.println("Lista de clientes totales");
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
		
		
		//Validar tipoCliente
		if (cliente.getTipoCliente() != null) {
			TipoCliente tipo = null;
			
			if (cliente.getTipoCliente().getId() != null) {
				// Caso 1: Mandan ID
				tipo = tipoClienteRepository.findById(cliente.getTipoCliente().getId())
						.orElseThrow(()-> new RuntimeException("Tipo cliente no encontrado con id: "
						+ cliente.getTipoCliente().getId()));
			}
			else if (cliente.getTipoCliente().getTipoCliente() != null) 
			{
				// Caso 2: Mandaron solo nombre
				
				tipo = tipoClienteRepository.findByTipoCliente(cliente.getTipoCliente().getTipoCliente())
						.orElseGet(() -> {
							TipoCliente nuevo = new TipoCliente();
							nuevo.setTipoCliente(cliente.getTipoCliente().getTipoCliente());
							return tipoClienteRepository.save(nuevo);
							});
			}
			clienteEntity.setTipoCliente(tipo);
		}
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
		
		//Validar tipoCliente
				if (cliente.getTipoCliente() != null) {
					TipoCliente tipo = null;
					
					if (cliente.getTipoCliente().getId() != null) {
						// Caso 1: Mandan ID
						tipo = tipoClienteRepository.findById(cliente.getTipoCliente().getId())
								.orElseThrow(()-> new RuntimeException
										("Tipo cliente no encontrado con id: "
												+ cliente.getTipoCliente().getId()));
					}
					else if (cliente.getTipoCliente().getTipoCliente() != null) 
					{
						// Caso 2: Mandaron solo nombre
						
						tipo = tipoClienteRepository.findByTipoCliente(cliente.getTipoCliente().getTipoCliente())
								.orElseGet(() -> {
									TipoCliente nuevo = new TipoCliente();
									nuevo.setTipoCliente(cliente.getTipoCliente().getTipoCliente());
									return tipoClienteRepository.save(nuevo);
									});
					}
					clienteEntity.setTipoCliente(tipo);
				}
		
		return clienteRepository.save(clienteEntity);
	}

}

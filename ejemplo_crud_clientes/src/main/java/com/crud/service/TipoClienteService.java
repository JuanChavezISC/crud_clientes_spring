package com.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crud.dto.TipoClienteDto;
import com.crud.entity.TipoCliente;
import com.crud.repository.ITipoClienteRepository;

@Service
public class TipoClienteService {
	
	@Autowired
	private ITipoClienteRepository tipoClienteRepository;
	
	// Consulta de todos los tipos de cliente
	@Transactional(readOnly = true)
	public List<TipoCliente> findAll() {
		return (List<TipoCliente>)tipoClienteRepository.findAll();
	}
	
	//Consulta por Id
	@Transactional(readOnly = true)
	public TipoCliente findById(Long id) {
		return (TipoCliente) tipoClienteRepository.findById(id).orElse(null);
	}
	
	// Crear nuevo Tipo Cliente
	
	public TipoCliente createTipoCliente(TipoClienteDto tipoCliente) 
	{
		TipoCliente tipoClienteEntity = new TipoCliente();
		tipoCliente.setTipoCliente(tipoCliente.getTipoCliente());
		return tipoClienteRepository.save(tipoClienteEntity);
	}
	
	// Eliminar Tipo Cliente
	
	public void delete(Long id) {
		tipoClienteRepository.deleteById(id);
	}
	
	//Actualizar Tipo Cliente
	
	public TipoCliente actualizarTipoCliente(Long id, TipoClienteDto tipocliente) {
		TipoCliente tipoClienteEntity = tipoClienteRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("Tipo Cliente no encontrado"));
		tipoClienteEntity.setTipoCliente(tipocliente.getTipoCliente());
		
		return tipoClienteRepository.save(tipoClienteEntity);
	}

}

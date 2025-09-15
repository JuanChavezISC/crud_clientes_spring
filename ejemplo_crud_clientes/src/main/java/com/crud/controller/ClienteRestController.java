package com.crud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.crud.dto.ClienteDto;
import com.crud.entity.Cliente;
import com.crud.service.ClienteServiceImp;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins= {"http://localhost:4200"})
public class ClienteRestController {

	// Inyeccion de Servicio
	@Autowired
	private ClienteServiceImp clienteService;
	
	// Consultar clientes que tenga en mi tabla clientes
	@GetMapping("/clientes")
	@ResponseStatus (HttpStatus.OK)
	public List<Cliente> consulta(){
		return clienteService.findAll();
	}
	
	// Accede a traves clientes con un parametro de Id especifico
	/*Uso de un objeto ResponseEntity con anotacion para indicar que es parametro en el PATH*/
	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> ConsultaPorId(@PathVariable Long id) {
	
		// Variable de tipo Cliente
		Cliente cliente = null;
		String response = "";
		try {
			cliente = clienteService.findByID(id);
		} catch (DataAccessException e) {
			response = "Error al realizar la consulta";
			response = response.concat(e.getMessage().concat(e.getMostSpecificCause().toString()));
			return new ResponseEntity<String>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (cliente == null) 
		{
			response="El cliente con el ID: ".concat(id.toString()).concat("no existe en base de datos");
			return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}
	
	// Eliminar clientes por Id especifico
	
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		Map<String, Object> response = new HashMap<>();
		try {
			Cliente clienteDelete = this.clienteService.findByID(id);
			if (clienteDelete == null) 
			{
				response.put("mensaje", "Error al eliminar. El cliente no existe en base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			clienteService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar en base de datos");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Cliente eliminado con exito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	// Crear Cliente
	
	@PostMapping("/clientes")
	public ResponseEntity<?> create(@RequestBody ClienteDto cliente){
		Cliente clienteNew = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			clienteNew= this.clienteService.createCliente(cliente);
			
		} catch (DataAccessException  e) 
		{
			response.put("mensaje", "Error al realizar insert");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Cliente creado con exito con el ID " + clienteNew.getId());
		response.put("cliente", clienteNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
		}
	// Actualizar cliente
	
	@PutMapping("/clientes/{id}")
	public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody ClienteDto cliente){
		
		Cliente ClienteUpd = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			ClienteUpd= this.clienteService.actualizarCliente(id, cliente);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar update");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Cliente actualizado con exito con el ID " + ClienteUpd.getId());
		response.put("cliente", ClienteUpd);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
	}
	
}

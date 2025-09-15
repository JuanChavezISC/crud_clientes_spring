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

import com.crud.dto.TipoClienteDto;
import com.crud.entity.TipoCliente;
import com.crud.service.TipoClienteService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins= {"http://localhost:4200"})
public class TipoClienteRestController {

	//  Inyeccion de servicios
	@Autowired
	private TipoClienteService tipoClienteService;


	
	// Consultar tipos de clientes que tenga en mi tabla TipoCliente
	@GetMapping("/tipoClientes")
	@ResponseStatus (HttpStatus.OK)
	public List<TipoCliente> consulta() {
		return tipoClienteService.findAll();
	}
	
	/*Acceder a traves de tipos clientes con parametro Id especifico
	 * Uso de objeto ResponseEntity con anotacion para indicar que es
	 * un parametro en el Path*/
	
	@GetMapping("/tipoClientes/{id}")
	public ResponseEntity<?> consultarPorId(@PathVariable Long id) {
		
		// Variable clase TipoCliente
		
		TipoCliente tipoCliente = null;
		String response = "";
		try {
			tipoCliente = tipoClienteService.findById(id);
		} catch (DataAccessException e) {
			response = "Error al realizar la consulta";
			response = response.concat(e.getMessage().concat(e.getMostSpecificCause().toString()));
			return new ResponseEntity<String>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (tipoCliente == null) {
			response="El tipo cliente con el ID: ".concat(id.toString()).concat("no existe en base de datos");
			return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<TipoCliente>(tipoCliente, HttpStatus.OK);
	}
	
	// Eliminar clientes por Id especifico
	@DeleteMapping("/tipoClientes/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		Map<String, Object> response = new HashMap<>();
		try {
			TipoCliente tipoClienteDelete = this.tipoClienteService.findById(id);
			if (tipoClienteDelete == null) {
				response.put("mensaje", "Error al eliminar. El cliente no existe en base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			tipoClienteService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar en base de datos");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje","Cliente eliminado correctamente");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	// Crear Tipo Cliente
	@PostMapping("/tipoClientes")
	public ResponseEntity<?> create(@RequestBody TipoClienteDto cliente) {
		TipoCliente tipoClienteNew = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			tipoClienteNew= this.tipoClienteService.createTipoCliente(cliente);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar insert");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Tipo cliente actualizado con exito con el ID " + tipoClienteNew.getId());
		response.put("cliente", tipoClienteNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	// Actualizar Tipo Cliente
	
	@PutMapping("/tipoClientes/{id}")
	public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody TipoClienteDto cliente) {
		TipoCliente tipoClienteUpd = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			tipoClienteUpd= this.tipoClienteService.actualizarTipoCliente(id, cliente);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar update");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Tipo cliente actualizado con exito con el ID " + tipoClienteUpd.getId());
		response.put("cliente", tipoClienteUpd);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}

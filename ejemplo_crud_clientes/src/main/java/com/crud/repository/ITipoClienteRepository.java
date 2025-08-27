package com.crud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.entity.TipoCliente;


public interface ITipoClienteRepository extends JpaRepository<TipoCliente, Long> {
	Optional<TipoCliente> findByTipoCliente(String tipoCliente);
}

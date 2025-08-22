package com.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.entity.Cliente;

public interface IClienteRepository extends JpaRepository<Cliente, Long> {
	
}

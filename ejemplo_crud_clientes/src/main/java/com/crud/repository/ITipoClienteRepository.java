package com.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.entity.TipoCliente;

public interface ITipoClienteRepository extends JpaRepository<TipoCliente, Long> {

}

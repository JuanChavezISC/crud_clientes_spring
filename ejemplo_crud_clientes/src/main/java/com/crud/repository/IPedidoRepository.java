package com.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.entity.Pedido;

public interface IPedidoRepository extends JpaRepository<Pedido, Long> {

}

package com.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.entity.Factura;

public interface IFacturaRepository extends JpaRepository<Factura, Long> {

}

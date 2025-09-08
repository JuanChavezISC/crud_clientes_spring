package com.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.entity.Producto;

public interface IProductoRepository extends JpaRepository<Producto, Long> {

}

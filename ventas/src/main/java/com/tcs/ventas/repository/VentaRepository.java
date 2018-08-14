package com.tcs.ventas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.ventas.entity.Venta;

public interface VentaRepository extends JpaRepository<Venta, Integer>{

}

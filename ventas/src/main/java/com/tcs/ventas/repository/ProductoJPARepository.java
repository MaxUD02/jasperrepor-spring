package com.tcs.ventas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcs.ventas.entity.Producto;

@Repository
public interface ProductoJPARepository extends JpaRepository<Producto, Integer>{
		
}

package com.tcs.ventas.mapper;

import com.tcs.ventas.entity.Producto;
import com.tcs.ventas.model.ProductoDTO;

public class ProductoMapper {
	
	public static Producto toEntity(ProductoDTO dto) {
		Producto entity = new Producto();
		entity.setCodigo(dto.getCodigo());
		entity.setNombre(dto.getNombre());
		entity.setPrecio(dto.getPrecio());
		return entity;
	}
	
	public static ProductoDTO toDTO(Producto entity) {
		ProductoDTO dto = new ProductoDTO();
		dto.setCodigo(entity.getCodigo());
		dto.setNombre(entity.getNombre());
		dto.setPrecio(entity.getPrecio());
		return dto;
	}
}

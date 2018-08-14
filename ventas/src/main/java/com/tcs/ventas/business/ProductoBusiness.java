package com.tcs.ventas.business;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.ventas.entity.Producto;
import com.tcs.ventas.mapper.ProductoMapper;
import com.tcs.ventas.model.ProductoDTO;
import com.tcs.ventas.repository.ProductoJPARepository;
import com.tcs.ventas.repository.ProductosRepository;

@Service
public class ProductoBusiness {
	
	
	@Autowired
	//private ProductosRepository productoRepository;
	private ProductoJPARepository productoJPARepository;
	
	
	public List<ProductoDTO> getAll(){
		return productoJPARepository.findAll()
				.stream()
				.map(ProductoMapper :: toDTO)
				.collect(Collectors.toList());	
	}
	
	public ProductoDTO getById(int codigoProducto) {
		return productoJPARepository
				.findById(codigoProducto)
				.map(ProductoMapper:: toDTO)
				.orElse(null);
	}
	
	public int save(ProductoDTO productoDTO) {
		Producto productoCreado = productoJPARepository.save(ProductoMapper.toEntity(productoDTO));
		return productoCreado.getCodigo();
	}
	
	public int update(ProductoDTO productoDTO) {
		return save(productoDTO);
	}
	
	public int remove(int codigoProducto) {
		productoJPARepository.deleteById(codigoProducto);
		return codigoProducto;
	}
}

package com.tcs.ventas.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.ventas.business.ProductoBusiness;
import com.tcs.ventas.model.ApiResponse;
import com.tcs.ventas.model.ProductoDTO;

@RestController
public class ProductoController {
	
	@Autowired
	private ProductoBusiness productoBusiness;
	
	@GetMapping("/producto")
	public ApiResponse<List<ProductoDTO>> getAll(){
		ApiResponse<List<ProductoDTO>> response = new ApiResponse<>();
		List<ProductoDTO> list = productoBusiness.getAll();
		response.setSuccess(true);
		response.setMessage("Consulta satisfactoria");
		response.setPayload(list);
		return response;
	}
	
	@GetMapping("/producto/{codigoProducto}")
	public ApiResponse<ProductoDTO> getById(@PathVariable int codigoProducto) {
		ProductoDTO producto = productoBusiness.getById(codigoProducto);
		ApiResponse<ProductoDTO> response = new ApiResponse<>();
		response.setSuccess(true);
		response.setMessage("Consulta satisfactoria");
		response.setPayload(producto);
		return response;
	}
	
	@PostMapping("/producto")
	public ResponseEntity<ApiResponse<ProductoDTO>> save(@Valid @RequestBody ProductoDTO producto) {
		ApiResponse<ProductoDTO> response = new ApiResponse<>();
		int codigoProducto = productoBusiness.save(producto);
		producto.setCodigo(codigoProducto);
		response.setSuccess(true);
		response.setMessage("Producto Creado");
		response.setPayload(producto);
		return new ResponseEntity<ApiResponse<ProductoDTO>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/producto")
	public ProductoDTO update(@RequestBody ProductoDTO producto) {
		productoBusiness.update(producto);
		return producto;
	}
	
	@DeleteMapping("/producto/{id}")
	public ProductoDTO delete(@PathVariable("id") int codigoProducto) {
		ProductoDTO productoAEliminar = productoBusiness.getById(codigoProducto);
		productoBusiness.remove(codigoProducto);
		return productoAEliminar;
	}

}

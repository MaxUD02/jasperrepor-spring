package com.tcs.ventas.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tcs.ventas.model.ProductoDTO;

@Repository
public class ProductosRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<ProductoDTO> getAll() {
		String sql = "SELECT c_codigo_producto, c_nombre_producto, c_precio_base FROM t_producto";
		return jdbcTemplate.query(sql, (rs, rowNum) -> {// control+1
			ProductoDTO producto = new ProductoDTO();
			producto.setCodigo(rs.getInt("c_codigo_producto"));
			producto.setNombre(rs.getString("c_nombre_producto"));
			producto.setPrecio(rs.getBigDecimal("c_precio_base"));
			return producto;
		});
	}

	public ProductoDTO getById(int codigoProducto) {
		String sql = "SELECT c_codigo_producto, c_nombre_producto, c_precio_base FROM t_producto where c_codigo_producto = ?";
		
		try {
		return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
			ProductoDTO producto = new ProductoDTO();
			producto.setCodigo(rs.getInt("c_codigo_producto"));
			producto.setNombre(rs.getString("c_nombre_producto"));
			producto.setPrecio(rs.getBigDecimal("c_precio_base"));
			return producto;
		}, codigoProducto);
		}catch(EmptyResultDataAccessException e) {
			return null;
		
	}
	}

	private int getNextId() {
		String sql = "select MAX(c_codigo_producto)+1 from t_producto";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	public int insert(ProductoDTO producto) {
		String sql = "insert into t_producto(c_codigo_producto, c_nombre_producto, c_precio_base) " + "values(?,?,?)";
		int newId = getNextId();
		jdbcTemplate.update(sql, newId, producto.getNombre(), producto.getPrecio());
		return newId;
	}

	public int update(ProductoDTO producto) {
		String sql = "update t_producto set c_nombre_producto = ?, c_precio_base = ?" + "where c_codigo_producto =?";
		return jdbcTemplate.update(sql, producto.getNombre(), producto.getPrecio(), producto.getCodigo());
	}

	public int delete(int codigoProducto) {
		String sql = "delete from t_producto where c_codigo_producto = ?";
		return jdbcTemplate.update(sql, codigoProducto);
	}
}

//format control + shift + f

package com.tcs.ventas.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_PRODUCTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
	
	@Id
	@Column(name = "C_CODIGO_PRODUCTO")
	private Integer codigo;
	
	@Column(name = "C_NOMBRE_PRODUCTO")
	private String nombre;
	
	@Column(name = "C_PRECIO_BASE")
	private BigDecimal precio;

}

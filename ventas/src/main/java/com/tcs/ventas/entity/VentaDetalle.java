package com.tcs.ventas.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table (name = "T_VENTA_DETALLE")
@JsonIgnoreProperties(value = {"venta"})
public class VentaDetalle {
	
	@EmbeddedId
	private VentaDetalleId id;
	
	@ManyToOne
	@JoinColumn(name = "C_CODIGO_VENTA", insertable = false, updatable = false)
	private Venta venta;
	
	@ManyToOne
	@JoinColumn(name = "C_CODIGO_PRODUCTO", insertable = false, updatable = false)
	private Producto producto;
	
	@Column(name = "C_PRECIO_UNITARIO")
	private BigDecimal precio;
	
	@Column(name = "C_CANTIDAD")
	private Integer cantidad;

}

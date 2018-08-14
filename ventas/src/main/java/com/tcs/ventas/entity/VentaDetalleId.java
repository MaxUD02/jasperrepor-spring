package com.tcs.ventas.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class VentaDetalleId implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "C_CODIGO_VENTA")
	private Integer codigoVenta;
	
	@Column(name = "C_CODIGO_PRODUCTO")
	private Integer codigoProducto;

}

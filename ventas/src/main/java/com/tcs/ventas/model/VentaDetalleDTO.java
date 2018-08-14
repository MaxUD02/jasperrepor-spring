package com.tcs.ventas.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class VentaDetalleDTO {
	
	private Integer codigoProducto;
	private BigDecimal precioProducto;
	private int cantidad;
	private BigDecimal subTotal;

}

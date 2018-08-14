package com.tcs.ventas.model;

import java.util.List;

import lombok.Data;

@Data
public class VentaDTO {
	
	private Integer codigoVenta;
	private List<VentaDetalleDTO> items;

}

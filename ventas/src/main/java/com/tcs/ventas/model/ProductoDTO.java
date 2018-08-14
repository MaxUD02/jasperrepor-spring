package com.tcs.ventas.model;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {
	
	
	private int codigo;
	@NotNull(message = "El nombre no puede ser null")
	@Size(min = 2, max=30, message = "El nombre debe tener entre 2 y 30 caracteres")
	private String nombre;
	@NotNull(message = "Ingresar un numero")
	private BigDecimal precio;

}

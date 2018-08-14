package com.tcs.ventas.model;

import lombok.Data;

@Data
public class ConstraintViolation {

	private String fieldName;
	private String message;
	
}

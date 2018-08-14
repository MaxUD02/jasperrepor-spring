package com.tcs.ventas.model;

import java.util.List;

import lombok.Data;

@Data
public class ApiResponse<T> {
	
	private boolean success;
	private String message;
	private T payload;
	private List<ConstraintViolation> constraintViolation;

}

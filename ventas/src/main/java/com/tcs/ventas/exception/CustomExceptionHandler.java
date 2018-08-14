package com.tcs.ventas.exception;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcs.ventas.model.ApiResponse;
import com.tcs.ventas.model.ConstraintViolation;

@ControllerAdvice
public class CustomExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ApiResponse<Object> handle(Exception e){
		ApiResponse<Object> response = new ApiResponse<>();
		response.setSuccess(false);
		response.setMessage(e.getMessage());
		return response;
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public ApiResponse<Object> handleNotValid(MethodArgumentNotValidException e) {
	List<ConstraintViolation> listaErrores = new ArrayList<>();

	    e.getBindingResult()
	        .getFieldErrors() 
	        .forEach(fieldError -> {
	            ConstraintViolation cv = new ConstraintViolation();
	            cv.setFieldName(fieldError.getField());
	            cv.setMessage(fieldError.getDefaultMessage());
	            listaErrores.add(cv);
	        });

	    ApiResponse<Object> response = new ApiResponse<>();
	    response.setSuccess(false);
	    response.setMessage("Objecto no valido");
	    response.setConstraintViolation(listaErrores);
	    return response;
	}
}

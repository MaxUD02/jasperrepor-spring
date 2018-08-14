package com.tcs.ventas.controller;

import com.tcs.ventas.model.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.ventas.business.VentaBusiness;
import com.tcs.ventas.entity.Venta;
import com.tcs.ventas.model.VentaDTO;

import java.util.Base64;

@RestController
@RequestMapping("/venta")
public class VentaController {
	
	@Autowired
	private VentaBusiness ventaBusiness;
	
	@GetMapping("/{id}")
	public VentaDTO getById(@PathVariable Integer id) {
		return ventaBusiness.findById(id);
	}

	@GetMapping("/{id]/pdf")
	public ApiResponse<String> getPdf(@PathVariable Integer id) {
		byte[] pdfFile = ventaBusiness.getPdf(id);
		String base64 = Base64.getEncoder().encodeToString(pdfFile);

		ApiResponse<String> response = new ApiResponse<>();
		response.setSuccess(true);
		response.setMessage("OK	");
		response.setPayload(base64);
		return response;
	}

}

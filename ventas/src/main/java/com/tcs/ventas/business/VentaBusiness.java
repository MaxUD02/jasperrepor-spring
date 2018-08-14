package com.tcs.ventas.business;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.stereotype.Service;

import com.tcs.ventas.entity.Venta;
import com.tcs.ventas.model.VentaDTO;
import com.tcs.ventas.model.VentaDetalleDTO;
import com.tcs.ventas.repository.VentaRepository;

@Service
public class VentaBusiness {
	
	private VentaRepository ventaRepository;
	
	public VentaBusiness(VentaRepository ventaRepository) {
		this.ventaRepository = ventaRepository;
	}
	
	
//	public Venta findById(Integer id) {
//		return ventaRepository.findById(id).orElse(null);
//	}
	
	public VentaDTO findById(Integer id) {
		Optional<Venta> optVenta = ventaRepository.findById(id);
		if(!optVenta.isPresent()) {
			return null;
		}
		Venta venta = optVenta.get();
		
		VentaDTO ventaDTO = new VentaDTO();
		ventaDTO.setCodigoVenta(venta.getCodigo());
		
		List<VentaDetalleDTO> items = venta.getItems()
		.stream()
		.map(ventaDetalleEntity ->{
			VentaDetalleDTO dto = new VentaDetalleDTO();
			dto.setCodigoProducto(ventaDetalleEntity.getProducto().getCodigo());
			dto.setCantidad(ventaDetalleEntity.getCantidad());
			dto.setPrecioProducto(ventaDetalleEntity.getProducto().getPrecio());
			
			Integer cantidad = ventaDetalleEntity.getCantidad();
			BigDecimal precio = ventaDetalleEntity.getProducto().getPrecio();
			BigDecimal subTotal = precio.multiply(new BigDecimal(cantidad));
			dto.setSubTotal(subTotal);
			return dto;
		})
		.collect(Collectors.toList());
		ventaDTO.setItems(items);
		
		return ventaDTO;
	}

	public byte[] getPdf(Integer id) {

		VentaDTO reportebyId = findById(id);

		List<VentaDetalleDTO> itemsReport = reportebyId.getItems();

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Map<String, Object> jasperParams = new HashMap<String, Object>();
		jasperParams.put("prmShowSign", false);

		InputStream ventasReportStream
				= getClass().getResourceAsStream("/report/report.jrxml");
		JasperReport jasperReport
				= null;
		try {
			jasperReport = JasperCompileManager.compileReport(ventasReportStream);
		} catch (JRException e) {
			e.printStackTrace();
		}
		//= (JasperReport) JRLoader.loadObject(ventasReportStream);
		JasperPrint jasperPrint = null;
		try {
			jasperPrint = JasperFillManager.fillReport(jasperReport, jasperParams, new JRBeanCollectionDataSource(itemsReport));
		} catch (JRException e) {
			e.printStackTrace();
		}

		try {
			JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
		} catch (JRException e) {
			e.printStackTrace();
		}

		byte[] bytes  = baos.toByteArray();
		return bytes;
	}
}

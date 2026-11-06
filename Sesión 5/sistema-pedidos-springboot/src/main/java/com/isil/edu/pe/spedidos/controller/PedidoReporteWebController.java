package com.isil.edu.pe.spedidos.controller;

import com.isil.edu.pe.spedidos.service.PedidoReporteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
@RequestMapping("/reportes")
public class PedidoReporteWebController {

    private final PedidoReporteService pedidoReporteService;

    public PedidoReporteWebController(PedidoReporteService pedidoReporteService) {
        this.pedidoReporteService = pedidoReporteService;
    }

    @GetMapping("/pedidos")
    public String mostrarReportes(
            @RequestParam(required = false) String clienteBusqueda,
            @RequestParam(required = false) BigDecimal totalMinimo,
            @RequestParam(required = false, defaultValue = "1") Integer minPedidos,
            Model model) {

        // 1. Cargar el resumen existente en el proyecto
        model.addAttribute("resumen", pedidoReporteService.obtenerResumen());

        // 2. Ejecutar la búsqueda avanzada si se enviaron los parámetros
        if (clienteBusqueda != null && !clienteBusqueda.trim().isEmpty() && totalMinimo != null) {
            try {
                model.addAttribute("resultadosBusqueda",
                        pedidoReporteService.buscarPedidosPorClienteYTotalMinimo(clienteBusqueda, totalMinimo));
            } catch (IllegalArgumentException e) {
                model.addAttribute("errorBusqueda", e.getMessage());
            }
        }

        // 3. Ejecutar la consulta de clientes frecuentes
        try {
            model.addAttribute("clientesFrecuentes",
                    pedidoReporteService.listarClientesFrecuentes(minPedidos));
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorFrecuentes", e.getMessage());
        }

        // Retorna el nombre del archivo JSP (reportes-pedidos.jsp)
        return "reportes-pedidos";
    }
}

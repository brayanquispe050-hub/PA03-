package com.isil.edu.pe.spedidos.controller;

import com.isil.edu.pe.spedidos.controller.dto.PedidoResponse;
import com.isil.edu.pe.spedidos.entity.Pedido;
import com.isil.edu.pe.spedidos.service.PedidoService;
import com.isil.edu.pe.spedidos.service.PedidoReporteService;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador HTTP actualizado para incluir los endpoints
 * requeridos por la PA3 (JSON).
 */
@RestController
@RequestMapping({"/api/v1/orders", "/api/v1/pedidos"})
public class PedidoApiController {

    private final PedidoService pedidoService;
    private final PedidoReporteService pedidoReporteService; // Servicio inyectado para la PA3

    public PedidoApiController(PedidoService pedidoService, PedidoReporteService pedidoReporteService) {
        this.pedidoService = pedidoService;
        this.pedidoReporteService = pedidoReporteService;
    }

    @GetMapping
    public List<PedidoResponse> listarPedidos() {
        return pedidoService.listarPedidos()
                .stream()
                .map(PedidoResponse::from)
                .toList();
    }

    @GetMapping(value = "/resumen", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> resumenPedidos() {
        List<Pedido> pedidos = pedidoService.listarPedidos();

        int totalUnidades = pedidos.stream()
                .mapToInt(Pedido::getCantidad)
                .sum();

        Map<String, Object> resumen = new HashMap<>();
        resumen.put("modulo", "Sistema de Pedidos");
        resumen.put("totalPedidos", pedidos.size());
        resumen.put("totalUnidades", totalUnidades);

        return resumen;
    }

    // =================================================================
    // NUEVOS ENDPOINTS REST (JSON) PARA LA PA3
    // =================================================================

    @GetMapping(value = "/busqueda-avanzada", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> busquedaAvanzada(
            @RequestParam String cliente,
            @RequestParam BigDecimal totalMinimo) {
        try {
            return ResponseEntity.ok(
                    pedidoReporteService.buscarPedidosPorClienteYTotalMinimo(cliente, totalMinimo)
            );
        } catch (IllegalArgumentException e) {
            // Devuelve HTTP 400 Bad Request si la validación falla (Caso 3)
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping(value = "/clientes-frecuentes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> clientesFrecuentes(
            @RequestParam int minPedidos) {
        try {
            return ResponseEntity.ok(
                    pedidoReporteService.listarClientesFrecuentes(minPedidos)
            );
        } catch (IllegalArgumentException e) {
            // Devuelve HTTP 400 Bad Request
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}

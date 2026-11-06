package com.isil.edu.pe.spedidos.service;

import com.isil.edu.pe.spedidos.entity.Pedido;
import com.isil.edu.pe.spedidos.repository.projection.ClienteFrecuenteDTO;
import com.isil.edu.pe.spedidos.service.domain.ProductoVendido;
import com.isil.edu.pe.spedidos.service.domain.ResumenPedidos;
import java.math.BigDecimal;
import java.util.List;

public interface PedidoReporteService {

    ResumenPedidos obtenerResumen();

    List<Pedido> buscarPorCliente(String cliente);

    List<ProductoVendido> listarProductosMasVendidos();

    // ==========================================
    // NUEVOS MÉTODOS PARA LA PA3
    // ==========================================
    List<Pedido> buscarPedidosPorClienteYTotalMinimo(String cliente, BigDecimal totalMinimo);

    List<ClienteFrecuenteDTO> listarClientesFrecuentes(int minPedidos);
}

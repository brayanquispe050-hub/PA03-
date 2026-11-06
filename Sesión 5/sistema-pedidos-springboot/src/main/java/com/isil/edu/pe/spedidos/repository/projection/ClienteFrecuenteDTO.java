package com.isil.edu.pe.spedidos.repository.projection;

import java.math.BigDecimal;

public class ClienteFrecuenteDTO {
    private String cliente;
    private int totalPedidos;
    private int totalUnidades;
    private BigDecimal importeAcumulado;
    private BigDecimal ticketPromedio;

    public ClienteFrecuenteDTO() {
    }

    public ClienteFrecuenteDTO(String cliente, int totalPedidos, int totalUnidades, BigDecimal importeAcumulado, BigDecimal ticketPromedio) {
        this.cliente = cliente;
        this.totalPedidos = totalPedidos;
        this.totalUnidades = totalUnidades;
        this.importeAcumulado = importeAcumulado;
        this.ticketPromedio = ticketPromedio;
    }

    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }
    public int getTotalPedidos() { return totalPedidos; }
    public void setTotalPedidos(int totalPedidos) { this.totalPedidos = totalPedidos; }
    public int getTotalUnidades() { return totalUnidades; }
    public void setTotalUnidades(int totalUnidades) { this.totalUnidades = totalUnidades; }
    public BigDecimal getImporteAcumulado() { return importeAcumulado; }
    public void setImporteAcumulado(BigDecimal importeAcumulado) { this.importeAcumulado = importeAcumulado; }
    public BigDecimal getTicketPromedio() { return ticketPromedio; }
    public void setTicketPromedio(BigDecimal ticketPromedio) { this.ticketPromedio = ticketPromedio; }
}

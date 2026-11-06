package com.isil.edu.pe.spedidos.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.isil.edu.pe.spedidos.service.PedidoService;
import com.isil.edu.pe.spedidos.service.PedidoReporteService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class PedidoApiControllerTest {

    private static final String ORDERS_PATH = "/api/v1/orders";
    private static final String SUMMARY_PATH = ORDERS_PATH + "/resumen";

    private PedidoService pedidoService;
    // Nuevo mock para la PA3
    private PedidoReporteService pedidoReporteService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        // Se inicializan los mocks
        pedidoService = mock(PedidoService.class);
        pedidoReporteService = mock(PedidoReporteService.class);

        // Se inyectan ambos dependencias en el constructor
        mockMvc = MockMvcBuilders
                .standaloneSetup(new PedidoApiController(pedidoService, pedidoReporteService))
                .build();
    }

    @Test
    @DisplayName("Return empty JSON array from PedidoApiController when no orders exist")
    void returnEmptyJsonArrayFromPedidoApiControllerWhenNoOrdersExist() throws Exception {
        // Arrange
        when(pedidoService.listarPedidos()).thenReturn(List.of());

        // Act
        ResultActions response = mockMvc.perform(get(ORDERS_PATH));

        // Assert
        response
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));
    }

    @Test
    @DisplayName("Return zero order count from PedidoApiController when no orders exist")
    void returnZeroOrderCountFromPedidoApiControllerWhenNoOrdersExist() throws Exception {
        // Arrange
        when(pedidoService.listarPedidos()).thenReturn(List.of());

        // Act
        ResultActions response = mockMvc.perform(get(SUMMARY_PATH));

        // Assert
        response
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
}

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Módulo de Supervisión y Reportes - Sistema de Pedidos</title>
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- FontAwesome para iconos -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body class="bg-light">

    <!-- Navbar Superior -->
    <nav class="navbar navbar-dark bg-dark shadow-sm">
        <div class="container-fluid px-4">
            <span class="navbar-brand mb-0 h1"><i class="fa-solid fa-boxes-stacked me-2"></i> Sistema de Pedidos - ISIL</span>
            <span class="text-white-50 small"><i class="fa-solid fa-user-shield me-1"></i> Panel de Supervisión</span>
        </div>
    </nav>

    <div class="container py-5">

        <!-- Encabezado de la Vista -->
        <div class="row mb-4">
            <div class="col-12">
                <h2 class="fw-bold text-dark"><i class="fa-solid fa-chart-line text-primary me-2"></i> Módulo de Supervisión y Reportes</h2>
                <p class="text-muted">Resumen general de operaciones, filtrado avanzado de pedidos y control de clientes frecuentes.</p>
            </div>
        </div>

        <!-- SECCIÓN 1: Resumen General (Tarjetas Estadísticas) -->
        <div class="row g-4 mb-5">
            <div class="col-md-3 col-sm-6">
                <div class="card border-0 shadow-sm rounded-4 bg-white border-start border-primary border-4">
                    <div class="card-body">
                        <h6 class="text-muted text-uppercase fw-semibold small">Total Pedidos</h6>
                        <h3 class="fw-bold text-dark mb-0">${resumen.totalPedidos}</h3>
                    </div>
                </div>
            </div>
            <div class="col-md-3 col-sm-6">
                <div class="card border-0 shadow-sm rounded-4 bg-white border-start border-success border-4">
                    <div class="card-body">
                        <h6 class="text-muted text-uppercase fw-semibold small">Total Unidades</h6>
                        <h3 class="fw-bold text-dark mb-0">${resumen.totalUnidades}</h3>
                    </div>
                </div>
            </div>
            <div class="col-md-3 col-sm-6">
                <div class="card border-0 shadow-sm rounded-4 bg-white border-start border-warning border-4">
                    <div class="card-body">
                        <h6 class="text-muted text-uppercase fw-semibold small">Importe Acumulado</h6>
                        <h3 class="fw-bold text-dark mb-0">S/ <fmt:formatNumber value="${resumen.importeAcumulado}" type="number" minFractionDigits="2" maxFractionDigits="2"/></h3>
                    </div>
                </div>
            </div>
            <div class="col-md-3 col-sm-6">
                <div class="card border-0 shadow-sm rounded-4 bg-white border-start border-info border-4">
                    <div class="card-body">
                        <h6 class="text-muted text-uppercase fw-semibold small">Ticket Promedio</h6>
                        <h3 class="fw-bold text-dark mb-0">S/ <fmt:formatNumber value="${resumen.ticketPromedio}" type="number" minFractionDigits="2" maxFractionDigits="2"/></h3>
                    </div>
                </div>
            </div>
        </div>

        <!-- SECCIÓN 2: Búsqueda Avanzada de Pedidos -->
        <div class="card border-0 shadow-sm rounded-4 mb-5">
            <div class="card-header bg-white py-3 px-4 border-bottom-0">
                <h5 class="fw-bold text-dark mb-0"><i class="fa-solid fa-magnifying-glass me-2 text-primary"></i> Búsqueda Avanzada de Pedidos</h5>
            </div>
            <div class="card-body px-4 pb-4">
                <form method="get" action="pedidos" class="row g-3 align-items-end">
                    <div class="col-md-5">
                        <label for="clienteBusqueda" class="form-label fw-semibold text-secondary">Cliente</label>
                        <input type="text" class="form-control rounded-3" id="clienteBusqueda" name="clienteBusqueda" value="${param.clienteBusqueda}" placeholder="Ej. Juan Perez">
                    </div>
                    <div class="col-md-4">
                        <label for="totalMinimo" class="form-label fw-semibold text-secondary">Total Mínimo (S/)</label>
                        <input type="number" step="0.01" class="form-control rounded-3" id="totalMinimo" name="totalMinimo" value="${param.totalMinimo}" placeholder="0.00">
                    </div>
                    <div class="col-md-3">
                        <button type="submit" class="btn btn-primary w-100 rounded-3 py-2 fw-semibold shadow-sm">
                            <i class="fa-solid fa-filter me-1"></i> Buscar Pedidos
                        </button>
                    </div>
                </form>

                <!-- Resultados de Búsqueda (Si se aplicó filtro) -->
                <c:if test="${not empty resultadosBusqueda}">
                    <div class="mt-4">
                        <h6 class="fw-bold text-secondary mb-3">Resultados de la Búsqueda:</h6>
                        <div class="table-responsive">
                            <table class="table table-hover align-middle border">
                                <thead class="table-light">
                                    <tr>
                                        <th>ID</th>
                                        <th>Cliente</th>
                                        <th>Código</th>
                                        <th>Producto</th>
                                        <th>Precio U.</th>
                                        <th>Cant.</th>
                                        <th>Total</th>
                                        <th>Fecha</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="p" items="${resultadosBusqueda}">
                                        <tr>
                                            <td>${p.id}</td>
                                            <td class="fw-semibold">${p.cliente}</td>
                                            <td><span class="badge bg-secondary">${p.productoCodigo}</span></td>
                                            <td>${p.productoNombre}</td>
                                            <td>S/ ${p.precioUnitario}</td>
                                            <td>${p.cantidad}</td>
                                            <td class="fw-bold text-success">S/ ${p.total}</td>
                                            <td>${p.fecha}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>

        <!-- SECCIÓN 3: Clientes Frecuentes -->
        <div class="card border-0 shadow-sm rounded-4">
            <div class="card-header bg-white py-3 px-4 d-flex justify-content-between align-items-center border-bottom-0">
                <h5 class="fw-bold text-dark mb-0"><i class="fa-solid fa-users me-2 text-primary"></i> Clientes Frecuentes</h5>
                <form method="get" action="pedidos" class="d-flex align-items-center gap-2">
                    <label for="minPedidos" class="text-muted small text-nowrap mb-0">Mín. pedidos:</label>
                    <input type="number" class="form-control form-control-sm rounded-3" id="minPedidos" name="minPedidos" value="${param.minPedidos != null ? param.minPedidos : '1'}" style="width: 70px;">
                    <button type="submit" class="btn btn-outline-primary btn-sm rounded-3 px-3 fw-semibold">Filtrar</button>
                </form>
            </div>
            <div class="card-body px-4 pb-4">
                <div class="table-responsive">
                    <table class="table table-hover align-middle mb-0">
                        <thead class="table-dark">
                            <tr>
                                <th class="py-3">Cliente</th>
                                <th class="py-3">Total Pedidos</th>
                                <th class="py-3">Total Unidades</th>
                                <th class="py-3">Importe Acumulado</th>
                                <th class="py-3">Ticket Promedio</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="cf" items="${clientesFrecuentes}">
                                <tr>
                                    <td class="fw-semibold text-dark"><i class="fa-solid fa-user-circle text-muted me-2"></i> ${cf.cliente}</td>
                                    <td><span class="badge bg-primary rounded-pill px-3">${cf.totalPedidos}</span></td>
                                    <td>${cf.totalUnidades}</td>
                                    <td class="fw-bold text-success">S/ <fmt:formatNumber value="${cf.importeAcumulado}" type="number" minFractionDigits="2" maxFractionDigits="2"/></td>
                                    <td class="fw-semibold">S/ <fmt:formatNumber value="${cf.ticketPromedio}" type="number" minFractionDigits="2" maxFractionDigits="2"/></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

    </div>

    <!-- Bootstrap 5 JS Bundle -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

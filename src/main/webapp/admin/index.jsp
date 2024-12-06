<%@ page import="org.cahuas.webapp.servelet.cabeceras.models.modelo.Venta" %>
<%@ page import="java.util.List" %>
<%@ page import="org.cahuas.webapp.servelet.cabeceras.models.modelo.DetalleVenta" %>
<%@ page import="java.util.Collections" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<!-- Boxicons -->
	<link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
	<!-- My CSS -->
	<link rel="stylesheet" href="/webbs/admin/assets/css/tabla.css"/>
	<% List<Venta> ventaTotal = (List<Venta>)session.getAttribute("historialVentaTotal"); %>
	<%List<DetalleVenta> DetalleVenta = (List<DetalleVenta>) session.getAttribute("todoDetalleVenta");%>

	<title>AdminHub</title>
</head>
<body>

<!-- SIDEBAR -->
<section id="sidebar" class="text-black">
	<a href="#" class="brand">
		<i class='bx bxs-cloud fs-3'></i>
		<span class="fs-4 ms-2">SHALOM</span>
	</a>
	<ul class="nav flex-column" id="t">
		<li class="nav-item" id="actives">
			<a href="/webbs/admin/index.jsp" class="active">
				<i class='bx bxs-dashboard'></i>
				<span class="text">Dashboard</span>
			</a>
		</li>
		<li class="nav-item">
			<a href="/webbs/productos">
				<i class='bx bxs-shopping-bag-alt'></i>
				<span class="text">Inventario</span>
			</a>
		</li>
		<li class="nav-item">
			<a href="/webbs/admin/configVenta.jsp">
				<i class='bx bxs-doughnut-chart'></i>
				<span class="text">Ventas</span>
			</a>
		</li>
		<li class="nav-item">
			<a href="#">
				<i class='bx bxs-group'></i>
				<span class="text">Usuarios</span>
			</a>
		</li>

	</ul>
	<ul class="nav flex-column">
		<li class="nav-item ">
			<a href="/webbs/logout" style="color: red;">
				<i class='bx bxs-log-out-circle'></i>
				<span class="text">Salir</span>
			</a>
		</li>
	</ul>

</section>
<!-- SIDEBAR -->



<!-- CONTENT -->
<section id="content">
	<!-- NAVBAR -->
	<nav class="navbar navbar-expand-lg navbar-light  " style="background-color: #F9F9F9;">
		<div class="container-fluid">

			<!-- Botón para ocultar el sidebar -->
			<i class="bx bx-menu fs-3" id="menu-btn" style="cursor: pointer;" aria-label="Toggle Sidebar"></i>

			<!-- Título de la barra de navegación -->
			<a class="navbar-brand ms-2" href="#">Dashboard</a>

			<!-- Botón de colapso para dispositivos pequeños -->
			<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarContent"
					aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<!-- Contenido colapsable del navbar -->
			<div class="collapse navbar-collapse" id="navbarContent">
				<!-- Barra de búsqueda -->
				<form class="d-flex ms-auto mt-2 mt-lg-0">
					<input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
					<button class="btn btn-outline-success" type="submit" aria-label="Search Button">
						<i class='bx bx-search'></i>
					</button>
				</form>

				<!-- Iconos adicionales (notificaciones y perfil) -->
				<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
					<li class="nav-item">
						<a href="#" class="btn btn-outline-secondary d-flex align-items-center justify-content-center ms-3" aria-label="Profile">
							<img src="/webbs/admin/assets/img/personImagen.png" alt="Profile Picture"  width="25" style="border-radius: 20%;">
						</a>
					</li>
				</ul>
			</div>
		</div>
	</nav>
	<!-- NAVBAR -->

	<!-- MAIN -->
	<main>
		<div class="head-title">
			<div class="left">
				<h1>Dashboard</h1>
				<ul class="breadcrumb">
					<li>
						<a href="#">Admin</a>
					</li>
					<li><i class='bx bx-chevron-right' ></i></li>
					<li>
						<a class="active" href="#">Dashboard</a>
					</li>
				</ul>
			</div>
		</div>

		<ul class="box-info">
			<li>
				<i class='bx bxs-calendar-check' ></i>
				<span class="text">
					<%
						int total =ventaTotal.size();  // Variable acumuladora para el total
					%>
						<h3><%=total%></h3>
						<p>transacciones</p>
					</span>
			</li>
			<li>
				<i class='bx bxs-shopping-bag-alt' ></i>
				<span class="text">
					<%
						int totaldeta = DetalleVenta.size(); // Variable acumuladora para el total
					%>
						<h3><%=totaldeta%></h3>
						<p>articulos vendidos</p>
					</span>
			</li>
			<li>
				<i class='bx bxs-dollar-circle' ></i>
				<span class="text">
					<%
						double totalVentas = 0; // Variable acumuladora para el total
						for (Venta v : ventaTotal) {
							totalVentas += v.getTotal(); // Sumar el total de cada venta
						}
					%>
					<h3>S/<%=totalVentas%></h3>
						<p>Total Ventas</p>
					</span>
			</li>
		</ul>


		<div class="table-data">
			<div class="order">
				<div class="head">
					<h3>Ordenes</h3>
				</div>
				<table>
					<thead>
					<tr>
						<th>Id</th>
						<th>Estado</th>
						<th>Fecha</th>
						<th>Total</th>
					</tr>
					</thead>
					<tbody>
					<%for(Venta v: ventaTotal){%>
					<tr>
						<td>
							<p><%=v.getId()%></p>
						</td>
						<td><%=v.getEstado()%></td>
						<td><%=v.getFecha()%></td>
						<td><%=v.getTotal()%></td>
					</tr>
					<%}%>
					</tbody>
				</table>
			</div>

		</div>
	</main>
	<!-- MAIN -->
</section>
<!-- CONTENT -->

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script src="assets/js/script.js"></script>
</body>
</html>
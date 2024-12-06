<%@page contentType="text/html" pageEncoding="UTF-8" import="org.cahuas.webapp.servelet.cabeceras.models.modelo.Producto"%>
<%@page import="java.util.List"%>
<%List<Producto> productos = (List<Producto>) request.getAttribute("productos");%>
<head>

    <title>Document</title>
    <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="/webbs/admin/assets/css/tabla.css"/>
</head>
<body>
<!-- SIDEBAR -->
<section id="sidebar" class="text-black">
    <a href="#" class="brand">
        <i class='bx bxs-cloud fs-3'></i>
        <span class="fs-4 ms-2">SHALOM</span>
    </a>
    <ul class="nav flex-column" id="t">
        <li class="nav-item" >
            <a href="/webbs/admin/index.jsp" >
                <i class='bx bxs-dashboard'></i>
                <span class="text">Dashboard</span>
            </a>
        </li>
        <li class="nav-item" id="actives">
            <a href="/webbs/productos" class="active">
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

<section id="content">
    <!-- Navbar Mejorado con colapsable -->
    <nav class="navbar navbar-expand-lg navbar-light  " style="background-color: #F9F9F9;">
        <div class="container-fluid">

            <!-- Botón para ocultar el sidebar -->
            <i class="bx bx-menu fs-3" id="menu-btn" style="cursor: pointer;" aria-label="Toggle Sidebar"></i>

            <!-- Título de la barra de navegación -->
            <a class="navbar-brand ms-2" href="#">Inventario</a>

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

    <main>
        <div class="head-title">
            <div class="left">
                <ul class="breadcrumb">
                    <li>
                        <a href="#">Admin</a>
                    </li>
                    <li><i class='bx bx-chevron-right' ></i></li>
                    <li>
                        <a class="active" href="#">Inventario</a>
                    </li>
                </ul>
            </div>
        </div>



        <div class="table-data">
            <div class="order">
                <div class="head">

                    <!-- Botones con íconos y colores específicos -->
                    <button class="icon-button excel" title="Exportar a Excel" onclick="exportarExcel()">
                        <i class="fas fa-file-excel"></i>
                    </button>
                    <script>
                        function exportarExcel() {
                            window.location.href = "<%=request.getContextPath()%>/ExportarExcel";
                        }
                    </script>
                    <button class="icon-button pdf" title="Exportar a PDF">
                        <i class="fas fa-file-pdf"></i>
                    </button>
                    <button class="icon-button agregar" title="Agregar nuevo" data-bs-toggle="modal" data-bs-target="#addProductModal">
                        <i class="fas fa-plus-circle"></i>
                    </button>

                    <!-- Barra de búsqueda, alineada a la derecha -->
                    <form action="#" >
                        <div class="form-input">
                            <input type="search" placeholder="Buscar...">
                            <button type="submit" class="search-btn">
                                <i class='bx bx-search'></i>
                            </button>
                        </div>
                    </form>

                </div>
                <table>
                    <thead>
                    <tr>
                        <th>Imagen</th>
                        <th>Nombre</th>
                        <th>Precio</th>
                        <th>Entrada</th>
                        <th>salida</th>
                        <th>acciones</th>

                    </tr>
                    </thead>
                    <tbody>
                    <%for(Producto p: productos){%>
                    <tr>
                        <td>
                            <img src="<%=request.getContextPath() +"/usuario/images/"+p.getRuta_imagen()%>">
                        </td>
                        <td><%=p.getNom()%></td>
                        <td>S/<%=p.getPrecio()%></td>
                        <td>01-10-2021</td>
                        <td>01-10-2021</td>
                        <td>
                            <button class="btn modificar" data-bs-toggle="modal" data-bs-target="#editProductModal"
                                    data-product-id="<%=p.getId()%>"
                                    data-product-name="<%=p.getNom()%>"
                                    data-product-category="<%=p.getCat()%>"
                                    data-product-price="<%=p.getPrecio()%>"
                                    data-product-stock="<%=p.getStock()%>"
                                    data-product-image="<%=p.getRuta_imagen()%>">
                                <i class="fas fa-edit"></i>
                            </button>
                            <button class="btn eliminar" data-bs-toggle="modal" data-bs-target="#deleteProductModal" data-product-id="<%=p.getId()%>">
                                <i class="fas fa-trash-alt"></i>
                            </button>
                        </td>
                    </tr>
                    <%}%>
                    </tbody>
                </table>

            </div>
        </div>
    </main>

</section>
<jsp:include page="modales.jsp"/>
<script src="/webbs/admin/assets/js/script.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
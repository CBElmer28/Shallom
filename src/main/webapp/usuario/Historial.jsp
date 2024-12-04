
<%@page contentType="text/html" pageEncoding="UTF-8" import="org.cahuas.webapp.servelet.cabeceras.models.modelo.Usuario"%>
<% Usuario usu =(Usuario)session.getAttribute("usuario");%>
<%@page import="org.cahuas.webapp.servelet.cabeceras.models.modelo.*"%>
<%@page import="java.util.List"%>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    List<Venta> historialVentas = (List<Venta>) session.getAttribute("historialCompras");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Tienda</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Mukta:300,400,700">
    <link rel="stylesheet" href="fonts/icomoon/style.css">

    <link rel="icon" href="images/icon.png" type="image/x-icon">

    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/magnific-popup.css">
    <link rel="stylesheet" href="css/jquery-ui.css">
    <link rel="stylesheet" href="css/owl.carousel.min.css">
    <link rel="stylesheet" href="css/owl.theme.default.min.css">


    <link rel="stylesheet" href="css/aos.css">

    <link rel="stylesheet" href="css/style.css">

</head>
<body>
<div class="site-wrap">
    <% if(usu == null ||usu.getUser().isEmpty()){%>
    <jsp:include page="nav/sinSession.jsp"/>
    <%}else{%>
    <jsp:include page="nav/usuarioEncabezado.jsp"/>
    <%}%>

</div>
</div>
</div>
<nav class="site-navigation text-right text-md-center" role="navigation">
    <div class="container">
        <ul class="site-menu js-clone-nav d-none d-md-block">
            <li>
                <a href="index.jsp">INICIO</a>
            </li>
            <li>
                <a href="about.jsp">NOSOTROS</a>
            </li>
            <li><a href="/webbs/usuario/productos">TIENDA</a></li>
            <li><a href="contact.jsp">CONTÁCTANOS</a></li>
            <li class="active"><a href="Historial.jsp">HISTORIAL</a></li>
        </ul>
    </div>
</nav>
</header>

<div class="container my-5">
    <h1 class="text-center mb-4">Historial de Compras</h1>
    <% if (historialVentas != null && !historialVentas.isEmpty()) { %>
    <div class="row">
        <% int index = 0;
            for (Venta venta : historialVentas) { %>
        <div class="col-12 mb-3">
            <div class="card p-3 shadow-sm">
                <div class="d-flex align-items-center">
                    <div class="p-2">
                        <i class="fas fa-shopping-bag fa-3x text-primary" style="font-size: 3rem;"></i>
                    </div>
                    <div class="flex-grow-1 pl-3">
                        <h5 class="mb-1">ID de Compra #<%= venta.getId() %></h5>
                        <p class="mb-0 text-muted">Fecha: <%= venta.getFecha() %></p>
                        <p class="mb-0 text-muted">Estado: <%= venta.getEstado() %></p>
                        <p class="mb-0 text-muted">Total: S/ <%= venta.getTotal() %></p>
                    </div>
                    <div>
                        <button class="btn btn-primary" data-toggle="modal" data-target="#detailsModal<%= index %>">
                            Ver detalles
                        </button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Modal para ver los detalles de la venta -->
        <div class="modal fade" id="detailsModal<%= index %>" tabindex="-1" role="dialog"
             aria-labelledby="detailsModalLabel<%= index %>" aria-hidden="true" style="z-index: 2000;">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="detailsModalLabel<%= index %>">Detalles de Venta</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="table-responsive">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th>Imagen</th>
                                    <th>Producto</th>
                                    <th>Cantidad</th>
                                    <th>Subtotal</th>
                                </tr>
                                </thead>
                                <tbody>
                                <%
                                    // Aquí recorremos los detalles de la venta
                                    for (DetalleVenta detalle : venta.getDetalles()) {
                                %>
                                <tr>
                                    <td><img src="<%= request.getContextPath() + "/usuario/images/" + detalle.getProducto().getRuta_imagen() %>"
                                             alt="Producto"
                                             class="img-thumbnail" style="width: 50px; height: 50px;"></td>
                                    <td><%= detalle.getProducto().getNom() %></td>
                                    <td><%= detalle.getCantidad() %></td>
                                    <td>S/ <%= detalle.getSubtotal() %></td>
                                </tr>
                                <% } %>
                                </tbody>
                            </table>
                        </div>
                        <div class="text-right">
                            <h5>Total: S/ <%= venta.getTotal() %></h5>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>
        <% index++;
        } %>
    </div>
    <% } else { %>
    <p class="text-center text-muted mt-5">No hay compras registradas.</p>
    <% } %>
</div>

<jsp:include page="footer/footer.jsp"/>

<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/jquery-ui.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/owl.carousel.min.js"></script>
<script src="js/jquery.magnific-popup.min.js"></script>
<script src="js/aos.js"></script>

<script src="js/main.js"></script>
</body>
</html>


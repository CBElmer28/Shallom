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
                        <h5 class="mb-1">Compra #<%= venta.getId() %></h5>
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


<footer class="site-footer border-top">
    <div class="container">
        <div class="row">
            <div class="col-lg-6 mb-5 mb-lg-0">
                <div class="row">
                    <div class="col-md-12">
                        <h3 class="footer-heading mb-4">Navigations</h3>
                    </div>
                    <div class="col-md-6 col-lg-4">
                        <ul class="list-unstyled">
                            <li><a href="#">Sell online</a></li>
                            <li><a href="#">Features</a></li>
                            <li><a href="#">Shopping cart</a></li>
                            <li><a href="#">Store builder</a></li>
                        </ul>
                    </div>
                    <div class="col-md-6 col-lg-4">
                        <ul class="list-unstyled">
                            <li><a href="#">Mobile commerce</a></li>
                            <li><a href="#">Dropshipping</a></li>
                            <li><a href="#">Website development</a></li>
                        </ul>
                    </div>
                    <div class="col-md-6 col-lg-4">
                        <ul class="list-unstyled">
                            <li><a href="#">Point of sale</a></li>
                            <li><a href="#">Hardware</a></li>
                            <li><a href="#">Software</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-md-6 col-lg-3 mb-4 mb-lg-0">
                <h3 class="footer-heading mb-4">Promo</h3>
                <a href="#" class="block-6">
                    <img src="images/hero_1.jpg" alt="Image placeholder" class="img-fluid rounded mb-4">
                    <h3 class="font-weight-light  mb-0">Finding Your Perfect Shoes</h3>
                    <p>Promo from  nuary 15 &mdash; 25, 2019</p>
                </a>
            </div>
            <div class="col-md-6 col-lg-3">
                <div class="block-5 mb-5">
                    <h3 class="footer-heading mb-4">Contact Info</h3>
                    <ul class="list-unstyled">
                        <li class="address">203 Fake St. Mountain View, San Francisco, California, USA</li>
                        <li class="phone"><a href="tel://23923929210">+2 392 3929 210</a></li>
                        <li class="email">emailaddress@domain.com</li>
                    </ul>
                </div>

                <div class="block-7">
                    <form action="#" method="post">
                        <label for="email_subscribe" class="footer-heading">Subscribe</label>
                        <div class="form-group">
                            <input type="text" class="form-control py-4" id="email_subscribe" placeholder="Email">
                            <input type="submit" class="btn btn-sm btn-primary" value="Send">
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="row pt-5 mt-5 text-center">
            <div class="col-md-12">
                <p>
                    <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                    Copyright &copy;<script data-cfasync="false" src="/cdn-cgi/scripts/5c5dd728/cloudflare-static/email-decode.min.js"></script><script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="icon-heart" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank" class="text-primary">Colorlib</a>
                    <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                </p>
            </div>

        </div>
    </div>
</footer>

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
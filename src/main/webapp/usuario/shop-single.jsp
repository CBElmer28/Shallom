<%@page contentType="text/html" pageEncoding="UTF-8" import="org.cahuas.webapp.servelet.cabeceras.models.modelo.*"%>
<%Producto product = (Producto) request.getAttribute("producto");%>
<% Usuario usu =(Usuario)session.getAttribute("usuario");%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Shoppers &mdash; Colorlib e-Commerce Template</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
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
        <li class="active"><a href="/webbs/usuario/productos">TIENDA</a></li>
        <li><a href="contact.jsp">CONTÁCTANOS</a></li>
        <li><a href="Historial.jsp">HISTORIAL</a></li>
      </ul>
    </div>
  </nav>
  </header>

    <div class="bg-light py-3">
      <div class="container">
        <div class="row">
          <div class="col-md-12 mb-0"><a href="index.jsp">Inicio</a> <span class="mx-2 mb-0">/</span> <strong class="text-black"><%=product.getNom()%></strong></div>
        </div>
      </div>
    </div>

  <div class="site-section">
    <div class="container">
      <div class="row">
        <div class="col-md-6">
          <img src="<%=request.getContextPath() + "/usuario/images/" + product.getRuta_imagen()%>" alt="Image" class="img-fluid">
        </div>
        <div class="col-md-6">
          <h2 class="text-black"><%=product.getNom()%></h2>
          <p><strong class="text-primary h4">$<%=product.getPrecio()%></strong></p>
          <p><strong class="text-primary h4">Stock <%=product.getStock()%></strong></p>

          <!-- Formulario para enviar ID y Cantidad -->
          <form action="<%=request.getContextPath()%>/usuario/carro/agregar" method="get">
            <div class="mb-5">
              <div class="input-group mb-3" style="max-width: 120px;">
                <div class="input-group-prepend">
                  <button class="btn btn-outline-primary js-btn-minus" type="button" >−</button>
                </div>
                <input type="text" name="cantidad" class="form-control text-center" value="1" >
                <div class="input-group-append">
                  <button class="btn btn-outline-primary js-btn-plus" type="button">+</button>
                </div>
              </div>
            </div>
            <!-- Campo oculto para enviar el ID del producto -->
            <input type="hidden" name="id" value="<%=product.getId()%>">
            <button type="submit" class="buy-now btn btn-sm btn-primary">Agregar al carro</button>
          </form>
        </div>
      </div>
    </div>
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
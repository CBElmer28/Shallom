<%@page contentType="text/html" pageEncoding="UTF-8" import="org.cahuas.webapp.servelet.cabeceras.models.modelo.Usuario"%>
<% Usuario usu =(Usuario)session.getAttribute("usuario");%>
<!DOCTYPE html>
<html lang="es">
<head>
  <title>Contáctanos</title>
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
      <li><a href="index.jsp" style="color: black;margin-left: 20px">INICIO</a></li>
      <li><a href="about.jsp" style="color: black;margin-left: 20px">NOSOTROS</a></li>
      <li><a href="/webbs/usuario/productos" style="color: black;margin-left: 20px">TIENDA</a></li>
      <li class="active"><a href="contact.jsp" style="color: black;margin-left: 20px">CONTACTANOS</a></li>
      <li><a href="Historial.jsp">HISTORIAL</a></li>
    </ul>
  </div>
</nav>
</header>

<div class="bg-light py-3">
  <div class="container">
    <div class="row">
      <div class="col-md-12 mb-0"><a href="index.jsp">Inicio</a> <span class="mx-2 mb-0">/</span> <strong class="text-black">Contactanos</strong></div>
    </div>
  </div>
</div>

<div class="site-section">
  <div class="container">
    <div class="row">
      <div class="col-md-12">
        <h2 class="h3 mb-3 text-black">Contáctenos</h2>
      </div>
      <div class="col-md-7">

        <form action="#" method="post">

          <div class="p-3 p-lg-5 border">
            <div class="form-group row">
              <div class="col-md-6">
                <label for="c_fname" class="text-black">Nombre <span class="text-danger">*</span></label>
                <input type="text" class="form-control" id="c_fname" name="c_fname">
              </div>
              <div class="col-md-6">
                <label for="c_lname" class="text-black">Apellido <span class="text-danger">*</span></label>
                <input type="text" class="form-control" id="c_lname" name="c_lname">
              </div>
            </div>
            <div class="form-group row">
              <div class="col-md-12">
                <label for="c_email" class="text-black">Correo <span class="text-danger">*</span></label>
                <input type="email" class="form-control" id="c_email" name="c_email" placeholder="">
              </div>
            </div>
            <div class="form-group row">
              <div class="col-md-12">
                <label for="c_subject" class="text-black">Asunto </label>
                <input type="text" class="form-control" id="c_subject" name="c_subject">
              </div>
            </div>

            <div class="form-group row">
              <div class="col-md-12">
                <label for="c_message" class="text-black">Mensaje </label>
                <textarea name="c_message" id="c_message" cols="30" rows="7" class="form-control"></textarea>
              </div>
            </div>
            <div class="form-group row">
              <div class="col-lg-12">
                <input type="submit" class="btn btn-primary btn-lg btn-block" value="Send Message">
              </div>
            </div>
          </div>
        </form>
      </div>
      <div class="col-md-5 ml-auto">
        <div class="p-4 border mb-3">
          <span class="d-block text-primary h6 text-uppercase">Lima</span>
          <p class="mb-0">Calle Los Gerundios 123, Los Olivos, Lima, Perú</p>
        </div>
      </div>
    </div>
  </div>
</div>

<footer class="site-footer border-top">
  <div class="container">
    <div class="row">
      <div class="col-lg-6 mb-5 mb-lg-0">
        <div class="row">
          <div class="col-md-12">
            <h3 class="footer-heading mb-4">Navegacion</h3>
          </div>
          <div class="col-md-6 col-lg-4">
            <ul class="list-unstyled">
              <li><a href="#">Inicio</a></li>
              <li><a href="about.jsp">Nosotros</a></li>
              <li><a href="shop.jsp">Productos</a></li>
              <li><a href="contact.jsp">Contactanos</a></li>
            </ul>
          </div>
          <div class="col-md-6 col-lg-4">
            <ul class="list-unstyled">
              <li><a href="#">Cuadernos</a></li>
              <li><a href="#">Temperas</a></li>
              <li><a href="#">Plumones</a></li>
            </ul>
          </div>
          <div class="col-md-6 col-lg-4">
            <ul class="list-unstyled">
              <li><a href="#">Tajadores</a></li>
              <li><a href="#">Borradores</a></li>
              <li><a href="#">Tijeras</a></li>
            </ul>
          </div>
        </div>
      </div>
      <div class="col-md-6 col-lg-3 mb-4 mb-lg-0">
        <h3 class="footer-heading mb-4">Promoción del Mes!</h3>
        <a href="#" class="block-6">
          <img src="images/promo3.jpg" alt="Image placeholder" class="img-fluid rounded mb-4">
          <h3 class="font-weight-light  mb-0">Mejor precio, Más comodo.</h3>
          <p>promo por diciembre 11 &mdash; 30, 2024</p>
        </a>
      </div>
      <div class="col-md-6 col-lg-3">
        <div class="block-5 mb-5">
          <h3 class="footer-heading mb-4">Información de Contacto</h3>
          <ul class="list-unstyled">
            <li class="address">Mz I Lote 13 Usares de Junín</li>
            <li class="phone"><a href="tel://997621074">+51 997621074</a></li>
            <li class="email">shalom@gmail.com</li>
          </ul>
        </div>

        <div class="block-7">
          <form action="#" method="post">
            <label for="email_subscribe" class="footer-heading">Enviar Correo</label>
            <div class="form-group">
              <input type="text" class="form-control py-4" id="email_subscribe" placeholder="Email">
              <input type="submit" class="btn btn-sm btn-primary" value="Send">
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</footer>
</div>

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

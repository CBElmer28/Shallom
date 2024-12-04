<%@page contentType="text/html" pageEncoding="UTF-8" import="org.cahuas.webapp.servelet.cabeceras.models.modelo.Usuario"%>
<% Usuario usu =(Usuario)session.getAttribute("usuario");%>
<!DOCTYPE html>
<html lang="en">

<head>
  <title>Shalom &mdash; Colorlib e-Commerce Template</title>
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
      <li><a href="index.jsp" style="color: black;margin-left: 20px">INICIO</a></li>
      <li><a href="about.jsp" style="color: black; margin-left: 20px;">NOSOTROS</a></li>
      <li><a href="/webbs/usuario/productos" style="margin-left: 20px">TIENDA</a></li>
      <li><a href="contact.jsp" style="margin-left: 20px">CONTACTANOS</a></li>
      <li><a href="Historial.jsp">HISTORIAL</a></li>
    </ul>
  </div>
</nav>
</header>

<div class="site-blocks-cover" style="background-image: url(images/blog_12.png);" data-aos="fade">
  <div class="container" style="position: relative; left: -300px;">
    <div class="row align-items-start align-items-md-center justify-content-end">
      <div class="col-md-5 text-center text-md-left pt-5 pt-md-0">
        <!-- Contenedor para el fondo transparente -->
        <div style="background: rgba(0, 0, 0, 0.5); padding: 20px; border-radius: 10px;">
          <h1 class="mb-2" style="text-align: center; font-size: 5.4rem; color: #ffffff; text-shadow: 2px 2px 7px yellow;">
            TU PORTAL DE LIBROS Y MÁS 😎
          </h1>
          <div class="intro-text text-center">
            <p class="mb-4" style="font-weight: bold; font-size: 2.3rem; color: white; text-shadow: 2px 2px 4px #000000;">
              Elige entre la gran variedad de productos para la escuela, universidad y oficina.
            </p>
            <p>
              <a <a href="/webbs/usuario/productos" class="btn btn-sm btn-primary">Comenzar</a>
            </p>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>



<div class="site-section site-section-sm site-blocks-1">
  <div class="container">
    <div class="row">
      <div class="col-md-6 col-lg-4 d-lg-flex mb-4 mb-lg-0 pl-4" data-aos="fade-up" data-aos-delay="">
        <div class="icon mr-4 align-self-start">
          <span class="icon-truck"></span>
        </div>
        <div class="text">
          <h2 class="text-uppercase">envio gratis</h2>
          <p>Disfruta de envío gratuito en pedidos superiores a S/ 150. Llevamos tus productos a la puerta de tu casa sin costo adicional.</p>
        </div>
      </div>
      <div class="col-md-6 col-lg-4 d-lg-flex mb-4 mb-lg-0 pl-4" data-aos="fade-up" data-aos-delay="100">
        <div class="icon mr-4 align-self-start">
          <span class="icon-refresh2"></span>
        </div>
        <div class="text">
          <h2 class="text-uppercase">retorno gratis</h2>
          <p>Si no estás satisfecho, tienes hasta 30 días para devolver tu compra de manera simple y sin complicaciones.</p>
        </div>
      </div>
      <div class="col-md-6 col-lg-4 d-lg-flex mb-4 mb-lg-0 pl-4" data-aos="fade-up" data-aos-delay="200">
        <div class="icon mr-4 align-self-start">
          <span class="icon-help"></span>
        </div>
        <div class="text">
          <h2 class="text-uppercase">soporte siempre</h2>
          <p>Estamos disponibles para ayudarte a cualquier hora, los 7 días de la semana. Contáctanos por chat, correo o llamada.</p>
        </div>
      </div>
    </div>
  </div>
</div>

<div class="site-section site-blocks-2">
  <div class="container">
    <div class="row">
      <div class="col-sm-6 col-md-6 col-lg-4 mb-4 mb-lg-0" data-aos="fade" data-aos-delay="">
        <a class="block-2-item" href="#">
          <figure class="image">
            <img src="images/tijera.jpg" alt="" class="img-fluid">
          </figure>
          <div class="text">
            <span class="text-uppercase">Colecciones</span>
            <h3>Tijeras</h3>
          </div>
        </a>
      </div>
      <div class="col-sm-6 col-md-6 col-lg-4 mb-5 mb-lg-0" data-aos="fade" data-aos-delay="100">
        <a class="block-2-item" href="#">
          <figure class="image">
            <img src="images/faberlapicero.jpg"  alt="" class="img-fluid">
          </figure>
          <div class="text">
            <span class="text-uppercase">Colecciones</span>
            <h3>Lapiceros</h3>
          </div>
        </a>
      </div>
      <div class="col-sm-6 col-md-6 col-lg-4 mb-5 mb-lg-0" data-aos="fade" data-aos-delay="200">
        <a class="block-2-item" href="#">
          <figure class="image">
            <img src="images/cuaderno1.jpg" alt="" style="width: 100%; height: auto;" class="img-fluid">
          </figure>
          <div class="text">
            <span class="text-uppercase">Colecciones</span>
            <h3>Cuadernos</h3>
          </div>
        </a>
      </div>
    </div>
  </div>
</div>

<div class="site-section block-3 site-blocks-2 bg-light">
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-md-7 site-section-heading text-center pt-4">
        <h2>Productos Más Destacados</h2>
      </div>
    </div>
    <div class="row">
      <div class="col-md-12">
        <div class="nonloop-block-3 owl-carousel">
          <div class="item">
            <div class="block-4 text-center">
              <figure class="block-4-image">
                <img src="images/faber.jpg" alt="Image placeholder" class="img-fluid">
              </figure>
              <div class="block-4-text p-4">
                <h3><a href="#">Tempera</a></h3>
                <p class="mb-0">Tempera rosada 250ml Faber Castell</p>
                <p class="text-primary font-weight-bold">S/. 8.50</p>
              </div>
            </div>
          </div>
          <div class="item">
            <div class="block-4 text-center">
              <figure class="block-4-image">
                <img src="images/temperaartesco.jpg" alt="Image placeholder" class="img-fluid">
              </figure>
              <div class="block-4-text p-4">
                <h3><a href="#">Tempera</a></h3>
                <p class="mb-0">Tempera Artesco Escarchada x 6 unid. </p>
                <p class="text-primary font-weight-bold">S/. 11.90</p>
              </div>
            </div>
          </div>
          <div class="item">
            <div class="block-4 text-center">
              <figure class="block-4-image">
                <img src="images/boligrafos.jpg" alt="Image placeholder" class="img-fluid">
              </figure>
              <div class="block-4-text p-4">
                <h3><a href="#">Boligrafo</a></h3>
                <p class="mb-0">Bolígrafos Faber Castell X 12 unid.</p>
                <p class="text-primary font-weight-bold">S/. 12.50</p>
              </div>
            </div>
          </div>

          <div class="item">
            <div class="block-4 text-center">
              <figure class="block-4-image">
                <img src="images/plumonesartica2.jpg" alt="Image placeholder" class="img-fluid">
              </figure>
              <div class="block-4-text p-4">
                <h3><a href="#">Plumones</a></h3>
                <p class="mb-0">Plumón Arti Creativo Jumbo Mamut X 12 Und</p>
                <p class="text-primary font-weight-bold">S/. 15.00</p>

              </div>
            </div>
          </div>
          <div class="item">
            <div class="block-4 text-center">
              <figure class="block-4-image">
                <img src="images/lapicero4.jpg" alt="Image placeholder" class="img-fluid">
              </figure>
              <div class="block-4-text p-4">
                <h3><a href="#">Lapiceros</a></h3>
                <p class="mb-0">Paquete de Lapiceros Typo X 4 Und</p>
                <p class="text-primary font-weight-bold">S/. 15.00</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<div class="site-section block-8">
  <div class="container">
    <div class="row justify-content-center  mb-5">
      <div class="col-md-7 site-section-heading text-center pt-4">
        <h2>GRAN VENTA!</h2>
      </div>
    </div>
    <div class="row align-items-center">
      <div class="col-md-12 col-lg-7 mb-5">
        <a href="#"><img src="images/estante.png" alt="Image placeholder" class="img-fluid rounded"></a>
      </div>
      <div class="col-md-12 col-lg-5 text-center pl-md-5">
        <h2><a href="#">50% de descuento en útiles escolares</a></h2>
        <p class="post-meta mb-4">Por <a href="#">Shalom</a> <span class="block-8-sep">&bullet;</span> septiembre 29, 2024</p>
        <p>Encuentra los mejores descuentos en libros de texto, novelas, y material de oficina. ¡Aprovecha estas ofertas por tiempo limitado!
        </p>
        <p><a href="/webbs/usuario/productos" class="btn btn-primary btn-sm">Compra Ahora</a></p>
      </div>
    </div>
  </div>
</div>


  <jsp:include page="footer/footer.jsp"/>


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

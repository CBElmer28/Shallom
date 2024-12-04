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

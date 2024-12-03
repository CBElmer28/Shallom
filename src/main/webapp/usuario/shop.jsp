<%@page contentType="text/html" pageEncoding="UTF-8" import="org.cahuas.webapp.servelet.cabeceras.models.modelo.*"%>
<%@page import="java.util.List"%>
<% Usuario usu =(Usuario)session.getAttribute("usuario");%>
<%List<Producto> productos = (List<Producto>) request.getAttribute("productos");%>
<!DOCTYPE html>
<html lang="es">
<head>
  <title>Tienda</title>
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
        <div class="col-md-12 mb-0"><a href="index.html">Inicio</a> <span class="mx-2 mb-0">/</span> <strong class="text-black">Tienda</strong></div>
      </div>
    </div>
  </div>

  <div class="site-section">
    <div class="container">

      <div class="row mb-5">
        <div class="col-md-9 order-2">

          <div class="row">
            <div class="col-md-12 mb-5">
              <div class="float-md-left mb-4"><h2 class="text-black h5">Nuestros productos</h2></div>
              <div class="d-flex">
                <div class="dropdown mr-1 ml-md-auto">
                  <button type="button" class="btn btn-secondary btn-sm dropdown-toggle" id="dropdownMenuOffset" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Latest
                  </button>
                  <div class="dropdown-menu" aria-labelledby="dropdownMenuOffset">
                    <a class="dropdown-item" href="#">Men</a>
                    <a class="dropdown-item" href="#">Women</a>
                    <a class="dropdown-item" href="#">Children</a>
                  </div>
                </div>
                <div class="btn-group">
                  <button type="button" class="btn btn-secondary btn-sm dropdown-toggle" id="dropdownMenuReference" data-toggle="dropdown">Reference</button>
                  <div class="dropdown-menu" aria-labelledby="dropdownMenuReference">
                    <a class="dropdown-item" href="#">Relevance</a>
                    <a class="dropdown-item" href="#">Name, A to Z</a>
                    <a class="dropdown-item" href="#">Name, Z to A</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#">Price, low to high</a>
                    <a class="dropdown-item" href="#">Price, high to low</a>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="row mb-5">
             <!-- AQUI VAN LOS PRODUCTOS-->
            <%for(Producto p: productos){%>
            <div class="col-sm-6 col-lg-4 mb-4" data-aos="fade-up">
              <div class="block-4 text-center border">
                <figure class="block-4-image">
                  <a href="/webbs/usuario/producto?id=<%=p.getId()%>"><img src="<%=request.getContextPath() +"/usuario/images/"+p.getRuta_imagen()%>" alt="Image placeholder" class="img-fluid"></a>
                </figure>
                <div class="block-4-text p-4">
                  <h3><a href="/webbs/usuario/producto?id=<%=p.getId()%>"><%=p.getNom()%></a></h3>
                  <p class="mb-0">Finding perfect t-shirt</p>
                  <p class="text-primary font-weight-bold"><%=p.getPrecio()%></p>
                </div>
              </div>
            </div>
            <%}%>

          </div>
          <div class="row" data-aos="fade-up">
            <div class="col-md-12 text-center">
              <div class="site-block-27">
                <ul>
                  <li><a href="#">&lt;</a></li>
                  <li class="active"><span>1</span></li>
                  <li><a href="#">2</a></li>
                  <li><a href="#">3</a></li>
                  <li><a href="#">4</a></li>
                  <li><a href="#">5</a></li>
                  <li><a href="#">&gt;</a></li>
                </ul>
              </div>
            </div>
          </div>
        </div>

        <div class="col-md-3 order-1 mb-5 mb-md-0">
          <div class="border p-4 rounded mb-4">
            <h3 class="mb-3 h6 text-uppercase text-black d-block">Categories</h3>
            <ul class="list-unstyled mb-0">
              <li class="mb-1"><a href="#" class="d-flex"><span>Papeles</span> <span class="text-black ml-auto">(2,220)</span></a></li>
              <li class="mb-1"><a href="#" class="d-flex"><span>niños</span> <span class="text-black ml-auto">(2,550)</span></a></li>
              <li class="mb-1"><a href="#" class="d-flex"><span>oficina</span> <span class="text-black ml-auto">(2,124)</span></a></li>
              <li class="mb-1"><a href="#" class="d-flex"><span>Estuches</span> <span class="text-black ml-auto">(2,124)</span></a></li>
            </ul>
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
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="org.cahuas.webapp.servelet.cabeceras.models.modelo.Usuario" %>
<%@ page import="org.cahuas.webapp.servelet.cabeceras.models.modelo.Carro" %>
<%@ page import="org.cahuas.webapp.servelet.cabeceras.models.modelo.ItemCarro" %>
<%@ page import="org.cahuas.webapp.servelet.cabeceras.models.modelo.DatalleProducto" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<% Usuario usu =(Usuario)session.getAttribute("usuario");%>
<%Carro carro = (Carro) session.getAttribute("carro");%>
<html lang="en">
  <head>
    <title>Shoppers &mdash; Colorlib e-Commerce Template</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Mukta:300,400,700"> 
    <link rel="stylesheet" href="fonts/icomoon/style.css">

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
        <li><a href="Historial.jsp">HISTORIAL</a></li>
      </ul>
    </div>
  </nav>
  </header>

    <div class="bg-light py-3">
      <div class="container">
        <div class="row">
          <div class="col-md-12 mb-0"><a href="index.html">Home</a> <span class="mx-2 mb-0">/</span> <strong class="text-black">Cart</strong></div>
        </div>
      </div>
    </div>

    <div class="site-section">
      <div class="container">
        <div class="row mb-5">
          <form class="col-md-12" method="post">
            <div class="site-blocks-table">
              <table class="table table-bordered">
                <thead>
                  <tr>
                    <th class="product-thumbnail">Imagen</th>
                    <th class="product-name">Producto</th>
                    <th class="product-price">Precio</th>
                    <th class="product-quantity">Cantidad</th>
                    <th class="product-total">Total</th>
                    <th class="product-remove">Eliminar</th>
                  </tr>
                </thead>
                <tbody>
                <%for(ItemCarro item: carro.getItems()){%>
                  <tr>
                    <td class="product-thumbnail">
                      <img src="<%=request.getContextPath() +"/usuario/images/"+item.getProducto().getRuta_imagen()%>" alt="Image" class="img-fluid">
                    </td>
                    <td class="product-name">
                      <h2 class="h5 text-black"><%=item.getProducto().getNom()%></h2>
                    </td>
                    <td>$<%=item.getProducto().getPrecio()%></td>
                    <td>
                      <div class="input-group mb-3" style="max-width: 120px;">
                        <div class="input-group-prepend">
                          <button class="btn btn-outline-primary js-btn-minus" type="button" >&minus;</button>
                        </div>
                        <input type="text" class="form-control text-center" name="cant_<%=item.getProducto().getId()%>" value="<%=item.getCantidad()%>" placeholder="" aria-label="Example text with button addon" aria-describedby="button-addon1">
                        <div class="input-group-append">
                          <button class="btn btn-outline-primary js-btn-plus" type="button">&plus;</button>
                        </div>
                      </div>

                    </td>
                    <td>$<%=carro.getTotalProduc(Long.toString(item.getProducto().getId()))%></td>
                    <td><a href="#" class="btn btn-primary btn-sm" value="<%=item.getProducto().getId()%>">X</a></td>
                  </tr>
                  <%}%>
                </tbody>
              </table>
            </div>
          </form>
        </div>

        <div class="row">
          <div class="col-md-6">
            <div class="row mb-5">
              <div class="col-md-6 mb-3 mb-md-0">
                <button class="btn btn-primary btn-sm btn-block">Actualizar carro</button>
              </div>
              <div class="col-md-6">
                <button class="btn btn-outline-primary btn-sm btn-block">Continue Comprando</button>
              </div>
            </div>
          </div>
          <div class="col-md-6 pl-5">
            <div class="row justify-content-end">
              <div class="col-md-7">
                <div class="row">
                  <div class="col-md-12 text-right border-bottom mb-5">
                    <h3 class="text-black h4 text-uppercase">Precio total</h3>
                  </div>
                </div>
                <div class="row mb-3">
                  <div class="col-md-6">
                    <span class="text-black">Subtotal</span>
                  </div>
                  <div class="col-md-6 text-right">
                    <strong class="text-black">$<%=carro.getTotal()%></strong>
                  </div>
                </div>
                <div class="row mb-5">
                  <div class="col-md-6">
                    <span class="text-black">Total</span>
                  </div>
                  <div class="col-md-6 text-right">
                    <strong class="text-black">$<%=carro.getTotal()%></strong>
                  </div>
                </div>

                <div class="row">
                  <div class="col-md-12">
                    <button class="btn btn-primary btn-lg py-3 btn-block" onclick="window.location='checkout.jsp'">Procede Pago</button>
                  </div>
                </div>
              </div>
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
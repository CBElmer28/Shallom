<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="org.cahuas.webapp.servelet.cabeceras.models.modelo.Usuario" %>
<%@ page import="org.cahuas.webapp.servelet.cabeceras.models.modelo.Carro" %>
<%@ page import="org.cahuas.webapp.servelet.cabeceras.models.modelo.ItemCarro" %>
<% Usuario usu =(Usuario)session.getAttribute("usuario");%>
<%Carro carro = (Carro) session.getAttribute("carro");%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Shalom &mdash; Checkout</title>
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
          <div class="col-md-12 mb-0"><a href="index.jsp">Inicio</a> <span class="mx-2 mb-0">/</span> <a href="cart.jsp">Carrito</a> <span class="mx-2 mb-0">/</span> <strong class="text-black">Checkout</strong></div>
        </div>
      </div>
    </div>

    <div class="site-section">
      <div class="container">
        <div class="row mb-5">
          <div class="col-md-12">
            <div class="border p-4 rounded" role="alert">
              Returning customer? <a href="#">Click here</a> to login
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6 mb-5 mb-md-0">
            <h2 class="h3 mb-3 text-black">Detalles</h2>
            <div class="p-3 p-lg-5 border">
              <div class="form-group">
                <label for="c_country" class="text-black">Ciudad <span class="text-danger">*</span></label>
                <select id="c_country" class="form-control">
                  <option value="1">Seleciona una ciudad</option>
                  <option value="2">Arequipa</option>
                  <option value="3">Cusco</option>
                  <option value="4">Trujillo</option>
                  <option value="5">Chiclayo</option>
                  <option value="6">Piura</option>
                  <option value="7">Iquitos</option>
                  <option value="8">Huancayo</option>
                  <option value="9">Tacna</option>
                  <option value="10">Puno</option>
                  <option value="11">Lima</option>
                </select>
              </div>
              <div class="form-group row">
                <div class="col-md-6">
                  <label for="c_fname" class="text-black">Nombres <span class="text-danger">*</span></label>
                  <input type="text" class="form-control" id="c_fname" name="c_fname">
                </div>
                <div class="col-md-6">
                  <label for="c_lname" class="text-black">Apellidos <span class="text-danger">*</span></label>
                  <input type="text" class="form-control" id="c_lname" name="c_lname">
                </div>
              </div>

              <div class="form-group row">
                <div class="col-md-12">
                  <label for="c_companyname" class="text-black">Empresa </label>
                  <input type="text" class="form-control" id="c_companyname" name="c_companyname">
                </div>
              </div>

              <div class="form-group row">
                <div class="col-md-12">
                  <label for="c_address" class="text-black">Correo <span class="text-danger">*</span></label>
                  <input type="text" class="form-control" id="c_address" name="c_address" placeholder="Street address">
                </div>
              </div>

              <div class="form-group row">
                <div class="col-md-6">
                  <label for="c_state_country" class="text-black">N.Tarjeta <span class="text-danger">*</span></label>
                  <input type="text" class="form-control" id="c_state_country" name="c_state_country">
                </div>
                <div class="col-md-6">
                  <label for="c_postal_zip" class="text-black">Vence <span class="text-danger">*</span></label>
                  <input type="text" class="form-control" id="c_postal_zip" name="c_postal_zip">
                </div>
              </div>
            </div>
          </div>
          <div class="col-md-6">
            <div class="row mb-5">
              <div class="col-md-12">
                <h2 class="h3 mb-3 text-black">Tu carrito</h2>
                <div class="p-3 p-lg-5 border">
                  <table class="table site-block-order-table mb-5">
                    <thead>
                      <th>Producto</th>
                      <th>Total</th>
                    </thead>
                    <tbody>
                    <%for(ItemCarro item: carro.getItems()){%>
                      <tr>
                        <td><%=item.getProducto().getNom()%> <strong class="mx-2">x</strong> <%=item.getCantidad()%></td>
                        <td><%=carro.getTotalProduc(Long.toString(item.getProducto().getId()))%></td>
                      </tr>
                    <%}%>
                      <tr>
                        <td class="text-black font-weight-bold"><strong>Total</strong></td>
                        <td class="text-black font-weight-bold"><strong>$<%=carro.getTotal()%></strong></td>
                      </tr>
                    </tbody>
                  </table>
                  <div class="form-group">
                    <button  class="btn btn-primary btn-lg py-3 btn-block" onclick="pagarOrden()">Pagar</button>
                  </div>
                  <script>
                    function pagarOrden() {
                      // Crear un enlace oculto para descargar el PDF
                      const link = document.createElement('a');
                      link.href = "<%=request.getContextPath()%>/usuario/carro/exportarproduc";
                      link.target = '_blank';
                      document.body.appendChild(link);
                      link.click();
                      document.body.removeChild(link);
                      setTimeout(() => {
                        window.location.href = 'thankyou.jsp';
                      }, 500); // Ajusta el tiempo en milisegundos si es necesario
                    }
                  </script>

                </div>
              </div>
            </div>

          </div>
        </div>
        <!-- </form> -->
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
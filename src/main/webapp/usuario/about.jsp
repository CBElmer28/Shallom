<%@ page import="org.cahuas.webapp.servelet.cabeceras.models.modelo.Usuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Usuario usu =(Usuario)session.getAttribute("usuario");%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Shalom &mdash; Nosotros</title>
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
            <li class="active">
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
                <div class="col-md-12 mb-0"><a href="index.jsp">Inicio</a> <span class="mx-2 mb-0">/</span> <strong class="text-black">Nosotros</strong></div>
            </div>
        </div>
    </div>

    <div class="site-section border-bottom" data-aos="fade">
        <div class="container">
            <div class="row mb-5">
                <div class="col-md-6">
                    <div class="block-16">
                        <figure>
                            <img src="images/local.jpg" alt="Image placeholder" class="img-fluid rounded" style="height: 450px; width: 800px">


                        </figure>
                    </div>
                </div>
                <div class="col-md-1"></div>
                <div class="col-md-5">


                    <div class="site-section-heading pt-3 mb-4">
                        <h2 class="text-black" style="text-align: center">Misión</h2>
                    </div>
                    <p class="text-center">Desarrollar una aplicación web eficiente y amigable que permita a la libreria gestionar y expandir su negocio, facilitando la venta de productos tanto al por mayor como al por menor, con un enfoque en la satisfaccion del cliente y la calidad del servicio que permita atraer a mas clientela hacia el negocio. </p>

                    <div class="site-section-heading pt-3 mb-4" >
                        <h2 class="text-black" style="text-align: center">Visión</h2>
                    </div>
                    <p class="text-center">Ser la tienda líder en ventas de productos de librería a nivel nacional, ofreciendo una experiencia de compra fácil, accesible y personalizada para clientes de todas las edades y necesidades, apoyando la difusión de la cultura y la educación a través de una amplia gama de productos.</p>

                </div>
            </div>
        </div>
    </div>

    <div class="site-section border-bottom" data-aos="fade">
        <div class="container">
            <div class="row justify-content-center mb-5">
                <div class="col-md-7 site-section-heading text-center pt-4">
                    <h2>Nuestro Equipo</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6 col-lg-3">
                    <div class="block-38 text-center">
                        <div class="block-38-img">
                            <div class="block-38-header">
                                <img src="images/person_3.jpg" alt="Image placeholder" class="mb-4">
                                <h3 class="block-38-heading h4">Keyen Cahuas</h3>
                                <p class="block-38-subheading">Desarrollador Backend</p>
                            </div>
                            <div class="block-38-body">
                                <p>Enfocado en crear sistemas escalables y seguros. Especialista en bases de datos y diseño de APIs. Estudiante de la Universidad Tecnológica del Perú. </p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 col-lg-3">
                    <div class="block-38 text-center">
                        <div class="block-38-img">
                            <div class="block-38-header">
                                <img src="images/person_4.jpg" alt="Image placeholder" class="mb-4">
                                <h3 class="block-38-heading h4">Jonathan Soto</h3>
                                <p class="block-38-subheading">Desarrollador Frontend</p>
                            </div>
                            <div class="block-38-body">
                                <p>Diseña interfaces modernas y funcionales. Con experiencia en React y Angular, crea soluciones visuales efectivas. Estudiante de la Universidad Tecnológica del Perú.  </p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 col-lg-3">
                    <div class="block-38 text-center">
                        <div class="block-38-img">
                            <div class="block-38-header">
                                <img src="images/person_4.jpg" alt="Image placeholder" class="mb-4">
                                <h3 class="block-38-heading h4">Jhoy Navarro</h3>
                                <p class="block-38-subheading">Desarrollador Frontend</p>
                            </div>
                            <div class="block-38-body">
                                <p>Apasionado por el diseño de aplicaciones web responsivas. Experta en animaciones y maquetacion. Estudiante de la Universidad Tecnológica del Perú. </p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 col-lg-3">
                    <div class="block-38 text-center">
                        <div class="block-38-img">
                            <div class="block-38-header">
                                <img src="images/person_3.jpg" alt="Image placeholder" class="mb-4">
                                <h3 class="block-38-heading h4">Elmer Calizaya</h3>
                                <p class="block-38-subheading">Desarrollador Backend</p>
                            </div>
                            <div class="block-38-body">
                                <p>Desarrolla sistemas eficientes y optimiza procesos. Conoce integración de servicios y creación de APIs. Estudiante de la Universidad Tecnológica del Perú. </p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 col-lg-3 custom-margin-left">
                    <div class="block-38 text-center">
                        <div class="block-38-img">
                            <div class="block-38-header">
                                <img src="images/person_4.jpg" alt="Image placeholder" class="mb-4">
                                <h3 class="block-38-heading h4">Juan Palomino</h3>
                                <p class="block-38-subheading">Desarrollador Frontend</p>
                            </div>
                            <div class="block-38-body">
                                <p>Crea sitios web modernos y accesibles. Aplica tecnologias innovadoras para mejorar la experiencia del usuario. Estudiante de la Universidad Tecnológica del Perú.</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <div class="site-section site-section-sm site-blocks-1 border-0" data-aos="fade">
        <div class="container">
            <div class="row">
                <div class="col-md-6 col-lg-4 d-lg-flex mb-4 mb-lg-0 pl-4" data-aos="fade-up" data-aos-delay="">
                    <div class="icon mr-4 align-self-start">
                        <span class="icon-truck"></span>
                    </div>
                    <div class="text">
                        <h2 class="text-uppercase">envío gratis</h2>
                        <p>Disfruta de envío gratuito en pedidos superiores a S/ 150. Llevamos tus productos a la puerta de tu casa sin costo adicional.</p>
                    </div>
                </div>
                <div class="col-md-6 col-lg-4 d-lg-flex mb-4 mb-lg-0 pl-4" data-aos="fade-up" data-aos-delay="100">
                    <div class="icon mr-4 align-self-start">
                        <span class="icon-refresh2"></span>
                    </div>
                    <div class="text">
                        <h2 class="text-uppercase">retorno gratis</h2>
                        <p>Si no estás satisfecho, tienes hasta 30 dias para devolver tu compra de manera simple y sin complicaciones.</p>
                    </div>
                </div>
                <div class="col-md-6 col-lg-4 d-lg-flex mb-4 mb-lg-0 pl-4" data-aos="fade-up" data-aos-delay="200">
                    <div class="icon mr-4 align-self-start">
                        <span class="icon-help"></span>
                    </div>
                    <div class="text">
                        <h2 class="text-uppercase">soporte siempre</h2>
                        <p>Estamos disponibles para ayudarte a cualquier hora, los 7 dias de la semana. Contáctanos por chat, correo o llamada.</p>
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
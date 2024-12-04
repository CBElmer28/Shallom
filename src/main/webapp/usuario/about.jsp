
<%@ page import="org.cahuas.webapp.servelet.cabeceras.models.modelo.Usuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                <div class="col-md-12 mb-0"><a href="index.html">Home</a> <span class="mx-2 mb-0">/</span> <strong class="text-black">About</strong></div>
            </div>
        </div>
    </div>

    <div class="site-section border-bottom" data-aos="fade">
        <div class="container">
            <div class="row mb-5">
                <div class="col-md-6">
                    <div class="block-16">
                        <figure>
                            <img src="images/blog_1.jpg" alt="Image placeholder" class="img-fluid rounded">
                            <a href="https://vimeo.com/channels/staffpicks/93951774" class="play-button popup-vimeo"><span class="ion-md-play"></span></a>

                        </figure>
                    </div>
                </div>
                <div class="col-md-1"></div>
                <div class="col-md-5">


                    <div class="site-section-heading pt-3 mb-4">
                        <h2 class="text-black">How We Started</h2>
                    </div>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eius repellat, dicta at laboriosam, nemo exercitationem itaque eveniet architecto cumque, deleniti commodi molestias repellendus quos sequi hic fugiat asperiores illum. Atque, in, fuga excepturi corrupti error corporis aliquam unde nostrum quas.</p>
                    <p>Accusantium dolor ratione maiores est deleniti nihil? Dignissimos est, sunt nulla illum autem in, quibusdam cumque recusandae, laudantium minima repellendus.</p>

                </div>
            </div>
        </div>
    </div>

    <div class="site-section border-bottom" data-aos="fade">
        <div class="container">
            <div class="row justify-content-center mb-5">
                <div class="col-md-7 site-section-heading text-center pt-4">
                    <h2>The Team</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6 col-lg-3">

                    <div class="block-38 text-center">
                        <div class="block-38-img">
                            <div class="block-38-header">
                                <img src="images/person_1.jpg" alt="Image placeholder" class="mb-4">
                                <h3 class="block-38-heading h4">Elizabeth Graham</h3>
                                <p class="block-38-subheading">CEO/Co-Founder</p>
                            </div>
                            <div class="block-38-body">
                                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Vitae aut minima nihil sit distinctio recusandae doloribus ut fugit officia voluptate soluta. </p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 col-lg-3">
                    <div class="block-38 text-center">
                        <div class="block-38-img">
                            <div class="block-38-header">
                                <img src="images/person_2.jpg" alt="Image placeholder" class="mb-4">
                                <h3 class="block-38-heading h4">Jennifer Greive</h3>
                                <p class="block-38-subheading">Co-Founder</p>
                            </div>
                            <div class="block-38-body">
                                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Vitae aut minima nihil sit distinctio recusandae doloribus ut fugit officia voluptate soluta. </p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 col-lg-3">
                    <div class="block-38 text-center">
                        <div class="block-38-img">
                            <div class="block-38-header">
                                <img src="images/person_3.jpg" alt="Image placeholder" class="mb-4">
                                <h3 class="block-38-heading h4">Patrick Marx</h3>
                                <p class="block-38-subheading">Marketing</p>
                            </div>
                            <div class="block-38-body">
                                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Vitae aut minima nihil sit distinctio recusandae doloribus ut fugit officia voluptate soluta. </p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 col-lg-3">
                    <div class="block-38 text-center">
                        <div class="block-38-img">
                            <div class="block-38-header">
                                <img src="images/person_4.jpg" alt="Image placeholder" class="mb-4">
                                <h3 class="block-38-heading h4">Mike Coolbert</h3>
                                <p class="block-38-subheading">Sales Manager</p>
                            </div>
                            <div class="block-38-body">
                                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Vitae aut minima nihil sit distinctio recusandae doloribus ut fugit officia voluptate soluta. </p>
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
                        <h2 class="text-uppercase">Free Shipping</h2>
                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus at iaculis quam. Integer accumsan tincidunt fringilla.</p>
                    </div>
                </div>
                <div class="col-md-6 col-lg-4 d-lg-flex mb-4 mb-lg-0 pl-4" data-aos="fade-up" data-aos-delay="100">
                    <div class="icon mr-4 align-self-start">
                        <span class="icon-refresh2"></span>
                    </div>
                    <div class="text">
                        <h2 class="text-uppercase">Free Returns</h2>
                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus at iaculis quam. Integer accumsan tincidunt fringilla.</p>
                    </div>
                </div>
                <div class="col-md-6 col-lg-4 d-lg-flex mb-4 mb-lg-0 pl-4" data-aos="fade-up" data-aos-delay="200">
                    <div class="icon mr-4 align-self-start">
                        <span class="icon-help"></span>
                    </div>
                    <div class="text">
                        <h2 class="text-uppercase">Customer Support</h2>
                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus at iaculis quam. Integer accumsan tincidunt fringilla.</p>
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


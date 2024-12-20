<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="org.cahuas.webapp.servelet.cabeceras.models.modelo.Carro" %>
<%Carro carro = (Carro) session.getAttribute("carro");%>
<header class="site-navbar" role="banner">
  <div class="site-navbar-top">
    <div class="container">
      <div class="row align-items-center">

        <div class="col-6 col-md-4 order-2 order-md-1 site-search-icon text-left">
          <form action="" class="site-block-top-search">
            <span class="icon icon-search2"></span>
            <input type="text" class="form-control border-0" placeholder="Search">
          </form>
        </div>

        <div class="col-12 mb-3 mb-md-0 col-md-4 order-1 order-md-2 text-center">
          <div class="site-logo">
            <a href="/webbs/usuario/index.jsp" class="js-logo-clone">Shalom</a>
          </div>
        </div>

        <div class="col-6 col-md-4 order-3 order-md-3 text-right">
          <div class="site-top-icons">
            <ul>
              <li><a href="/webbs/usuario/configUsuario.jsp"><span class="icon icon-person"></span></a></li>
              <li><a href="/webbs/logout"><span class="icon icon-input"></span></a></li>
              <li>
                <a href="/webbs/usuario/cart.jsp" class="site-cart">
                  <span class="icon icon-shopping_cart"></span>
                  <span class="count"><%=carro.getItems().size()%></span>
                </a>
              </li>
              <li class="d-inline-block d-md-none ml-md-0"><a href="#" class="site-menu-toggle js-menu-toggle"><span class="icon-menu"></span></a></li>
            </ul>
          </div>
        </div>


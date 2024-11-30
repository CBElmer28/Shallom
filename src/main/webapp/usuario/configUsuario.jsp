
<%@ page import="org.cahuas.webapp.servelet.cabeceras.models.modelo.Usuario" %>
<%@ page import="org.cahuas.webapp.servelet.cabeceras.models.modelo.Cliente" %><%--
  Created by IntelliJ IDEA.
  User: keyen
  Date: 29/11/2024
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Usuario usu =(Usuario)session.getAttribute("usuario");%>

<% Cliente c = (Cliente)session.getAttribute("cliente"); %>

<html>
<head>
    <title>configUsuario</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" type="image/png" href="/images/men.jpg"/>
    <link rel="stylesheet" type="text/css" href="scss/bootstrap/css/bootstrap-grid.min.css">
    <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
    <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
    <link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
    <link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
    <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
    <link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
    <link rel="stylesheet" type="text/css" href="css/util.css">
    <link rel="stylesheet" type="text/css" href="css/main.css">

    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/magnific-popup.css">
    <link rel="stylesheet" href="css/jquery-ui.css">
    <link rel="stylesheet" href="css/owl.carousel.min.css">
    <link rel="stylesheet" href="css/owl.theme.default.min.css">
    <link rel="stylesheet" href="css/aos.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="limiter">
    <div class="container-login100">
        <div class="shadow-lg p-2 p-lg-5 rounded" data-aos="fade-up">
            <div class="wrap-login100 p-t-50 p-b-90">
                <form action="/webbs/config" method="post" class="login100-form validate-form flex-sb flex-w">
                    <span class="login100-form-title p-b-51">
                        Registro
                    </span>

                    <div class="wrap-input100 validate-input m-b-16" data-validate="Username es requerido">
                        <input class="input100" type="text" id="nombre" name="nombre" placeholder="<%=c.getNombre()%>">
                    </div>

                    <div class="wrap-input100 validate-input m-b-16" data-validate="User es requerido">
                        <input class="input100" type="text" id="user" name="user" placeholder="<%=usu.getUser()%>">
                    </div>

                    <div class="wrap-input100 validate-input m-b-16" data-validate="correo es requerido">
                        <input class="input100" type="email" id="correo" name="correo" placeholder="<%=c.getCo()%>">
                    </div>

                    <div class="wrap-input100 validate-input m-b-16" data-validate="correo es requerido">
                        <input class="input100" type="number" id="telefono" name="telefono" placeholder="<%=c.getTel()%>">
                    </div>

                    <div class="wrap-input100 validate-input m-b-16" data-validate="correo es requerido">
                        <input class="input100" type="number" id="DNI" name="DNI" placeholder="<%=usu.getDni()%>">
                    </div>

                    <div class="wrap-input100 validate-input m-b-16" data-validate="Password is required">
                        <input class="input100" type="password" name="password" id="password" placeholder="<%=usu.getPass()%>">
                    </div>


                    <div class="container-login100-form-btn m-t-17">
                        <button type="submit" class="login100-form-btn">
                            configurar
                        </button>
                    </div>
                </form>
            </div>

            <div class="text-center">
                <a href="/webbs/usuario/index.jsp" class="txt1">
                    regresar
                </a>
                </p>
            </div>
        </div>
    </div>
</div>

<div id="dropDownSelect1"></div>
<script>
    document.getElementById("registroForm").addEventListener("submit", function(event) {
        const username = document.getElementById("username").value.trim();
        const user = document.getElementById("user").value.trim();
        const correo = document.getElementById("correo").value.trim();
        const telefono = document.getElementById("telefono").value.trim();
        const dni = document.getElementById("DNI").value.trim();
        const password = document.getElementById("password").value.trim();

        if (!username || !user || !correo || !telefono || !dni || !password) {
            event.preventDefault(); // Detiene el envío del formulario
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Todos los campos son obligatorios',
                confirmButtonText: 'Aceptar'
            });
            return;
        }

        // Validación adicional (ejemplo: longitud mínima de la contraseña)
        if (password.length < 6) {
            event.preventDefault();
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'La contraseña debe tener al menos 6 caracteres',
                confirmButtonText: 'Aceptar'
            });
            return;
        }

        // Si todo está bien, el formulario se envía normalmente
    });
</script>
<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
<script src="vendor/animsition/js/animsition.min.js"></script>
<script src="vendor/bootstrap/js/popper.js"></script>
<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="vendor/select2/select2.min.js"></script>
<script src="vendor/daterangepicker/moment.min.js"></script>
<script src="vendor/daterangepicker/daterangepicker.js"></script>
<script src="vendor/countdowntime/countdowntime.js"></script>

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

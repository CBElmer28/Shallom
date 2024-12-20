<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <title>Olvidaste la contraseña</title>
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
                <form action="/webbs/forgotPassword" method="POST" class="login100-form validate-form flex-sb flex-w">
                    <span class="login100-form-title p-b-51">
                        Recuperar Contraseña
                    </span>

                    <p class="text-center txt1 m-b-20" style="color: #666666;">
                        Por favor, introduce tu correo electrónico para enviarte un correo
                        con un código para restablecer tu contraseña.
                    </p>

                    <div class="wrap-input100 validate-input m-b-16" data-validate="El correo electrónico es obligatorio">
                        <input class="input100" type="email" id="email" name="email" placeholder="Correo Electrónico" required="">
                    </div>

                    <div class="container-login100-form-btn m-t-17">
                        <button type="submit" class="login100-form-btn">
                            Enviar Enlace de Restablecimiento
                        </button>
                    </div>
                </form>
            </div>

            <div class="text-center">
                <p class="txt1" style="color: #999999;">
                    ¿Ya recordaste tu contraseña?
                    <a href="login.jsp" class="txt1">
                        Inicia sesión aquí
                    </a>
                </p>
            </div>
        </div>
    </div>
</div>

<div id="dropDownSelect1"></div>

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


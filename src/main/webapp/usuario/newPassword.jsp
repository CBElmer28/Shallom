<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
	<title>Nueva contraseña</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="usuario/scss/bootstrap/css/bootstrap-grid.min.css">
	<link rel="stylesheet" type="text/css" href="usuario/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="usuario/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
	<link rel="stylesheet" type="text/css" href="usuario/vendor/animate/animate.css">
	<link rel="stylesheet" type="text/css" href="usuario/vendor/css-hamburgers/hamburgers.min.css">
	<link rel="stylesheet" type="text/css" href="usuario/vendor/animsition/css/animsition.min.css">
	<link rel="stylesheet" type="text/css" href="usuario/vendor/select2/select2.min.css">
	<link rel="stylesheet" type="text/css" href="usuario/vendor/daterangepicker/daterangepicker.css">
	<link rel="stylesheet" type="text/css" href="usuario/css/util.css">
	<link rel="stylesheet" type="text/css" href="usuario/css/main.css">

	<link rel="stylesheet" href="usuario/css/bootstrap.min.css">
	<link rel="stylesheet" href="usuario/css/magnific-popup.css">
	<link rel="stylesheet" href="usuario/css/jquery-ui.css">
	<link rel="stylesheet" href="usuario/css/owl.carousel.min.css">
	<link rel="stylesheet" href="usuario/css/owl.theme.default.min.css">
	<link rel="stylesheet" href="usuario/css/aos.css">
	<link rel="stylesheet" href="usuario/css/style.css">
</head>
<body>
<div class="limiter">
	<div class="container-login100">
		<div class="shadow-lg p-2 p-lg-5 rounded" data-aos="fade-up">
			<div class="wrap-login100 p-t-50 p-b-90">
				<form action="/webbs/newPassword" method="post" class="login100-form validate-form flex-sb flex-w">
                    <span class="login100-form-title p-b-51">
                        Restablecer Contraseña
                    </span>

					<p class="text-center txt1 m-b-20" style="color: #666666;">
						Por favor, ingresa tu nueva contraseña y confírmala para continuar. (Tu contraseña debe tener almenos 8 caracteres, 1 letra y 1 número)
					</p>

					<%-- Mensaje de error si las contraseñas no coinciden --%>
					<%
						String status = (String) request.getAttribute("status");
						if ("passwordMismatch".equals(status)) {
							out.print("<p class='text-danger text-center'>Las contraseñas no coinciden. Intenta nuevamente.</p>");
						} else if ("resetFailed".equals(status)) {
							out.print("<p class='text-danger text-center'>Ocurrió un error al restablecer la contraseña. Intenta nuevamente.</p>");
						} else if ("resetSuccess".equals(status)) {
							out.print("<p class='text-success text-center'>Contraseña restablecida con éxito. Inicia sesión con tu nueva contraseña.</p>");
						}
					%>

					<div class="wrap-input100 validate-input m-b-16" data-validate="La contraseña es obligatoria">
						<input class="input100" type="password" id="password" name="password" placeholder="Nueva Contraseña" required pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$">

					</div>

					<div class="wrap-input100 validate-input m-b-16" data-validate="Debes confirmar tu contraseña">
						<input class="input100" type="password" id="confPassword" name="confPassword" placeholder="Confirmar Nueva Contraseña" required>
					</div>

					<div class="container-login100-form-btn m-t-17">
						<button type="submit" class="login100-form-btn">
							Restablecer Contraseña
						</button>
					</div>
				</form>
			</div>

			<div class="text-center">
				<p class="txt1" style="color: #999999;">
					¿Recordaste tu contraseña?
					<a href="/webbs/login.jsp" class="txt1">
						Inicia sesión aquí
					</a>
				</p>
			</div>
		</div>
	</div>
</div>

<div id="dropDownSelect1"></div>

<script src="usuario/vendor/jquery/jquery-3.2.1.min.js"></script>
<script src="usuario/vendor/animsition/js/animsition.min.js"></script>
<script src="usuario/vendor/bootstrap/js/popper.js"></script>
<script src="usuario/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="usuario/vendor/select2/select2.min.js"></script>
<script src="usuario/vendor/daterangepicker/moment.min.js"></script>
<script src="usuario/vendor/daterangepicker/daterangepicker.js"></script>
<script src="usuario/vendor/countdowntime/countdowntime.js"></script>
<script src="usuario/js/aos.js"></script>
<script src="usuario/js/main.js"></script>
	
</body>
</html>
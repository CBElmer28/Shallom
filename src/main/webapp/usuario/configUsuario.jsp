<%@ page import="org.cahuas.webapp.servelet.cabeceras.models.modelo.Usuario" %>
<%@ page import="org.cahuas.webapp.servelet.cabeceras.models.modelo.Cliente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Usuario usu =(Usuario)session.getAttribute("usuario");%>
<% Cliente c = (Cliente)session.getAttribute("cliente"); %>

<!DOCTYPE html>
<html lang="es">
<head>
    <title>Shalom &mdash; Configuración Usuario</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" type="image/png" href="/images/men.jpg"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link rel="stylesheet" href="css/style.css">
    <style>
        .form-label {
            font-weight: bold;
        }
        .form-control {
            border: 2px solid #ced4da;
            border-radius: 0.25rem;
            box-shadow: none;
            transition: border-color 0.2s ease-in-out;
        }
        .form-control:focus {
            border-color: #6c757d;
            box-shadow: 0 0 5px rgba(108, 117, 125, 0.5);
        }
        .btn-primary {
            background-color: #827ffe;
            border: none;
            transition: background-color 0.2s ease-in-out;
        }
        .btn-primary:hover {
            background-color: #403866;
        }
        .container {
            max-width: 600px;
            margin-top: 50px;
            background: #f8f9fa;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
        }
        .text-danger {
            font-size: 0.875em;
        }
    </style>
</head>
<body>
<div class="container">
    <h2 class="text-center mb-4" style="color: #827ffe">Configuración del Usuario</h2>
    <form action="/webbs/config" method="post" id="configForm">
        <!-- Nombre -->
        <div class="mb-3">
            <label for="nombre" class="form-label" style="color: #403866">Nombre:</label>
            <input type="text" class="form-control" id="nombre" name="nombre" required value="<%=c.getNombre()%>" maxlength="50">
        </div>

        <!-- Usuario -->
        <div class="mb-3">
            <label for="user" class="form-label" style="color: #403866">Usuario:</label>
            <input type="text" class="form-control" id="user" name="user" required value="<%=usu.getUser()%>" maxlength="30">
        </div>

        <!-- Correo Electrónico -->
        <div class="mb-3">
            <label for="correo" class="form-label" style="color: #403866">Correo Electrónico:</label>
            <input type="email" class="form-control" id="correo" name="correo" required value="<%=c.getCo()%>" pattern="^[^@\s]+@[^@\s]+\.[^@\s]+$">
        </div>

        <!-- Teléfono -->
        <div class="mb-3">
            <label for="telefono" class="form-label" style="color: #403866">Celular:</label>
            <input type="tel" class="form-control" id="telefono" name="telefono" required value="<%=c.getTel()%>" pattern="^\d{9}$" maxlength="9">
            <small class="text-danger">Debe contener 9 dígitos.</small>
        </div>

        <!-- DNI -->
        <div class="mb-3">
            <label for="DNI" class="form-label" style="color: #403866">DNI:</label>
            <input type="text" class="form-control" id="DNI" name="DNI" required value="<%=usu.getDni()%>" pattern="^\d{8}$" maxlength="8">
            <small class="text-danger">Debe contener 8 dígitos.</small>
        </div>

        <!-- Contraseña -->
        <div class="mb-3">
            <label for="password" class="form-label" style="color: #403866">Contraseña:</label>
            <input type="password" class="form-control" id="password" name="password" required minlength="8" pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$">
            <small class="text-danger">Debe tener al menos 8 caracteres, incluyendo letras y números.</small>
        </div>

        <!-- Botón Enviar -->
        <div class="d-grid">
            <button type="submit" class="btn btn-primary">Confirmar Cambios</button>
        </div>

        <!-- Enlace Regresar -->
        <div class="text-center mt-4">
            <a href="/webbs/usuario/index.jsp" class="text-decoration-none">Regresar</a>
        </div>
    </form>
</div>

<script>
    document.getElementById("configForm").addEventListener("submit", function(event) {
        const telefono = document.getElementById("telefono").value.trim();
        const dni = document.getElementById("DNI").value.trim();

        if (!/^[0-9]{9}$/.test(telefono)) {
            event.preventDefault();
            alert("El número de teléfono debe tener 9 dígitos y no contener letras.");
            return;
        }

        if (!/^[0-9]{8}$/.test(dni)) {
            event.preventDefault();
            alert("El DNI debe tener 8 dígitos y no contener letras.");
            return;
        }
    });
</script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
</body>
</html>
package org.cahuas.webapp.servelet.cabeceras.controlador;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ValidateOtp")
public class ValidateOtp extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // El método service se encarga de procesar las solicitudes HTTP
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtiene el OTP ingresado por el usuario desde la solicitud
        int value = Integer.parseInt(request.getParameter("otp"));

        // Obtiene la sesión del usuario actual
        HttpSession session = request.getSession();

        // Recupera el OTP almacenado en la sesión (previamente generado)
        int otp = (int) session.getAttribute("otp");

        // Declaración de dispatcher para redirigir al usuario según el resultado
        RequestDispatcher dispatcher = null;

        // Verifica si el OTP ingresado es correcto
        if (value == otp) {
            // Si el OTP es correcto, establece atributos para la vista
            request.setAttribute("email", request.getParameter("email"));
            request.setAttribute("status", "success");

            // Redirige al usuario a la página para establecer una nueva contraseña
            dispatcher = request.getRequestDispatcher("/usuario/newPassword.jsp");
            dispatcher.forward(request, response);
        } else {
            // Si el OTP es incorrecto, muestra un mensaje de error
            request.setAttribute("message", "wrong otp");

            // Redirige de nuevo a la página de ingreso del OTP
            dispatcher = request.getRequestDispatcher("/usuario/EnterOTP.jsp");
            dispatcher.forward(request, response);
        }
    }
}

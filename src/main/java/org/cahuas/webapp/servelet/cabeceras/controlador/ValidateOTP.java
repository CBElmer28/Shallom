package org.cahuas.webapp.servelet.cabeceras.controlador;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet encargado de validar el código OTP (One-Time Password) ingresado por el usuario
 * como parte del proceso de recuperación de contraseña. Este servlet compara el OTP proporcionado
 * por el usuario con el valor almacenado en la sesión. Si los códigos coinciden, redirige al
 * usuario a la página para establecer una nueva contraseña. Si no coinciden, redirige al usuario
 * de nuevo a la página para ingresar el OTP, mostrando un mensaje de error.
 *
 * @author Team Shalom
 * @version 1.6
 */
@WebServlet("/ValidateOtp")
public class ValidateOtp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Método encargado de manejar la validación del código OTP enviado por el usuario.
	 * Este método obtiene el OTP ingresado por el usuario desde la solicitud HTTP y lo
	 * compara con el OTP almacenado en la sesión. En función del resultado, redirige al
	 * usuario a la página adecuada.
	 *
	 * @param request la solicitud HTTP que contiene el OTP ingresado por el usuario y otros parámetros.
	 * @param response la respuesta HTTP que se envía al usuario, redirigiéndolo a la página correspondiente.
	 * @throws ServletException si ocurre un error en la ejecución del servlet.
	 * @throws IOException si ocurre un error durante el proceso de redirección o escritura en la respuesta.
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Obtener el OTP ingresado por el usuario desde la solicitud HTTP.
		int value = Integer.parseInt(request.getParameter("otp"));

		// Obtener la sesión del usuario y el OTP almacenado en ella.
		HttpSession session = request.getSession();
		int otp = (int) session.getAttribute("otp");

		// Declaración de RequestDispatcher para redirigir al usuario a las páginas correspondientes.
		RequestDispatcher dispatcher = null;

		// Comparar el OTP ingresado con el almacenado en la sesión.
		if (value == otp) {
			// Si el OTP es correcto, se envían los parámetros necesarios a la siguiente página.
			request.setAttribute("email", request.getParameter("email"));
			request.setAttribute("status", "success");

			// Redirigir a la página para crear una nueva contraseña.
			dispatcher = request.getRequestDispatcher("/usuario/newPassword.jsp");
			dispatcher.forward(request, response);
		} else {
			// Si el OTP es incorrecto, se establece un mensaje de error y se redirige al formulario de OTP.
			request.setAttribute("message", "wrong otp");

			// Redirigir a la página para ingresar el OTP nuevamente.
			dispatcher = request.getRequestDispatcher("/usuario/EnterOTP.jsp");
			dispatcher.forward(request, response);
		}
	}
}
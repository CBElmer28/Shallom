package org.cahuas.webapp.servelet.cabeceras.controlador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.cahuas.webapp.servelet.cabeceras.models.util.ConexionBaseDatos;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Servlet que maneja el cambio de contraseña del usuario.
 * Este servlet recibe las nuevas contraseñas del usuario, verifica que coincidan,
 * y actualiza la contraseña en la base de datos si es válido.
 *
 * @author Team Shalom
 * @version 1.8
 *
 */
@WebServlet("/newPassword")
public class NewPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Maneja la solicitud HTTP POST para cambiar la contraseña del usuario.
	 * Este método verifica si las contraseñas coinciden, las cifra utilizando BCrypt
	 * y las actualiza en la base de datos. En caso de éxito o error, redirige al usuario
	 * a la página de login con un mensaje de estado.
	 *
	 * @param request la solicitud HTTP que contiene las nuevas contraseñas del usuario.
	 * @param response la respuesta HTTP que se envía al cliente después de procesar la solicitud.
	 * @throws ServletException si ocurre un error relacionado con el servlet durante el procesamiento de la solicitud.
	 * @throws IOException si ocurre un error durante el proceso de redirección o escritura en la respuesta.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String newPassword = request.getParameter("password");
		String confPassword = request.getParameter("confPassword");
		RequestDispatcher dispatcher = null;

		// Verificar si las contraseñas coinciden
		if (newPassword != null && confPassword != null && newPassword.equals(confPassword)) {

			try {
				// Usar la clase ConexionBaseDatos para obtener la conexión
				Connection con = ConexionBaseDatos.getConnection();

				// Obtener el email de la sesión
				String email = (String) session.getAttribute("email");

				// Cifrar la nueva contraseña usando BCrypt
				String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());

				// Realizar el UPDATE en la tabla 'usuarios' usando el 'email' en la tabla 'clientes'
				String sql = "UPDATE usuarios u " +
						"JOIN clientes c ON u.id = c.id_usuario " +
						"SET u.pass = ? " +
						"WHERE c.co = ?";

				PreparedStatement pst = con.prepareStatement(sql);

				// Establecer los parámetros de la consulta
				pst.setString(1, hashedPassword); // Establecer la contraseña cifrada
				pst.setString(2, email); // Usar el 'email' para encontrar el usuario

				// Ejecutar la actualización
				int rowCount = pst.executeUpdate();

				// Verificar el resultado
				if (rowCount > 0) {
					// Contraseña actualizada con éxito
					session.setAttribute("status", "resetSuccess");
					// Redirigir al login sin usar dispatcher
					response.sendRedirect("/webbs/usuario/login.jsp");
					return; // Asegúrate de detener la ejecución aquí para evitar más procesamiento
				} else {
					// Error al actualizar la contraseña
					session.setAttribute("status", "resetFailed");
					response.sendRedirect("/webbs/usuario/login.jsp");
					return; // Detén la ejecución para evitar más procesamiento
				}

			} catch (SQLException e) {
				// Manejo de errores de SQL
				e.printStackTrace();
				session.setAttribute("status", "resetFailed");
				response.sendRedirect("/webbs/usuario/login.jsp");
				return; // Detén la ejecución aquí
			} catch (Exception e) {
				// Manejo de otros errores
				e.printStackTrace();
				session.setAttribute("status", "resetFailed");
				response.sendRedirect("/webbs/usuario/login.jsp");
				return; // Detén la ejecución aquí
			}

		} else {
			// Si las contraseñas no coinciden, redirigir con un mensaje de error
			request.setAttribute("status", "passwordMismatch");
			dispatcher = request.getRequestDispatcher("/usuario/newPassword.jsp");
			dispatcher.forward(request, response);
		}
	}
}
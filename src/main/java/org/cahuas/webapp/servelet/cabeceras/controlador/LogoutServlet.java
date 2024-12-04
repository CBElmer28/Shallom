package org.cahuas.webapp.servelet.cabeceras.controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;
import org.cahuas.webapp.servelet.cabeceras.models.services.LoginService;

/**
 * Servlet que maneja el proceso de cierre de sesión de un usuario.
 * Este servlet invalida la sesión activa del usuario y redirige al usuario a la página de inicio.
 * 
 * @author Team Shalom
 * @version 1.6
 *
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    
    /**
     * Maneja la solicitud HTTP GET para cerrar la sesión del usuario.
     * Este método invalida la sesión activa del usuario, eliminando todos los atributos de la sesión,
     * y redirige al usuario a la página de inicio.
     * 
     * @param req la solicitud HTTP que contiene la información de la sesión.
     * @param resp la respuesta HTTP que se envía al cliente después de cerrar sesión.
     * @throws ServletException si ocurre un error relacionado con el servlet durante el procesamiento de la solicitud.
     * @throws IOException si ocurre un error durante el proceso de redirección o escritura en la respuesta.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtiene la sesión actual si existe
        HttpSession session = req.getSession(false);
        
        // Si la sesión existe, la invalida
        if (session != null) {
            session.invalidate();
        }
        
        // Redirige al usuario a la página de inicio
        resp.sendRedirect(req.getContextPath() + "/usuario/index.jsp");
    }
}

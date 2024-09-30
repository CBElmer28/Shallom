package org.cahuas.webapp.servelet.cabeceras.models.filters;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.cahuas.webapp.servelet.cabeceras.models.modelo.Usuario;
import org.cahuas.webapp.servelet.cabeceras.models.services.LoginService;
import org.cahuas.webapp.servelet.cabeceras.models.services.LoginServiceJdbcImpl;
import org.cahuas.webapp.servelet.cabeceras.models.util.ConexionBaseDatos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

@WebFilter({"/admin/*"})
public class LoginAdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
       // Convertimos los objetos genéricos a HttpServletRequest y HttpServletResponse
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        // Obtenemos el nombre de usuario de la sesión o de donde lo tengas almacenado
        if (session != null && session.getAttribute("username") != null) {
            String username = (String)session.getAttribute("username");
            try {
                // Conectarse a la base de datos
                Connection conn = ConexionBaseDatos.getConnection();
                LoginServiceJdbcImpl loginService = new LoginServiceJdbcImpl(conn);
                // Recuperar el usuario desde la base de datos
                Usuario usuario = loginService.existeUsuario(username);
                // Verificar si el usuario es de tipo "admin"
                if (usuario != null && "admin".equals(usuario.getTipo())) {
                    // Si es admin, continuar con la cadena de filtros
                    filterChain.doFilter(request, response);
                } else {
                    // Si no es admin, denegar el acceso con un error 403
                    httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "No tienes permisos para acceder a esta página.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                httpResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                        "Error al verificar los permisos.");
            }
        } else {
            // Si no hay una sesión activa o no hay usuario logueado, redirigir a login
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/usuario/login.jsp");
        }
    }
}

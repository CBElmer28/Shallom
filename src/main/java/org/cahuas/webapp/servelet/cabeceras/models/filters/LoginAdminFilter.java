package org.cahuas.webapp.servelet.cabeceras.models.filters;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.cahuas.webapp.servelet.cabeceras.models.modelo.Usuario;
import org.cahuas.webapp.servelet.cabeceras.models.services.LoginServiceJdbcImpl;
import org.cahuas.webapp.servelet.cabeceras.models.util.ConexionBaseDatos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter({"/admin/*"})
public class LoginAdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            String username = (String)session.getAttribute("username");
            try {
                Connection conn = ConexionBaseDatos.getConnection();
                LoginServiceJdbcImpl loginService = new LoginServiceJdbcImpl(conn);
                Usuario usuario = loginService.existeUsuario(username);
                if (usuario != null && "admin".equals(usuario.getTipo())) {
                    filterChain.doFilter(request, response);
                } else {
                    httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "No tienes permisos para acceder a esta página.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                httpResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                        "Error al verificar los permisos.");
            }
        } else {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/usuario/login.jsp");
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package org.cahuas.webapp.servelet.cabeceras.controlador;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.cahuas.webapp.servelet.cabeceras.models.modelo.Usuario;
import org.cahuas.webapp.servelet.cabeceras.models.services.*;
import org.cahuas.webapp.servelet.cabeceras.models.util.ConexionBaseDatos;

@WebServlet(name = "login", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

      @Override
       protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try {
            Connection conn = ConexionBaseDatos.getConnection();
            LoginServiceJdbcImpl usu = new LoginServiceJdbcImpl(conn);
            Usuario ne =usu.UsuarioSesion(username, password);
        if (ne != null && ne.getUser().equals(username) && ne.getPass().equals(password)) {
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            if ("admin".equals(ne.getTipo())) {
                resp.sendRedirect(req.getContextPath() + "/admin/index.jsp");
            } else if ("empleado".equals(ne.getTipo())) {
                session.setAttribute("usuario", ne);
                resp.sendRedirect(req.getContextPath() + "/usuario/index.jsp");
            }
        } else {
            // REDIRIGE AL LOGIN
            resp.sendRedirect(req.getContextPath()+"/usuario/login.jsp");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            resp.sendRedirect(req.getContextPath()+"/usuario/index.jsp");
        }
       }

   
}

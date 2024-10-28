/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.cahuas.webapp.servelet.cabeceras.models.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Filter;
import java.util.logging.LogRecord;
import org.cahuas.webapp.servelet.cabeceras.models.services.ServiceJdbcException;
import org.cahuas.webapp.servelet.cabeceras.models.util.ConexionBaseDatos;

public class ConexionFilter implements Filter{

     public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException, SQLException {
/*
        try (Connection conn = ConexionBaseDatos.getConnection()) {

            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            try {
                request.setAttribute("conn", conn);
                chain.doFilter(request, response);
                conn.commit();
            } catch (SQLException | ServiceJdbcException e) {
                conn.rollback();
                ((HttpServletResponse)response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException e) {
           e.getMessage();
        }*/
    }


    @Override
    public boolean isLoggable(LogRecord record) {
        return false;
    }
}

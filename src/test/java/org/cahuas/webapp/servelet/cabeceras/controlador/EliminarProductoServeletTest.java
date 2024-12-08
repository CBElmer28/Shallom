package org.cahuas.webapp.servelet.cabeceras.controlador;

import static org.junit.jupiter.api.Assertions.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.cahuas.webapp.servelet.cabeceras.models.modelo.Producto;
import org.cahuas.webapp.servelet.cabeceras.models.services.ProductoService;
import org.cahuas.webapp.servelet.cabeceras.models.util.ConexionBaseDatos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import static org.mockito.Mockito.*;

class EliminarProductoServeletTest {

    private EliminarProductoServelet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private ProductoService productoService;
    private Connection connection;

    @BeforeEach
    void setUp() {
        servlet = new EliminarProductoServelet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        productoService = mock(ProductoService.class);
        connection = mock(Connection.class);

        mockStatic(ConexionBaseDatos.class);
    }

    @Test
    void doPost_ProductoEliminadoExitosamente() throws ServletException, IOException, SQLException {
        // Mockear la conexión de base de datos
        when(ConexionBaseDatos.getConnection()).thenReturn(connection);

        // Mockear parámetros del request
        when(request.getParameter("idProducto")).thenReturn("1");

        // Mockear comportamiento del servicio
        Producto productoMock = new Producto(1, "Producto 1", 10);
        when(productoService.porId(1)).thenReturn(Optional.of(productoMock));

        // Ejecutar método
        servlet.doPost(request, response);

        // Verificar que el producto se haya eliminado
        verify(productoService).eliminar(1);

        // Verificar redirección
        verify(response).sendRedirect(contains("/productos"));
    }

    @Test
    void doPost_ProductoNoEncontrado() throws ServletException, IOException, SQLException {
        // Mockear la conexión de base de datos
        when(ConexionBaseDatos.getConnection()).thenReturn(connection);

        // Mockear parámetros del request
        when(request.getParameter("idProducto")).thenReturn("99");

        // Mockear comportamiento del servicio
        when(productoService.porId(99)).thenReturn(Optional.empty());

        // Ejecutar método
        servlet.doPost(request, response);

        // Verificar que el producto no se elimine
        verify(productoService, never()).eliminar(anyInt());

        // Verificar respuesta de error
        verify(response).sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de producto no válido en la tabla");
    }

    @Test
    void doPost_IdInvalido() throws ServletException, IOException {
        // Mockear parámetros del request con un ID no numérico
        when(request.getParameter("idProducto")).thenReturn("abc");

        // Ejecutar método
        servlet.doPost(request, response);

        // Verificar respuesta de error por ID no válido
        verify(response).sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de producto no válido");
    }

    @Test
    void doPost_ErrorEnConexion() throws ServletException, IOException, SQLException {
        // Mockear un error en la conexión a la base de datos
        when(ConexionBaseDatos.getConnection()).thenThrow(new SQLException("Error de conexión"));

        // Mockear parámetros del request
        when(request.getParameter("idProducto")).thenReturn("1");

        // Ejecutar método y verificar que lanza RuntimeException
        assertThrows(RuntimeException.class, () -> servlet.doPost(request, response));
    }
}
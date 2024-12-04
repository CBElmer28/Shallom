package org.cahuas.webapp.servelet.cabeceras.controlador;

import static org.junit.jupiter.api.Assertions.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.cahuas.webapp.servelet.cabeceras.models.modelo.Producto;
import org.cahuas.webapp.servelet.cabeceras.models.modelo.Proveedor;
import org.cahuas.webapp.servelet.cabeceras.models.services.ProductoServiceJdbcImpl;
import org.cahuas.webapp.servelet.cabeceras.models.util.ConexionBaseDatos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

class AgregarProductoServeletTest {

    private AgregarProductoServelet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private ProductoServiceJdbcImpl productoService;
    private Part filePart;

    @BeforeEach
    void setUp() {
        servlet = new AgregarProductoServelet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        productoService = mock(ProductoServiceJdbcImpl.class);
        filePart = mock(Part.class);
    }

    @Test
    void doPost_AgregarProductoExitoso() throws ServletException, IOException, SQLException {
        // Simular la conexión de base de datos
        Connection connection = mock(Connection.class);
        mockStatic(ConexionBaseDatos.class);
        when(ConexionBaseDatos.getConnection()).thenReturn(connection);

        // Configuración de los parámetros
        when(request.getParameter("nombre")).thenReturn("Producto Test");
        when(request.getParameter("cat")).thenReturn("Categoría Test");
        when(request.getParameter("precio")).thenReturn("100");
        when(request.getParameter("stock")).thenReturn("10");
        when(request.getParameter("idProveedor")).thenReturn("1");

        // Mockear archivo de imagen
        when(request.getPart("imagen")).thenReturn(filePart);
        when(filePart.getSize()).thenReturn(1234L);
        when(filePart.getSubmittedFileName()).thenReturn("producto1.jpg");

        // Mockear proveedor
        Proveedor proveedorMock = new Proveedor();
        when(productoService.porIdCategoria(1)).thenReturn(proveedorMock);

        // Ejecutar el método
        servlet.doPost(request, response);

        // Verificar que el método guardar del servicio fue llamado con los valores correctos
        ArgumentCaptor<Producto> productoCaptor = ArgumentCaptor.forClass(Producto.class);
        verify(productoService).guardar(productoCaptor.capture());

        Producto productoGuardado = productoCaptor.getValue();
        assertEquals("Producto Test", productoGuardado.getNom());
        assertEquals("Categoría Test", productoGuardado.getCat());
        assertEquals(100, productoGuardado.getPrecio());
        assertEquals(10, productoGuardado.getStock());
        assertEquals("producto1.jpg", productoGuardado.getRuta_imagen());

        // Verificar redirección
        verify(response).sendRedirect(contains("/productos"));
    }

    @Test
    void doPost_ErrorSinParametros() throws ServletException, IOException {
        // Configuración sin parámetros obligatorios
        when(request.getParameter("nombre")).thenReturn(null);

        // Ejecutar el método
        servlet.doPost(request, response);

        // Verificar que responde con error HTTP
        verify(response).sendError(eq(HttpServletResponse.SC_INTERNAL_SERVER_ERROR), anyString());
    }
}
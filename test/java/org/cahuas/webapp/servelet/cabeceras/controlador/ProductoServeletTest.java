package org.cahuas.webapp.servelet.cabeceras.controlador;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.cahuas.webapp.servelet.cabeceras.models.modelo.Producto;
import org.cahuas.webapp.servelet.cabeceras.models.services.ProductoService;
import org.cahuas.webapp.servelet.cabeceras.models.util.ConexionBaseDatos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

class ProductoServeletTest {

    private ProductoServelet productoServelet;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher requestDispatcher;

    @Mock
    private Connection connection;

    @Mock
    private ProductoService productoService;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        productoServelet = new ProductoServelet();
    }

    @Test
    void testProductoEncontrado() throws Exception {
        // Configurar el mock de ProductoService
        Producto productoMock = new Producto();
        productoMock.setId(1);
        productoMock.setNom("Producto Test");
        productoMock.setPrecio(100);
        productoMock.setStock(10);

        when(request.getParameter("id")).thenReturn("1");
        when(ConexionBaseDatos.getConnection()).thenReturn(connection);
        when(productoService.buscarId(1)).thenReturn(productoMock);
        when(request.getRequestDispatcher("shop-single.jsp")).thenReturn(requestDispatcher);

        // Ejecutar el servlet
        productoServelet.doGet(request, response);

        // Verificar comportamientos
        verify(request).setAttribute("producto", productoMock);
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    void testProductoNoEncontrado() throws Exception {
        when(request.getParameter("id")).thenReturn("999"); // ID inexistente
        when(ConexionBaseDatos.getConnection()).thenReturn(connection);
        when(productoService.buscarId(999)).thenReturn(null);

        // Ejecutar el servlet
        productoServelet.doGet(request, response);

        // Verificar que responde con error 400
        verify(response).sendError(HttpServletResponse.SC_BAD_REQUEST, "NO EXISTEN PRODUCTO");
    }


    @Test
    void testIdInvalido() throws Exception {
        when(request.getParameter("id")).thenReturn("abc"); // ID no numérico

        // Ejecutar el servlet
        productoServelet.doGet(request, response);

        // Verificar que responde con error 400
        verify(response).sendError(HttpServletResponse.SC_BAD_REQUEST, "NO EXISTEN PRODUCTO");
    }

    @Test
    void testSQLException() throws Exception {
        when(request.getParameter("id")).thenReturn("1");
        when(ConexionBaseDatos.getConnection()).thenThrow(new SQLException("Database error"));

        // Ejecutar el servlet
        try {
            productoServelet.doGet(request, response);
        } catch (RuntimeException e) {
            // Verificar que lanza una excepción con la causa correcta
            verify(response, never()).sendError(anyInt(), anyString());
            throw e;
        }
    }
}

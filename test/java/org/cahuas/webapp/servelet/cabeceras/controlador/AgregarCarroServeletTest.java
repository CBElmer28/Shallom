package org.cahuas.webapp.servelet.cabeceras.controlador;

import org.junit.jupiter.api.Test;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.cahuas.webapp.servelet.cabeceras.models.modelo.Carro;
import org.cahuas.webapp.servelet.cabeceras.models.modelo.Producto;
import org.cahuas.webapp.servelet.cabeceras.models.services.ProductoService;
import org.junit.jupiter.api.BeforeEach;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class AgregarCarroServeletTest {

    private AgregarCarroServelet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private ProductoService productoService;

    @BeforeEach
    public void setUp() {
        // Inicializamos el servlet y los mocks
        servlet = new AgregarCarroServelet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        productoService = mock(ProductoService.class);
    }

    @Test
    public void testAgregarProductoExitoso() throws Exception {
        // Configurar mocks
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("cantidad")).thenReturn("2");
        when(request.getSession()).thenReturn(session);

        Producto producto = new Producto(1, "Producto Test", 100);
        when(productoService.porId(1)).thenReturn(Optional.of(producto));

        Carro carro = new Carro();
        when(session.getAttribute("carro")).thenReturn(carro);

        // Ejecutar el servlet
        servlet.doGet(request, response);

        // Verificar que se agregó el producto al carro
        verify(session).getAttribute("carro");
        verify(session).setAttribute(eq("carro"), any(Carro.class));
        assert carro.getItems().size() == 1;
        assert carro.getItems().get(0).getProducto().equals(producto);
        assert carro.getItems().get(0).getCantidad() == 2;

        // Verificar redirección
        verify(response).sendRedirect(request.getContextPath() + "/usuario/cart.jsp");
    }

    @Test
    public void testProductoNoEncontrado() throws Exception {
        // Configurar mocks para un producto inexistente
        when(request.getParameter("id")).thenReturn("999");
        when(request.getParameter("cantidad")).thenReturn("1");
        when(productoService.porId(999)).thenReturn(Optional.empty());

        // Ejecutar el servlet
        servlet.doGet(request, response);

        // Verificar que se devuelve un error 400
        verify(response).sendError(HttpServletResponse.SC_BAD_REQUEST, "NO EXISTEN PRODUCTO EN LA BASE DE DATOS");
    }

    @Test
    public void testCantidadInvalida() throws Exception {
        // Configurar mocks para un valor inválido de cantidad
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("cantidad")).thenReturn("abc");

        // Ejecutar el servlet
        servlet.doGet(request, response);

        // Verificar que se devuelve un error 500 (excepción de formato)
        verify(response, never()).sendRedirect(anyString());
    }
}
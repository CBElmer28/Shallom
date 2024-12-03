package org.cahuas.webapp.servelet.cabeceras.controlador;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.cahuas.webapp.servelet.cabeceras.models.modelo.Producto;
import org.cahuas.webapp.servelet.cabeceras.models.services.ProductoService;
import org.cahuas.webapp.servelet.cabeceras.models.services.ProductoServiceJdbcImpl;
import org.cahuas.webapp.servelet.cabeceras.models.util.ConexionBaseDatos;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class ProductosServeletTest {

    private ProductosServelet productosServelet;

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
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        productosServelet = new ProductosServelet();
    }

    @Test
    public void testUsuarioProductos() throws ServletException, IOException, SQLException {
        // Preparar datos simulados
        Producto producto1 = new Producto(1, "Producto 1", 100, 10);
        Producto producto2 = new Producto(2, "Producto 2", 200, 20);
        List<Producto> productosMock = Arrays.asList(producto1, producto2);

        // Simular el comportamiento
        when(request.getRequestURI()).thenReturn("/usuario/productos");
        when(request.getRequestDispatcher("/usuario/shop.jsp")).thenReturn(requestDispatcher);
        when(ConexionBaseDatos.getConnection()).thenReturn(connection);

        ProductoServiceJdbcImpl serviceMock = mock(ProductoServiceJdbcImpl.class);
        when(serviceMock.listar()).thenReturn(productosMock);
        doReturn(serviceMock).when(productoService).listar();

        // Ejecutar el servlet
        productosServelet.doGet(request, response);

        // Verificar interacciones y resultados
        verify(request).setAttribute("productos", productosMock);
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void testAdminProductos() throws ServletException, IOException, SQLException {
        // Preparar datos simulados
        Producto producto1 = new Producto(1, "Producto 1", 100, 10);
        Producto producto2 = new Producto(2, "Producto 2", 200, 20);
        List<Producto> productosMock = Arrays.asList(producto1, producto2);

        // Simular el comportamiento
        when(request.getRequestURI()).thenReturn("/productos");
        when(request.getRequestDispatcher("/admin/inventario.jsp")).thenReturn(requestDispatcher);
        when(ConexionBaseDatos.getConnection()).thenReturn(connection);

        ProductoServiceJdbcImpl serviceMock = mock(ProductoServiceJdbcImpl.class);
        when(serviceMock.listar()).thenReturn(productosMock);
        doReturn(serviceMock).when(productoService).listar();

        // Ejecutar el servlet
        productosServelet.doGet(request, response);

        // Verificar interacciones y resultados
        verify(request).setAttribute("productos", productosMock);
        verify(requestDispatcher).forward(request, response);
    }
}

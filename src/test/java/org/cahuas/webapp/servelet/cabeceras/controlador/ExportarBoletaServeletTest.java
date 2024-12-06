package org.cahuas.webapp.servelet.cabeceras.controlador;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.cahuas.webapp.servelet.cabeceras.models.modelo.Carro;
import org.cahuas.webapp.servelet.cabeceras.models.modelo.DatalleProducto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class ExportarBoletaServeletTest {

    private ExportarBoletaServelet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private ServletContext servletContext;
    private ServletConfig servletConfig;
    private Carro carro;

    @BeforeEach
    void setUp() throws ServletException {
        servlet = new ExportarBoletaServelet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        servletContext = mock(ServletContext.class);
        servletConfig = mock(ServletConfig.class);
        carro = mock(Carro.class);

        // Configuración del servlet
        when(servletConfig.getServletContext()).thenReturn(servletContext);
        servlet.init(servletConfig);

        // Mockear el request y el session
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("carro")).thenReturn(carro);
    }

    @Test
    void doGet_GeneraReporteExitosamente() throws Exception {
        // Mockear lista de productos del carro
        List<DatalleProducto> detalleProductos = new ArrayList<>();
        detalleProductos.add(new DatalleProducto("Producto 1", 2, 20));
        detalleProductos.add(new DatalleProducto("Producto 2", 1, 50));
        when(carro.getDetalleProductos()).thenReturn(detalleProductos);

        // Mockear los recursos (logo y reporte)
        InputStream logoStream = mock(InputStream.class);
        InputStream reporteStream = mock(InputStream.class);
        when(servletContext.getResourceAsStream("/reportesJasper/img/logosinfondo.png")).thenReturn(logoStream);
        when(servletContext.getResourceAsStream("/reportesJasper/BoletaProuctos.jasper")).thenReturn(reporteStream);

        // Mockear el JasperReport y JasperPrint
        JasperReport jasperReport = mock(JasperReport.class);
        JasperPrint jasperPrint = mock(JasperPrint.class);
        when(JRLoader.loadObject(reporteStream)).thenReturn(jasperReport);
        when(JasperFillManager.fillReport(eq(jasperReport), anyMap(), any(JRBeanArrayDataSource.class)))
                .thenReturn(jasperPrint);

        // Configurar el output stream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        when(response.getOutputStream()).thenReturn(new ServletOutputStreamWrapper(outputStream));

        // Usar mock static para el método estático
        try (MockedStatic<JasperExportManager> mocked = mockStatic(JasperExportManager.class)) {
            // Ejecutar el método
            servlet.doGet(request, response);

            // Verificar encabezados y contenido
            verify(response).setContentType("application/pdf");
            verify(response).setHeader("Content-Disposition", "inline; filename=boleta.pdf");
            verify(response.getOutputStream()).flush();

            // Verificar que JasperExportManager.exportReportToPdfStream fue llamado
            mocked.verify(() -> JasperExportManager.exportReportToPdfStream(eq(jasperPrint), eq(outputStream)));
        }
    }

    @Test
    void doGet_ErrorGenerandoReporte() throws Exception {
        // Simular un error al cargar el recurso del reporte
        when(servletContext.getResourceAsStream("/reportesJasper/BoletaProuctos.jasper")).thenReturn(null);

        // Configurar el output stream para el response
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        when(response.getOutputStream()).thenReturn(new ServletOutputStreamWrapper(outputStream));

        // Ejecutar el método
        servlet.doGet(request, response);

        // Verificar el mensaje de error
        verify(response).setContentType("text/plain");
        String respuesta = outputStream.toString();
        assert respuesta.contains("no se pudeo generar el reporte");
    }
}

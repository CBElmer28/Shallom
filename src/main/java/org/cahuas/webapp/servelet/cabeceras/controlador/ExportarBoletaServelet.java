
package org.cahuas.webapp.servelet.cabeceras.controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.cahuas.webapp.servelet.cabeceras.models.modelo.Carro;
import org.cahuas.webapp.servelet.cabeceras.models.modelo.DatalleProducto;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@WebServlet("/usuario/carro/exportarproduc") // Define la URL del servlet para generar el reporte
public class ExportarBoletaServelet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletOutputStream out = resp.getOutputStream(); // Obtiene el flujo de salida para enviar el PDF al cliente
        try {
            // Recupera la sesión HTTP
            HttpSession session = req.getSession();
            // Obtiene el objeto 'carro' de la sesión (especifica los productos en el carrito)
            Carro carro = (Carro) session.getAttribute("carro");

            // Obtiene el logo de la empresa y el reporte Jasper desde los recursos del servlet
            InputStream logoEmpresa = this.getServletConfig().getServletContext().getResourceAsStream("/reportesJasper/img/logoo.png");
            InputStream reporteProducto = this.getServletConfig().getServletContext().getResourceAsStream("/reportesJasper/BoletaProuctos.jasper");

            // Verifica si ambos archivos (logo y reporte) se cargaron correctamente
            if (logoEmpresa != null && reporteProducto != null) {

                // Mapa de parámetros que se pasan al reporte Jasper
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("logoEmpresa", logoEmpresa); // Agrega el logo de la empresa

                // Crea una lista de detalle de productos a partir del carrito
                List<DatalleProducto> detallesReporte = new ArrayList<>();
                detallesReporte.add(new DatalleProducto()); // Puedes agregar una entrada vacía si es necesario
                detallesReporte.addAll(carro.getDetalleProductos()); // Agrega los productos del carro

                // Crea una fuente de datos para JasperReports usando los detalles de productos
                JRBeanArrayDataSource ds = new JRBeanArrayDataSource(detallesReporte.toArray());
                parameters.put("ds", ds); // Agrega la fuente de datos a los parámetros

                // Carga el reporte Jasper
                JasperReport reporte = (JasperReport) JRLoader.loadObject(reporteProducto);
                // Rellena el reporte con los parámetros y la fuente de datos
                JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parameters, ds);

                // Configura la respuesta HTTP para enviar el reporte en formato PDF
                resp.setContentType("application/pdf");
                resp.setHeader("Content-Disposition", "inline; filename=boleta.pdf");

                // Exporta el reporte a PDF y lo escribe en el flujo de salida
                JasperExportManager.exportReportToPdfStream

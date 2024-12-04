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
import org.cahuas.webapp.servelet.cabeceras.models.modelo.*;
import org.cahuas.webapp.servelet.cabeceras.models.services.VentaServiceJdbcImpl;
import org.cahuas.webapp.servelet.cabeceras.models.util.ConexionBaseDatos;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.*;

/**
 * Servlet para exportar una boleta en formato PDF.
 * <p>
 * Este servlet genera un reporte en PDF basado en los detalles de los productos en el carrito de compras,
 * utilizando JasperReports. También registra la venta en la base de datos y actualiza el historial de compras
 * del usuario.
 * </p>
 * 
 * @author Team Shalom
 * @version 1.8
 */
@WebServlet("/usuario/carro/exportarproduc")
public class ExportarBoletaServelet extends HttpServlet {

    /**
     * Maneja las solicitudes GET para exportar una boleta en formato PDF.
     * <p>
     * Este método realiza las siguientes operaciones:
     * - Obtiene el carrito de compras y los datos del usuario desde la sesión.
     * - Genera un reporte PDF utilizando JasperReports, con detalles del carrito y un logo de la empresa.
     * - Registra la venta en la base de datos, incluyendo los detalles de los productos comprados.
     * - Actualiza el historial de compras del usuario en la sesión.
     * - Si ocurre un error o faltan recursos, envía un mensaje al cliente.
     * </p>
     * 
     * @param req  Solicitud HTTP que contiene los datos necesarios para generar el reporte.
     * @param resp Respuesta HTTP que devuelve el reporte PDF o un mensaje de error.
     * @throws ServletException Si ocurre un error relacionado con el servlet.
     * @throws IOException      Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletOutputStream out = resp.getOutputStream();
        try {
            // Obtener el carrito de compras y otros datos de la sesión
            HttpSession session = req.getSession();
            Carro carro = (Carro) session.getAttribute("carro");
            InputStream logoEmpresa = this.getServletConfig().getServletContext().getResourceAsStream("/reportesJasper/img/logosinfondo.png");
            InputStream reporteProducto = this.getServletConfig().getServletContext().getResourceAsStream("/reportesJasper/BoletaProuctos.jasper");

            // Validar que los recursos necesarios estén disponibles
            if (logoEmpresa != null && reporteProducto != null) {
                // Preparar parámetros para JasperReports
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("logoEmpresa", logoEmpresa);

                List<DatalleProducto> detallesReporte = new ArrayList<>();
                detallesReporte.add(new DatalleProducto());
                detallesReporte.addAll(carro.getDetalleProductos());
                JRBeanArrayDataSource ds = new JRBeanArrayDataSource(detallesReporte.toArray());
                parameters.put("ds", ds);

                // Generar el reporte PDF
                JasperReport reporte = (JasperReport) JRLoader.loadObject(reporteProducto);
                JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parameters, ds);
                resp.setContentType("application/pdf");
                resp.setHeader("Content-Disposition", "inline; filename=boleta.pdf");
                JasperExportManager.exportReportToPdfStream(jasperPrint, out);
                out.flush();
                out.close();

                // Registrar la venta en la base de datos
                Connection conn = ConexionBaseDatos.getConnection();
                conn.setAutoCommit(false);
                VentaServiceJdbcImpl ventaService = new VentaServiceJdbcImpl(conn);
                Usuario usuario = (Usuario) session.getAttribute("usuario");
                String estado = "pendiente";
                int ventaId = ventaService.insertarVenta(usuario.getId(), estado, carro.getTotal());

                // Insertar los detalles de la venta
                List<DetalleVenta> detallesVenta = new ArrayList<>();
                for (ItemCarro item : carro.getItems()) {
                    DetalleVenta detalle = new DetalleVenta(
                        ventaId,
                        item.getProducto(),
                        item.getCantidad(),
                        item.getProducto().getPrecio(),
                        item.getCantidad() * item.getProducto().getPrecio()
                    );
                    detallesVenta.add(detalle);
                }
                ventaService.insertarDetalleVenta(ventaId, detallesVenta);
                conn.commit();

                // Actualizar el historial de compras del usuario
                List<Venta> historialVentas = ventaService.obtenerHistorialVentas(usuario.getId());
                session.setAttribute("historialCompras", historialVentas);
                carro.getItems().clear();
            } else {
                // Manejar el caso en que los recursos faltan
                resp.setContentType("text/plain");
                out.println("No se pudo generar el reporte.");
            }
        } catch (Exception e) {
            // Manejar excepciones
            resp.setContentType("text/plain");
            out.print("Ocurrió un error al intentar generar el reporte: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

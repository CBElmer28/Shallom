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

@WebServlet("/usuario/carro/exportarproduc")
public class ExportarBoletaServelet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletOutputStream out = resp.getOutputStream();
        try  {
        HttpSession session = req.getSession();
        Carro carro = (Carro) session.getAttribute("carro");
        InputStream logoEmpresa = this.getServletConfig().getServletContext().getResourceAsStream("/reportesJasper/img/logosinfondo.png");
        InputStream reporteProducto = this.getServletConfig().getServletContext().getResourceAsStream("/reportesJasper/BoletaProuctos.jasper");

        if (logoEmpresa!=null && reporteProducto!=null) {

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("logoEmpresa", logoEmpresa);

            // Usa la lista de DetalleProductoDTO en lugar de los items del carro
            List<DatalleProducto> detallesReporte = new ArrayList<>();
            detallesReporte.add(new DatalleProducto());
            detallesReporte.addAll(carro.getDetalleProductos());
            JRBeanArrayDataSource ds = new JRBeanArrayDataSource(detallesReporte.toArray());
            parameters.put("ds", ds);
            JasperReport reporte = (JasperReport) JRLoader.loadObject(reporteProducto);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parameters, ds);
            resp.setContentType("application/pdf");
            resp.setHeader("Content-Disposition", "inline; filename=boleta.pdf");

            JasperExportManager.exportReportToPdfStream(jasperPrint, out);
            out.flush();
            out.close();


            Connection conn = ConexionBaseDatos.getConnection();
            conn.setAutoCommit(false);
            VentaServiceJdbcImpl ventaService = new VentaServiceJdbcImpl(conn);
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            String t= "pendiente";
            conn.commit();
            int o = ventaService.insertarVenta(usuario.getId(),t, carro.getTotal());
            List<DetalleVenta> det= new ArrayList<>();
            for(ItemCarro v: carro.getItems()){
                DetalleVenta dv = new DetalleVenta(o,v.getProducto(),v.getCantidad(),v.getProducto().getPrecio(),(v.getCantidad()*v.getProducto().getPrecio()));
                 det.add(dv);
            }
            conn.commit();
            ventaService.insertarDetalleVenta(o,det);
            conn.commit();
            List<Venta> historialVentas = ventaService.obtenerHistorialVentas(usuario.getId());
            session.setAttribute("historialCompras", historialVentas);
            carro.getItems().clear();

          }else {
            resp.setContentType("text/plain");
            out.println("no se pudeo generar el reporte");
        }
        } catch (Exception e) {
            resp.setContentType("text/plain");
            out.print("ocurrio un error al intentar generar el reporte: "+e.getMessage());
            e.printStackTrace();
        }
    }
}

package org.cahuas.webapp.servelet.cabeceras.controlador;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.cahuas.webapp.servelet.cabeceras.models.modelo.Carro;
import org.cahuas.webapp.servelet.cabeceras.models.modelo.DatalleProducto;
import org.cahuas.webapp.servelet.cabeceras.models.modelo.Usuario;

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
        InputStream logoEmpresa = this.getServletConfig().getServletContext().getResourceAsStream("/reportesJasper/img/logoo.png");
        InputStream reporteProducto = this.getServletConfig().getServletContext().getResourceAsStream("/reportesJasper/BoletaProuctos.jasper");

        if (logoEmpresa!=null && reporteProducto!=null) {

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("logoEmpresa", logoEmpresa);
            Gson gson = new Gson();
            // Usa la lista de DetalleProductoDTO en lugar de los items del carro
            List<DatalleProducto> detallesReporte = carro.getDetalleProductos();
            //detallesReporte.addAll(carro.getDetalleProductos());
            detallesReporte.add(new DatalleProducto(5,"keyen",6,7));
            //DatalleProducto[] detallesArray = detallesReporte.toArray(new DatalleProducto[0]);
          //  DatalleProducto[] detallesArray = {new DatalleProducto("hola",10,15,20)};
              JasperReport reporte = (JasperReport) JRLoader.loadObject(reporteProducto);

            JRBeanArrayDataSource ds = new JRBeanArrayDataSource(detallesReporte.toArray());
            parameters.put("ds", ds);
           // parameters.put("keyen", keyen);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parameters, ds);
            resp.setContentType("application/pdf");
            resp.setHeader("Content-Disposition", "inline; filename=boleta.pdf");

            JasperExportManager.exportReportToPdfStream(jasperPrint, out);
            out.flush();
            out.close();
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

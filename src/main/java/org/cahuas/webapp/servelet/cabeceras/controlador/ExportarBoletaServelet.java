package org.cahuas.webapp.servelet.cabeceras.controlador;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
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

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

/**
 * Servlet para exportar una boleta en formato PDF y enviarla por correo.
 *
 * @author Team Shalom
 * @version 1.9
 */
@WebServlet("/usuario/carro/exportarproduc")
public class ExportarBoletaServelet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        Connection conn = null;

        try {
            // Obtener el usuario de la sesión
            HttpSession session = req.getSession();
            Usuario usuario = (Usuario) session.getAttribute("usuario");

            if (usuario == null) {
                resp.setContentType("text/plain");
                out.print("El usuario no ha iniciado sesión.");
                return;
            }

            // Obtener el correo del cliente asociado al usuario
            conn = ConexionBaseDatos.getConnection();
            String correoCliente = obtenerCorreoCliente(conn, usuario.getId());

            if (correoCliente == null || correoCliente.isEmpty()) {
                resp.setContentType("text/plain");
                out.print("No se encontró un correo asociado al usuario.");
                return;
            }

            // Obtener el carrito de compras
            Carro carro = (Carro) session.getAttribute("carro");
            InputStream logoEmpresa = this.getServletConfig().getServletContext().getResourceAsStream("/reportesJasper/img/logosinfondo.png");
            InputStream reporteProducto = this.getServletConfig().getServletContext().getResourceAsStream("/reportesJasper/BoletaProuctos.jasper");

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
                ByteArrayOutputStream pdfOutput = new ByteArrayOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, pdfOutput);

                // Registrar la venta en la base de datos
                conn.setAutoCommit(false);
                VentaServiceJdbcImpl ventaService = new VentaServiceJdbcImpl(conn);
                String estado = "pendiente";
                int ventaId = ventaService.insertarVenta(usuario.getId(), estado, carro.getTotal());

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

                // Enviar el PDF por correo
                enviarCorreoConPDF(correoCliente, pdfOutput.toByteArray(), "Boleta de compra", "Gracias por tu compra. Adjuntamos tu boleta.");

                // Actualizar el historial de compras del usuario
                List<Venta> historialVentas = ventaService.obtenerHistorialVentas(usuario.getId());
                session.setAttribute("historialCompras", historialVentas);
                carro.getItems().clear();

                resp.setContentType("text/plain");
                out.print("Boleta generada y enviada exitosamente.");
            } else {
                resp.setContentType("text/plain");
                out.println("No se pudo generar el reporte.");
            }
        } catch (Exception e) {
            resp.setContentType("text/plain");
            out.print("Ocurrió un error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String obtenerCorreoCliente(Connection conn, int usuarioId) throws Exception {
        String correo = null;
        String sql = "SELECT co FROM clientes WHERE id_usuario = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    correo = rs.getString("co");
                }
            }
        }
        return correo;
    }

    private void enviarCorreoConPDF(String destinatario, byte[] pdfBytes, String asunto, String mensajeTexto) throws MessagingException {
        String remitente = "shalomoficial12@gmail.com";
        String clave = "vvyhyxukcysumxcd";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props, new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, clave);
            }
        });

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(remitente));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
        message.setSubject(asunto);

        MimeBodyPart texto = new MimeBodyPart();
        texto.setText(mensajeTexto);

        MimeBodyPart adjunto = new MimeBodyPart();
        adjunto.setDataHandler(new jakarta.activation.DataHandler(new jakarta.activation.DataSource() {
            @Override
            public InputStream getInputStream() throws IOException {
                return new ByteArrayInputStream(pdfBytes);
            }

            @Override
            public OutputStream getOutputStream() throws IOException {
                throw new UnsupportedOperationException("No se soporta esta operación.");
            }

            @Override
            public String getContentType() {
                return "application/pdf";
            }

            @Override
            public String getName() {
                return "boleta.pdf";
            }
        }));
        adjunto.setFileName("boleta.pdf");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(texto);
        multipart.addBodyPart(adjunto);

        message.setContent(multipart);
        Transport.send(message);
    }
}

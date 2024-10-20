package org.cahuas.webapp.servelet.cabeceras.controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.cahuas.webapp.servelet.cabeceras.models.modelo.Producto;
import org.cahuas.webapp.servelet.cabeceras.models.modelo.Proveedor;
import org.cahuas.webapp.servelet.cabeceras.models.services.ProductoService;
import org.cahuas.webapp.servelet.cabeceras.models.services.ProductoServiceJdbcImpl;
import org.cahuas.webapp.servelet.cabeceras.models.util.ConexionBaseDatos;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1 MB
        maxFileSize = 1024 * 1024 * 5,   // 5 MB
        maxRequestSize = 1024 * 1024 * 10 // 10 MB
)
@WebServlet("/agregar-producto")
public class AgregarProductoServelet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Connection conn = ConexionBaseDatos.getConnection();
            String nombre = request.getParameter("nombre");
            String cat = request.getParameter("cat");
            Integer precio;
            try {
                precio = Integer.valueOf(request.getParameter("precio"));
            } catch (NumberFormatException e){
                precio = 0;
            }

            Integer stock;
            try {
                stock = Integer.valueOf(request.getParameter("stock"));
            } catch (NumberFormatException e){
                stock = 1;
            }

            Integer idProveedor;
            try {
                idProveedor = Integer.valueOf(request.getParameter("idProveedor"));
            } catch (NumberFormatException e){
                idProveedor = 1;
            }
            ProductoServiceJdbcImpl a = new ProductoServiceJdbcImpl(conn);
            Proveedor t;
            t=a.porIdCategoria(idProveedor);

            Part filePart = request.getPart("imagen"); // Asegúrate de que "imagen" coincida con el nombre en el formulario
            if (filePart != null && filePart.getSize() > 0) {
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String uploadPath = getServletContext().getRealPath("/")+"usuario/images";

                File file = new File(uploadPath);
                if (!file.exists()) {
                    file.mkdir();
                }

                filePart.write(uploadPath + File.separator + fileName);
            Producto producto = new Producto(nombre, cat,precio, stock, t, fileName); // fileName es solo "producto1.jpg"
            a.guardar(producto);
            resp.sendRedirect(request.getContextPath() +"/productos ");

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

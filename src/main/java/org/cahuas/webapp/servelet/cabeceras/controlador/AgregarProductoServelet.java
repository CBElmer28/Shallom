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
import org.cahuas.webapp.servelet.cabeceras.models.services.ProductoServiceJdbcImpl;
import org.cahuas.webapp.servelet.cabeceras.models.util.ConexionBaseDatos;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Servlet que gestiona la adición de productos a través de un formulario.
 * <p>
 * Este servlet permite cargar productos con datos básicos como nombre, categoría, precio,
 * stock, proveedor, y una imagen asociada. Además, guarda la información del producto en
 * la base de datos y almacena la imagen en el servidor.
 * </p>
 *
 * @author Team SHALOM
 * @version 1.8
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1 MB
        maxFileSize = 1024 * 1024 * 5,   // 5 MB
        maxRequestSize = 1024 * 1024 * 10 // 10 MB
)
@WebServlet("/agregar-producto")
public class AgregarProductoServelet extends HttpServlet {

    /**
     * Método que procesa las solicitudes POST para agregar un nuevo producto.
     * <p>
     * Los datos requeridos incluyen:
     * <ul>
     *     <li><b>nombre</b>: Nombre del producto.</li>
     *     <li><b>cat</b>: Categoría del producto.</li>
     *     <li><b>precio</b>: Precio del producto. Si no es numérico, se asigna 0.</li>
     *     <li><b>stock</b>: Stock del producto. Si no es numérico, se asigna 1.</li>
     *     <li><b>idProveedor</b>: ID del proveedor. Si no es numérico, se asigna 1.</li>
     *     <li><b>imagen</b>: Archivo de imagen asociado al producto.</li>
     * </ul>
     * </p>
     * Si el archivo de imagen es válido, se guarda en el servidor y el producto se almacena
     * en la base de datos.
     *
     * @param request La solicitud HTTP recibida con los datos del formulario.
     * @param resp    La respuesta HTTP enviada al cliente.
     * @throws ServletException Si ocurre un error relacionado con el servlet.
     * @throws IOException      Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Connection conn = ConexionBaseDatos.getConnection();

            String nombre = request.getParameter("nombre");
            String cat = request.getParameter("cat");

            // Validación de precio
            Integer precio;
            try {
                precio = Integer.valueOf(request.getParameter("precio"));
            } catch (NumberFormatException e) {
                precio = 0;
            }

            // Validación de stock
            Integer stock;
            try {
                stock = Integer.valueOf(request.getParameter("stock"));
            } catch (NumberFormatException e) {
                stock = 1;
            }

            // Validación de idProveedor
            Integer idProveedor;
            try {
                idProveedor = Integer.valueOf(request.getParameter("idProveedor"));
            } catch (NumberFormatException e) {
                idProveedor = 1;
            }

            ProductoServiceJdbcImpl a = new ProductoServiceJdbcImpl(conn);
            Proveedor t = a.porIdCategoria(idProveedor);

            Part filePart = request.getPart("imagen");
            if (filePart != null && filePart.getSize() > 0) {
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String uploadPath = getServletContext().getRealPath("/") + "usuario/images";

                File file = new File(uploadPath);
                if (!file.exists()) {
                    file.mkdir();
                }

                filePart.write(uploadPath + File.separator + fileName);

                Producto producto = new Producto(nombre, cat, precio, stock, t, fileName);
                a.guardar(producto);

                resp.sendRedirect(request.getContextPath() + "/productos");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
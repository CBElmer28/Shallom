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

// Configuración de la clase servlet para manejar archivos cargados (archivos de imagen)
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1 MB
        maxFileSize = 1024 * 1024 * 5,   // 5 MB
        maxRequestSize = 1024 * 1024 * 10 // 10 MB
)
@WebServlet("/agregar-producto") // URL donde este servlet está disponible
public class AgregarProductoServelet extends HttpServlet {

    // Método para manejar la solicitud POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Establecer conexión a la base de datos
            Connection conn = ConexionBaseDatos.getConnection();

            // Obtener los parámetros del formulario (nombre, categoría, precio, stock, idProveedor)
            String nombre = request.getParameter("nombre");
            String cat = request.getParameter("cat");

            // Validación de precio (si no es un número, se asigna un valor predeterminado de 0)
            Integer precio;
            try {
                precio = Integer.valueOf(request.getParameter("precio"));
            } catch (NumberFormatException e){
                precio = 0;
            }

            // Validación de stock (si no es un número, se asigna un valor predeterminado de 1)
            Integer stock;
            try {
                stock = Integer.valueOf(request.getParameter("stock"));
            } catch (NumberFormatException e){
                stock = 1;
            }

            // Validación del idProveedor (si no es un número, se asigna un valor predeterminado de 1)
            Integer idProveedor;
            try {
                idProveedor = Integer.valueOf(request.getParameter("idProveedor"));
            } catch (NumberFormatException e){
                idProveedor = 1;
            }

            // Instanciar el servicio para manejar la lógica de productos y obtener el proveedor
            ProductoServiceJdbcImpl a = new ProductoServiceJdbcImpl(conn);
            Proveedor t;
            t = a.porIdCategoria(idProveedor); // Obtener el proveedor por su ID

            // Manejo de archivo cargado (imagen del producto)
            Part filePart = request.getPart("imagen"); // Obtener el archivo de imagen del formulario
            if (filePart != null && filePart.getSize() > 0) {
                // Obtener el nombre del archivo y preparar la ruta de carga
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String uploadPath = getServletContext().getRealPath("/") + "usuario/images";

                // Crear la carpeta de destino si no existe
                File file = new File(uploadPath);
                if (!file.exists()) {
                    file.mkdir(); // Crear el directorio si no existe
                }

                // Guardar el archivo en el directorio de destino
                filePart.write(uploadPath + File.separator + fileName);
                
                // Crear un objeto Producto con los datos recibidos, incluyendo el nombre de archivo de la imagen
                Producto producto = new Producto(nombre, cat, precio, stock, t, fileName);
                
                // Guardar el producto en la base de datos
                a.guardar(producto);
                
                // Redirigir al usuario a la lista de productos
                resp.sendRedirect(request.getContextPath() + "/productos");
            }
        } catch (SQLException e) {
            // Manejo de excepciones en caso de error con la base de datos
            throw new RuntimeException(e);
        }
    }
}

package org.cahuas.webapp.servelet.cabeceras.controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.cahuas.webapp.servelet.cabeceras.models.modelo.Producto;
import org.cahuas.webapp.servelet.cabeceras.models.services.ProductoService;
import org.cahuas.webapp.servelet.cabeceras.models.services.ProductoServiceJdbcImpl;
import org.cahuas.webapp.servelet.cabeceras.models.util.ConexionBaseDatos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/ExportarExcel")
public class ExportarExcelServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Establecer la conexión a la base de datos
        Connection connection = null;
        try {
            connection = ConexionBaseDatos.getConnection();  // Obtiene la conexión con la base de datos
        } catch (SQLException e) {
            throw new RuntimeException(e);  // Maneja la excepción si no se puede conectar a la base de datos
        }
        
        // Crear el servicio para manejar los productos y obtener la lista de productos
        ProductoService service = new ProductoServiceJdbcImpl(connection);
        List<Producto> productos = service.listar();  // Obtiene todos los productos desde la base de datos
        
        // Configurar la respuesta HTTP para descargar el archivo Excel
        resp.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        resp.setHeader("Content-Disposition", "attachment; filename=productos.xlsx");  // Define el nombre del archivo de salida

        try (XSSFWorkbook workbook = new XSSFWorkbook()) {  // Crea un libro de trabajo de Excel usando Apache POI
            // Crear la hoja de trabajo y el encabezado
            Sheet sheet = workbook.createSheet("Productos");
            Row headerRow = sheet.createRow(0);  // Fila de encabezado en la primera fila
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Nombre");
            headerRow.createCell(2).setCellValue("Precio");
            headerRow.createCell(3).setCellValue("Cantidad");

            // Llenar la hoja con los datos de los productos
            int rowNum = 1;  // Comienza en la segunda fila
            for (Producto producto : productos) {
                Row row = sheet.createRow(rowNum++);  // Crea una nueva fila por cada producto
                row.createCell(0).setCellValue(producto.getId());  // Establece el ID del producto
                row.createCell(1).setCellValue(producto.getNom());  // Establece el nombre del producto
                row.createCell(2).setCellValue(producto.getPrecio());  // Establece el precio del producto
                row.createCell(3).setCellValue(producto.getStock());  // Establece la cantidad en stock del producto
            }

            // Escribir el archivo Excel en la respuesta
            ServletOutputStream outputStream = resp.getOutputStream();
            workbook.write(outputStream);  // Escribe el contenido del libro en el flujo de salida
            outputStream.flush();  // Asegura que todo el contenido se envíe al cliente
        }
    }
}

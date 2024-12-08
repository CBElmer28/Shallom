
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

/**
 * Servlet que exporta los productos en formato Excel.
 * Esta clase maneja la exportación de la lista de productos a un archivo Excel.
 * El archivo generado incluye las columnas: ID, Nombre, Precio y Cantidad.
 *
 * @author Team Shalom
 * @version 1.0
 */
@WebServlet("/ExportarExcel")
public class ExportarExcelServlet extends HttpServlet {

    /**
     * Maneja la solicitud GET para exportar la lista de productos a un archivo Excel.
     * 
     * @param req la solicitud HTTP
     * @param resp la respuesta HTTP
     * @throws ServletException si ocurre un error en el procesamiento del servlet
     * @throws IOException si ocurre un error de entrada/salida
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Establecer la conexión a la base de datos
        Connection connection = null;
        try {
            // Establece la conexión a la base de datos
            connection = ConexionBaseDatos.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);  // Maneja la excepción si no se puede conectar a la base de datos
        }
        
        // Obtiene el servicio de productos y la lista de productos
        ProductoService service = new ProductoServiceJdbcImpl(connection);
        List<Producto> productos = service.listar();

        // Configura el tipo de contenido para una descarga de archivo Excel
        resp.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        resp.setHeader("Content-Disposition", "attachment; filename=productos.xlsx");  // Define el nombre del archivo de salida

        // Crea un libro de trabajo de Excel en memoria
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            // Crea una hoja llamada "Productos"
            Sheet sheet = workbook.createSheet("Productos");

            // Crea y configura los estilos para las celdas del encabezado
            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 14);
            headerFont.setColor(IndexedColors.WHITE.getIndex());
            headerStyle.setFont(headerFont);
            headerStyle.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderTop(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);

            // Crea y configura los estilos para las celdas de los datos
            CellStyle dataStyle = workbook.createCellStyle();
            Font dataFont = workbook.createFont();
            dataFont.setFontHeightInPoints((short) 12);
            dataStyle.setFont(dataFont);
            dataStyle.setAlignment(HorizontalAlignment.LEFT);
            dataStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            dataStyle.setBorderBottom(BorderStyle.THIN);
            dataStyle.setBorderTop(BorderStyle.THIN);
            dataStyle.setBorderLeft(BorderStyle.THIN);
            dataStyle.setBorderRight(BorderStyle.THIN);

            // Crea la fila de encabezado con los nombres de las columnas
            Row headerRow = sheet.createRow(0);
            String[] headers = {"ID", "Nombre", "Precio", "Cantidad"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            // Llena las filas con los datos de los productos
            int rowNum = 1;
            for (Producto producto : productos) {
                Row row = sheet.createRow(rowNum++);
                Cell cellId = row.createCell(0);
                cellId.setCellValue(producto.getId());
                cellId.setCellStyle(dataStyle);

                Cell cellName = row.createCell(1);
                cellName.setCellValue(producto.getNom());
                cellName.setCellStyle(dataStyle);

                Cell cellPrice = row.createCell(2);
                cellPrice.setCellValue(producto.getPrecio());
                cellPrice.setCellStyle(dataStyle);

                Cell cellStock = row.createCell(3);
                cellStock.setCellValue(producto.getStock());
                cellStock.setCellStyle(dataStyle);
            }

            // Ajusta el tamaño de las columnas automáticamente
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Escribe el archivo Excel en la respuesta HTTP
            ServletOutputStream outputStream = resp.getOutputStream();
            workbook.write(outputStream);  // Escribe el contenido del libro en el flujo de salida
            outputStream.flush();  // Asegura que todo el contenido se envíe al cliente
        }
    }
}

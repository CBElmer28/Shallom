package org.cahuas.webapp.servelet.cabeceras.controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
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
        Connection connection;
        try {
            connection = ConexionBaseDatos.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ProductoService service = new ProductoServiceJdbcImpl(connection);
        List<Producto> productos = service.listar();

        // Configuración para el archivo Excel
        resp.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        resp.setHeader("Content-Disposition", "attachment; filename=productos.xlsx");

        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Productos");

            // Estilos personalizados
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

            // Crea la fila de encabezado
            Row headerRow = sheet.createRow(0);
            String[] headers = {"ID", "Nombre", "Precio", "Cantidad"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            // Llena los datos de productos
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

            // Ajustar el tamaño de las columnas automáticamente
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Escribir el archivo Excel al cliente
            ServletOutputStream outputStream = resp.getOutputStream();
            workbook.write(outputStream);
            outputStream.flush();
        }
    }
}
package org.cahuas.webapp.servelet.cabeceras.controlador;

import static org.junit.jupiter.api.Assertions.*;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.cahuas.webapp.servelet.cabeceras.models.modelo.Producto;
import org.cahuas.webapp.servelet.cabeceras.models.services.ProductoService;
import org.cahuas.webapp.servelet.cabeceras.models.util.ConexionBaseDatos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class ExportarExcelServletTest {

    private ExportarExcelServlet servlet;

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private ProductoService productService;
    @Mock
    private Connection connection;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        servlet = new ExportarExcelServlet();
    }

    @Test
    void doGet_exportaExcelCorrectamente() throws Exception {
        // Mockear la conexión y la lista de productos
        when(ConexionBaseDatos.getConnection()).thenReturn(connection);
        Producto producto1 = new Producto(1, "Producto 1", 100, 50);
        Producto producto2 = new Producto(2, "Producto 2", 200, 30);
        List<Producto> productos = Arrays.asList(producto1, producto2);

        when(productService.listar()).thenReturn(productos);

        // Configurar los mocks para HttpServletResponse
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ServletOutputStream outputStream = new ServletOutputStreamWrapper(byteArrayOutputStream);
        when(response.getOutputStream()).thenReturn(outputStream);

        // Llamar al servlet
        servlet.doGet(request, response);

        // Verificar que el contenido generado es un archivo Excel (XLSX)
        verify(response).setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        verify(response).setHeader("Content-Disposition", "attachment; filename=productos.xlsx");

        // Capturar el contenido generado
        ArgumentCaptor<byte[]> byteArrayCaptor = ArgumentCaptor.forClass(byte[].class);
        verify(response.getOutputStream()).write(byteArrayCaptor.capture());

        // Verificar si el contenido es un archivo Excel
        byte[] excelData = byteArrayCaptor.getValue();
        assertTrue(excelData.length > 0, "El archivo Excel no está vacío");

        // Aquí también puedes validar el contenido del archivo Excel usando Apache POI
        try (XSSFWorkbook workbook = new XSSFWorkbook(Arrays.toString(byteArrayOutputStream.toByteArray()))) {
            org.apache.poi.xssf.usermodel.XSSFSheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);
            assertEquals("ID", headerRow.getCell(0).getStringCellValue());
            assertEquals("Nombre", headerRow.getCell(1).getStringCellValue());
            assertEquals("Precio", headerRow.getCell(2).getStringCellValue());
            assertEquals("Cantidad", headerRow.getCell(3).getStringCellValue());

            // Validar los datos de los productos
            assertEquals(2, sheet.getPhysicalNumberOfRows());
            assertEquals(1, sheet.getRow(1).getCell(0).getNumericCellValue(), 0.01);
            assertEquals("Producto 1", sheet.getRow(1).getCell(1).getStringCellValue());
            assertEquals(100.0, sheet.getRow(1).getCell(2).getNumericCellValue(), 0.01);
            assertEquals(50, sheet.getRow(1).getCell(3).getNumericCellValue());
        }
    }
}

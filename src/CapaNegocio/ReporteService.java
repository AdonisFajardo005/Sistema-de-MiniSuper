/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaNegocio;

/**
 *
 * @author adoni
 */
import CapaDatos.ClienteDAO;
import CapaDatos.ComprasDAO;
import CapaDatos.ProductosDAO;
import CapaDatos.ProveedoresDAO;
import CapaDatos.VentaDAO;
import CapaModelo.Cliente;
import CapaModelo.Compra;
import CapaModelo.Producto;
import CapaModelo.Proveedor;
import CapaModelo.Venta;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;

import java.awt.Desktop;
import java.io.File;

public class ReporteService {

    private final String rutaBase
            = "C:\\Users\\adoni\\OneDrive\\Documentos\\INGENIERIA\\ANÁLISIS Y DISEÑO DE SISTEMAS II\\REPORTES MINISUPER ZONA AZUL\\";

    public void generarReporteClientes() {

        try {

            ClienteDAO dao = new ClienteDAO();
            List<Cliente> lista = dao.listar();

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Clientes");

            int rowIndex = 0;

            // =====================================================
            // CONFIGURAR ANCHO PARA QUE EL LOGO TENGA 17.4 CM
            // 1 cm ≈ 567 unidades Excel
            // 17.4 cm × 567 ≈ 9866
            // =====================================================
            sheet.setColumnWidth(0, 9866);
            sheet.setColumnWidth(1, 4000);
            sheet.setColumnWidth(2, 4000);
            sheet.setColumnWidth(3, 6000);

            // =====================================================
            // CREAR FILA CON 2.51 CM DE ALTO
            // 2.51 cm ≈ 71 puntos
            // =====================================================
            Row logoRow = sheet.createRow(0);
            logoRow.setHeightInPoints(71);

            // =====================================================
            // INSERTAR LOGO
            // =====================================================
            InputStream inputStream = getClass().getResourceAsStream("/recursos/logo.png");

            if (inputStream != null) {

                byte[] bytes = IOUtils.toByteArray(inputStream);
                int pictureIdx = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
                inputStream.close();

                Drawing<?> drawing = sheet.createDrawingPatriarch();
                CreationHelper helper = workbook.getCreationHelper();
                ClientAnchor anchor = helper.createClientAnchor();

                anchor.setCol1(0);
                anchor.setRow1(0);
                anchor.setCol2(1);
                anchor.setRow2(1);

                Picture pict = drawing.createPicture(anchor, pictureIdx);

                // Ajustar proporcionalmente al espacio creado
                pict.resize();
            }

            // Dejar espacio debajo del logo
            rowIndex = 3;

            // =====================================================
            // ESTILO TITULO
            // =====================================================
            Font tituloFont = workbook.createFont();
            tituloFont.setBold(true);
            tituloFont.setFontHeightInPoints((short) 18);

            CellStyle tituloStyle = workbook.createCellStyle();
            tituloStyle.setFont(tituloFont);
            tituloStyle.setAlignment(HorizontalAlignment.CENTER);

            // =====================================================
            // TITULO PRINCIPAL
            // =====================================================
            Row tituloRow = sheet.createRow(rowIndex++);
            Cell tituloCell = tituloRow.createCell(0);
            tituloCell.setCellValue("MINISUPER ZONA AZUL");
            tituloCell.setCellStyle(tituloStyle);

            sheet.addMergedRegion(new CellRangeAddress(
                    tituloRow.getRowNum(), tituloRow.getRowNum(), 0, 3));

            // SUBTITULO
            Row subRow = sheet.createRow(rowIndex++);
            Cell subCell = subRow.createCell(0);
            subCell.setCellValue("REPORTE DE CLIENTES");
            subCell.setCellStyle(tituloStyle);

            sheet.addMergedRegion(new CellRangeAddress(
                    subRow.getRowNum(), subRow.getRowNum(), 0, 3));

            rowIndex++;

            // =====================================================
            // FECHA Y HORA
            // =====================================================
            LocalDateTime ahora = LocalDateTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

            Row fechaRow = sheet.createRow(rowIndex++);
            fechaRow.createCell(0)
                    .setCellValue("Fecha y Hora: " + ahora.format(formato));

            rowIndex++;

            // =====================================================
            // ESTILOS TABLA
            // =====================================================
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.WHITE.getIndex());

            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFont(headerFont);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderTop(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);

            CellStyle borderStyle = workbook.createCellStyle();
            borderStyle.setBorderBottom(BorderStyle.THIN);
            borderStyle.setBorderTop(BorderStyle.THIN);
            borderStyle.setBorderLeft(BorderStyle.THIN);
            borderStyle.setBorderRight(BorderStyle.THIN);

            // =====================================================
            // ENCABEZADOS
            // =====================================================
            String[] columnas = {"Cédula", "Nombre", "Celular", "Dirección"};

            Row headerRow = sheet.createRow(rowIndex++);

            for (int i = 0; i < columnas.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columnas[i]);
                cell.setCellStyle(headerStyle);
            }

            // =====================================================
            // DATOS
            // =====================================================
            for (Cliente c : lista) {

                Row row = sheet.createRow(rowIndex++);

                row.createCell(0).setCellValue(c.getCedula());
                row.createCell(1).setCellValue(c.getNombre());
                row.createCell(2).setCellValue(c.getCelular());
                row.createCell(3).setCellValue(c.getDireccion());

                for (int i = 0; i < 4; i++) {
                    row.getCell(i).setCellStyle(borderStyle);
                }
            }

            rowIndex++;

            // TOTAL
            Row totalRow = sheet.createRow(rowIndex);
            totalRow.createCell(0)
                    .setCellValue("Total de registros: " + lista.size());

            // =====================================================
            // PIE DE PAGINA
            // =====================================================
            Footer footer = sheet.getFooter();
            footer.setCenter("MiniSuper Zona Azul - Página &P de &N");

            // =====================================================
            // GUARDAR ARCHIVO
            // =====================================================
            String nombreArchivo
                    = "Reporte_Clientes_" + System.currentTimeMillis() + ".xlsx";

            File file = new File(rutaBase + nombreArchivo);

            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.close();
            workbook.close();

            Desktop.getDesktop().open(file);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generarReporteVentas() {

        try {

            VentaDAO dao = new VentaDAO();
            List<Venta> lista = dao.listar();

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Ventas");

            int rowIndex = 0;

            // =====================================================
            // CONFIGURAR ANCHO PARA LOGO (17.4 CM)
            // =====================================================
            sheet.setColumnWidth(0, 9866); // 17.4 cm
            sheet.setColumnWidth(1, 4000);
            sheet.setColumnWidth(2, 5000);
            sheet.setColumnWidth(3, 4000);
            sheet.setColumnWidth(4, 4000);

            // =====================================================
            // FILA LOGO (2.51 CM)
            // =====================================================
            Row logoRow = sheet.createRow(0);
            logoRow.setHeightInPoints(71); // 2.51 cm

            // =====================================================
            // INSERTAR LOGO
            // =====================================================
            InputStream inputStream = getClass().getResourceAsStream("/recursos/logo.png");

            if (inputStream != null) {

                byte[] bytes = IOUtils.toByteArray(inputStream);
                int pictureIdx = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
                inputStream.close();

                Drawing<?> drawing = sheet.createDrawingPatriarch();
                CreationHelper helper = workbook.getCreationHelper();
                ClientAnchor anchor = helper.createClientAnchor();

                anchor.setCol1(0);
                anchor.setRow1(0);
                anchor.setCol2(1);
                anchor.setRow2(1);

                Picture pict = drawing.createPicture(anchor, pictureIdx);
                pict.resize();
            }

            rowIndex = 3;

            // =====================================================
            // ESTILO TITULO
            // =====================================================
            Font tituloFont = workbook.createFont();
            tituloFont.setBold(true);
            tituloFont.setFontHeightInPoints((short) 18);

            CellStyle tituloStyle = workbook.createCellStyle();
            tituloStyle.setFont(tituloFont);
            tituloStyle.setAlignment(HorizontalAlignment.CENTER);

            // TITULO PRINCIPAL
            Row tituloRow = sheet.createRow(rowIndex++);
            Cell tituloCell = tituloRow.createCell(0);
            tituloCell.setCellValue("MINISUPER ZONA AZUL");
            tituloCell.setCellStyle(tituloStyle);

            sheet.addMergedRegion(new CellRangeAddress(
                    tituloRow.getRowNum(), tituloRow.getRowNum(), 0, 4));

            // SUBTITULO
            Row subRow = sheet.createRow(rowIndex++);
            Cell subCell = subRow.createCell(0);
            subCell.setCellValue("REPORTE DE VENTAS");
            subCell.setCellStyle(tituloStyle);

            sheet.addMergedRegion(new CellRangeAddress(
                    subRow.getRowNum(), subRow.getRowNum(), 0, 4));

            rowIndex++;

            // =====================================================
            // FECHA Y HORA
            // =====================================================
            LocalDateTime ahora = LocalDateTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

            Row fechaRow = sheet.createRow(rowIndex++);
            fechaRow.createCell(0)
                    .setCellValue("Fecha y Hora: " + ahora.format(formato));

            rowIndex++;

            // =====================================================
            // ESTILOS TABLA
            // =====================================================
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.WHITE.getIndex());

            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFont(headerFont);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderTop(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);

            CellStyle borderStyle = workbook.createCellStyle();
            borderStyle.setBorderBottom(BorderStyle.THIN);
            borderStyle.setBorderTop(BorderStyle.THIN);
            borderStyle.setBorderLeft(BorderStyle.THIN);
            borderStyle.setBorderRight(BorderStyle.THIN);

            // =====================================================
            // ENCABEZADOS
            // =====================================================
            String[] columnas = {
                "ID Venta",
                "Fecha",
                "Producto",
                "Cantidad",
                "Precio Venta"
            };

            Row headerRow = sheet.createRow(rowIndex++);

            for (int i = 0; i < columnas.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columnas[i]);
                cell.setCellStyle(headerStyle);
            }

            double totalGeneral = 0;

            // =====================================================
            // DATOS
            // =====================================================
            for (Venta v : lista) {

                Row row = sheet.createRow(rowIndex++);

                row.createCell(0).setCellValue(v.getIdVenta());
                row.createCell(1).setCellValue(v.getFecha().toString());
                row.createCell(2).setCellValue(v.getNombreProducto());
                row.createCell(3).setCellValue(v.getCantidad());
                row.createCell(4).setCellValue(v.getPrecioVenta().doubleValue());

                totalGeneral += v.getCantidad() * v.getPrecioVenta().doubleValue();

                for (int i = 0; i < 5; i++) {
                    row.getCell(i).setCellStyle(borderStyle);
                }
            }

            rowIndex++;

            // TOTAL GENERAL
            Row totalRow = sheet.createRow(rowIndex);
            totalRow.createCell(0)
                    .setCellValue("TOTAL GENERAL ₡: " + totalGeneral);

            // =====================================================
            // PIE DE PAGINA
            // =====================================================
            Footer footer = sheet.getFooter();
            footer.setCenter("MiniSuper Zona Azul - Página &P de &N");

            // =====================================================
            // GUARDAR ARCHIVO
            // =====================================================
            String nombreArchivo
                    = "Reporte_Ventas_" + System.currentTimeMillis() + ".xlsx";

            File file = new File(rutaBase + nombreArchivo);

            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.close();
            workbook.close();

            Desktop.getDesktop().open(file);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generarReporteCompras() {

        try {

            ComprasDAO dao = new ComprasDAO();
            List<Compra> lista = dao.listar();

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Compras");

            int rowIndex = 0;

            // =====================================================
            // ANCHO DE COLUMNAS (17.4 cm aprox en col 0)
            // =====================================================
            sheet.setColumnWidth(0, 9866);
            sheet.setColumnWidth(1, 4000);
            sheet.setColumnWidth(2, 5000);
            sheet.setColumnWidth(3, 4000);
            sheet.setColumnWidth(4, 5000);
            sheet.setColumnWidth(5, 6000);

            // =====================================================
            // FILA LOGO (2.51 cm alto)
            // =====================================================
            Row logoRow = sheet.createRow(0);
            logoRow.setHeightInPoints(71);

            InputStream inputStream = getClass().getResourceAsStream("/recursos/logo.png");

            if (inputStream != null) {

                byte[] bytes = IOUtils.toByteArray(inputStream);
                int pictureIdx = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
                inputStream.close();

                Drawing<?> drawing = sheet.createDrawingPatriarch();
                CreationHelper helper = workbook.getCreationHelper();
                ClientAnchor anchor = helper.createClientAnchor();

                anchor.setCol1(0);
                anchor.setRow1(0);
                anchor.setCol2(1);
                anchor.setRow2(1);

                Picture pict = drawing.createPicture(anchor, pictureIdx);
                pict.resize();
            }

            rowIndex = 3;

            // =====================================================
            // ESTILO TITULO
            // =====================================================
            Font tituloFont = workbook.createFont();
            tituloFont.setBold(true);
            tituloFont.setFontHeightInPoints((short) 18);

            CellStyle tituloStyle = workbook.createCellStyle();
            tituloStyle.setFont(tituloFont);
            tituloStyle.setAlignment(HorizontalAlignment.CENTER);

            Row tituloRow = sheet.createRow(rowIndex++);
            Cell tituloCell = tituloRow.createCell(0);
            tituloCell.setCellValue("MINISUPER ZONA AZUL");
            tituloCell.setCellStyle(tituloStyle);

            sheet.addMergedRegion(new CellRangeAddress(
                    tituloRow.getRowNum(), tituloRow.getRowNum(), 0, 5));

            Row subRow = sheet.createRow(rowIndex++);
            Cell subCell = subRow.createCell(0);
            subCell.setCellValue("REPORTE DE COMPRAS");
            subCell.setCellStyle(tituloStyle);

            sheet.addMergedRegion(new CellRangeAddress(
                    subRow.getRowNum(), subRow.getRowNum(), 0, 5));

            rowIndex++;

            // =====================================================
            // FECHA Y HORA
            // =====================================================
            LocalDateTime ahora = LocalDateTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

            Row fechaRow = sheet.createRow(rowIndex++);
            fechaRow.createCell(0)
                    .setCellValue("Fecha y Hora: " + ahora.format(formato));

            rowIndex++;

            // =====================================================
            // ESTILOS TABLA
            // =====================================================
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.WHITE.getIndex());

            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFont(headerFont);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderTop(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);

            CellStyle borderStyle = workbook.createCellStyle();
            borderStyle.setBorderBottom(BorderStyle.THIN);
            borderStyle.setBorderTop(BorderStyle.THIN);
            borderStyle.setBorderLeft(BorderStyle.THIN);
            borderStyle.setBorderRight(BorderStyle.THIN);

            // ESTILO MONEDA ₡
            CellStyle currencyStyle = workbook.createCellStyle();
            DataFormat format = workbook.createDataFormat();
            currencyStyle.setDataFormat(format.getFormat("₡ #,##0.00"));
            currencyStyle.setBorderBottom(BorderStyle.THIN);
            currencyStyle.setBorderTop(BorderStyle.THIN);
            currencyStyle.setBorderLeft(BorderStyle.THIN);
            currencyStyle.setBorderRight(BorderStyle.THIN);

            // =====================================================
            // ENCABEZADOS
            // =====================================================
            String[] columnas = {
                "ID Compra",
                "Fecha",
                "Producto",
                "Cantidad",
                "Precio Compra",
                "Proveedor"
            };

            Row headerRow = sheet.createRow(rowIndex++);

            for (int i = 0; i < columnas.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columnas[i]);
                cell.setCellStyle(headerStyle);
            }

            double totalGeneral = 0;

            // =====================================================
            // DATOS
            // =====================================================
            for (Compra c : lista) {

                Row row = sheet.createRow(rowIndex++);

                row.createCell(0).setCellValue(c.getIdCompra());
                row.createCell(1).setCellValue(
                        c.getFecha() != null ? c.getFecha().toString() : "");
                row.createCell(2).setCellValue(
                        c.getNombreProducto() != null ? c.getNombreProducto() : "");
                row.createCell(3).setCellValue(c.getCantidad());

                double precio = c.getPrecioCompra();

                Cell precioCell = row.createCell(4);
                precioCell.setCellValue(precio);
                precioCell.setCellStyle(currencyStyle);

                row.createCell(5).setCellValue(
                        c.getProveedor() != null ? c.getProveedor() : "");

                totalGeneral += c.getCantidad() * precio;

                for (int i = 0; i < 6; i++) {
                    if (i != 4) {
                        row.getCell(i).setCellStyle(borderStyle);
                    }
                }
            }

            rowIndex++;

            // =====================================================
            // TOTAL GENERAL (SIN NOTACION CIENTIFICA)
            // =====================================================
            Row totalRow = sheet.createRow(rowIndex);

            Cell labelCell = totalRow.createCell(0);
            labelCell.setCellValue("TOTAL GENERAL ₡:");

            Cell totalCell = totalRow.createCell(1);
            totalCell.setCellValue(totalGeneral);
            totalCell.setCellStyle(currencyStyle);

            // =====================================================
            // PIE DE PAGINA
            // =====================================================
            Footer footer = sheet.getFooter();
            footer.setCenter("MiniSuper Zona Azul - Página &P de &N");

            // =====================================================
            // GUARDAR Y ABRIR
            // =====================================================
            String nombreArchivo
                    = "Reporte_Compras_" + System.currentTimeMillis() + ".xlsx";

            File file = new File(rutaBase + nombreArchivo);

            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.close();
            workbook.close();

            Desktop.getDesktop().open(file);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generarReporteProductos() {

        try {

            ProductosDAO dao = new ProductosDAO();
            List<Producto> lista = dao.listarProductos();

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Productos");

            int rowIndex = 0;

            // =====================================================
            // ANCHO COLUMNAS
            // =====================================================
            sheet.setColumnWidth(0, 9866);
            sheet.setColumnWidth(1, 5000);
            sheet.setColumnWidth(2, 4000);
            sheet.setColumnWidth(3, 5000);

            // =====================================================
            // FILA LOGO (2.51 cm)
            // =====================================================
            Row logoRow = sheet.createRow(0);
            logoRow.setHeightInPoints(71);

            InputStream inputStream = getClass().getResourceAsStream("/recursos/logo.png");

            if (inputStream != null) {

                byte[] bytes = IOUtils.toByteArray(inputStream);
                int pictureIdx = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
                inputStream.close();

                Drawing<?> drawing = sheet.createDrawingPatriarch();
                CreationHelper helper = workbook.getCreationHelper();
                ClientAnchor anchor = helper.createClientAnchor();

                anchor.setCol1(0);
                anchor.setRow1(0);
                anchor.setCol2(1);
                anchor.setRow2(1);

                Picture pict = drawing.createPicture(anchor, pictureIdx);
                pict.resize();
            }

            rowIndex = 3;

            // =====================================================
            // ESTILO TITULO
            // =====================================================
            Font tituloFont = workbook.createFont();
            tituloFont.setBold(true);
            tituloFont.setFontHeightInPoints((short) 18);

            CellStyle tituloStyle = workbook.createCellStyle();
            tituloStyle.setFont(tituloFont);
            tituloStyle.setAlignment(HorizontalAlignment.CENTER);

            Row tituloRow = sheet.createRow(rowIndex++);
            Cell tituloCell = tituloRow.createCell(0);
            tituloCell.setCellValue("MINISUPER ZONA AZUL");
            tituloCell.setCellStyle(tituloStyle);

            sheet.addMergedRegion(new CellRangeAddress(
                    tituloRow.getRowNum(), tituloRow.getRowNum(), 0, 3));

            Row subRow = sheet.createRow(rowIndex++);
            Cell subCell = subRow.createCell(0);
            subCell.setCellValue("REPORTE DE PRODUCTOS");
            subCell.setCellStyle(tituloStyle);

            sheet.addMergedRegion(new CellRangeAddress(
                    subRow.getRowNum(), subRow.getRowNum(), 0, 3));

            rowIndex++;

            // =====================================================
            // FECHA Y HORA
            // =====================================================
            LocalDateTime ahora = LocalDateTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

            Row fechaRow = sheet.createRow(rowIndex++);
            fechaRow.createCell(0)
                    .setCellValue("Fecha y Hora: " + ahora.format(formato));

            rowIndex++;

            // =====================================================
            // ESTILOS TABLA
            // =====================================================
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.WHITE.getIndex());

            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFont(headerFont);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            CellStyle borderStyle = workbook.createCellStyle();
            borderStyle.setBorderBottom(BorderStyle.THIN);
            borderStyle.setBorderTop(BorderStyle.THIN);
            borderStyle.setBorderLeft(BorderStyle.THIN);
            borderStyle.setBorderRight(BorderStyle.THIN);

            // ESTILO MONEDA ₡
            CellStyle currencyStyle = workbook.createCellStyle();
            DataFormat format = workbook.createDataFormat();
            currencyStyle.setDataFormat(format.getFormat("₡ #,##0.00"));
            currencyStyle.setBorderBottom(BorderStyle.THIN);
            currencyStyle.setBorderTop(BorderStyle.THIN);
            currencyStyle.setBorderLeft(BorderStyle.THIN);
            currencyStyle.setBorderRight(BorderStyle.THIN);

            // =====================================================
            // ENCABEZADOS
            // =====================================================
            String[] columnas = {
                "Código",
                "Nombre",
                "Cantidad en Stock",
                "Precio Unitario"
            };

            Row headerRow = sheet.createRow(rowIndex++);

            for (int i = 0; i < columnas.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columnas[i]);
                cell.setCellStyle(headerStyle);
            }

            double totalInventario = 0;

            // =====================================================
            // DATOS
            // =====================================================
            for (Producto p : lista) {

                Row row = sheet.createRow(rowIndex++);

                row.createCell(0).setCellValue(p.getCodigo());
                row.createCell(1).setCellValue(p.getNombre());
                row.createCell(2).setCellValue(p.getCantidadEnStock());

                double precio = 0;
                if (p.getPrecio() != null) {
                    precio = p.getPrecio().doubleValue();
                }

                Cell precioCell = row.createCell(3);
                precioCell.setCellValue(precio);
                precioCell.setCellStyle(currencyStyle);

                totalInventario += precio * p.getCantidadEnStock();

                row.getCell(0).setCellStyle(borderStyle);
                row.getCell(1).setCellStyle(borderStyle);
                row.getCell(2).setCellStyle(borderStyle);
            }

            rowIndex++;

            // =====================================================
            // TOTAL INVENTARIO
            // =====================================================
            Row totalRow = sheet.createRow(rowIndex);

            Cell labelCell = totalRow.createCell(0);
            labelCell.setCellValue("VALOR TOTAL INVENTARIO ₡:");

            Cell totalCell = totalRow.createCell(1);
            totalCell.setCellValue(totalInventario);
            totalCell.setCellStyle(currencyStyle);

            // =====================================================
            // PIE DE PAGINA
            // =====================================================
            Footer footer = sheet.getFooter();
            footer.setCenter("MiniSuper Zona Azul - Página &P de &N");

            // =====================================================
            // GUARDAR Y ABRIR
            // =====================================================
            String nombreArchivo
                    = "Reporte_Productos_" + System.currentTimeMillis() + ".xlsx";

            File file = new File(rutaBase + nombreArchivo);

            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.close();
            workbook.close();

            Desktop.getDesktop().open(file);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generarReporteProveedores() {

        try {

            ProveedoresDAO dao = new ProveedoresDAO();
            List<Proveedor> lista = dao.listarProveedores();

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Proveedores");

            int rowIndex = 0;

            // =====================================================
            // ANCHO COLUMNAS
            // =====================================================
            sheet.setColumnWidth(0, 9866);
            sheet.setColumnWidth(1, 5000);
            sheet.setColumnWidth(2, 4000);
            sheet.setColumnWidth(3, 5000);

            // =====================================================
            // FILA LOGO (2.51 cm)
            // =====================================================
            Row logoRow = sheet.createRow(0);
            logoRow.setHeightInPoints(71);

            InputStream inputStream = getClass().getResourceAsStream("/recursos/logo.png");

            if (inputStream != null) {

                byte[] bytes = IOUtils.toByteArray(inputStream);
                int pictureIdx = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
                inputStream.close();

                Drawing<?> drawing = sheet.createDrawingPatriarch();
                CreationHelper helper = workbook.getCreationHelper();
                ClientAnchor anchor = helper.createClientAnchor();

                anchor.setCol1(0);
                anchor.setRow1(0);
                anchor.setCol2(1);
                anchor.setRow2(1);

                Picture pict = drawing.createPicture(anchor, pictureIdx);
                pict.resize();
            }

            rowIndex = 3;

            // =====================================================
            // ESTILO TITULO
            // =====================================================
            Font tituloFont = workbook.createFont();
            tituloFont.setBold(true);
            tituloFont.setFontHeightInPoints((short) 18);

            CellStyle tituloStyle = workbook.createCellStyle();
            tituloStyle.setFont(tituloFont);
            tituloStyle.setAlignment(HorizontalAlignment.CENTER);

            Row tituloRow = sheet.createRow(rowIndex++);
            Cell tituloCell = tituloRow.createCell(0);
            tituloCell.setCellValue("MINISUPER ZONA AZUL");
            tituloCell.setCellStyle(tituloStyle);

            sheet.addMergedRegion(new CellRangeAddress(
                    tituloRow.getRowNum(), tituloRow.getRowNum(), 0, 3));

            Row subRow = sheet.createRow(rowIndex++);
            Cell subCell = subRow.createCell(0);
            subCell.setCellValue("REPORTE DE PROVEEDORES");
            subCell.setCellStyle(tituloStyle);

            sheet.addMergedRegion(new CellRangeAddress(
                    subRow.getRowNum(), subRow.getRowNum(), 0, 3));

            rowIndex++;

            // =====================================================
            // FECHA Y HORA
            // =====================================================
            LocalDateTime ahora = LocalDateTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

            Row fechaRow = sheet.createRow(rowIndex++);
            fechaRow.createCell(0)
                    .setCellValue("Fecha y Hora: " + ahora.format(formato));

            rowIndex++;

            // =====================================================
            // ESTILOS TABLA
            // =====================================================
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.WHITE.getIndex());

            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFont(headerFont);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            CellStyle borderStyle = workbook.createCellStyle();
            borderStyle.setBorderBottom(BorderStyle.THIN);
            borderStyle.setBorderTop(BorderStyle.THIN);
            borderStyle.setBorderLeft(BorderStyle.THIN);
            borderStyle.setBorderRight(BorderStyle.THIN);

            // =====================================================
            // ENCABEZADOS
            // =====================================================
            String[] columnas = {
                "Cédula",
                "Nombre",
                "Celular",
                "Empresa"
            };

            Row headerRow = sheet.createRow(rowIndex++);

            for (int i = 0; i < columnas.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columnas[i]);
                cell.setCellStyle(headerStyle);
            }

            // =====================================================
            // DATOS
            // =====================================================
            for (Proveedor p : lista) {

                Row row = sheet.createRow(rowIndex++);

                row.createCell(0).setCellValue(p.getCedula());
                row.createCell(1).setCellValue(p.getNombre());
                row.createCell(2).setCellValue(p.getCelular());
                row.createCell(3).setCellValue(p.getEmpresa());

                row.getCell(0).setCellStyle(borderStyle);
                row.getCell(1).setCellStyle(borderStyle);
                row.getCell(2).setCellStyle(borderStyle);
                row.getCell(3).setCellStyle(borderStyle);
            }

            rowIndex++;

            // =====================================================
            // TOTAL PROVEEDORES
            // =====================================================
            Row totalRow = sheet.createRow(rowIndex);

            Cell totalCell = totalRow.createCell(0);
            totalCell.setCellValue("TOTAL DE PROVEEDORES: " + lista.size());

            // =====================================================
            // PIE DE PAGINA
            // =====================================================
            Footer footer = sheet.getFooter();
            footer.setCenter("MiniSuper Zona Azul - Página &P de &N");

            // =====================================================
            // GUARDAR Y ABRIR
            // =====================================================
            String nombreArchivo
                    = "Reporte_Proveedores_" + System.currentTimeMillis() + ".xlsx";

            File file = new File(rutaBase + nombreArchivo);

            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.close();
            workbook.close();

            Desktop.getDesktop().open(file);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

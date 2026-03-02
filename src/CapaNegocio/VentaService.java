/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaNegocio;

import CapaDatos.VentaDAO;
import CapaModelo.Venta;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author adoni
 */
public class VentaService {

    private VentaDAO ventaDAO = new VentaDAO();

    public boolean guardarVenta(String nombreProducto, int cantidad, BigDecimal precioTotal) {
        Venta v = new Venta();
        v.setFecha(LocalDate.now());
        v.setNombreProducto(nombreProducto);
        v.setCantidad(cantidad);
        v.setPrecioVenta(precioTotal);

        return ventaDAO.insertar(v);
    }
}

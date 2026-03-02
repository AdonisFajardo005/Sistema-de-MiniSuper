/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaDatos;

import CapaModelo.Venta;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author adoni
 */
public class VentaDAO {

 public boolean insertar(Venta v) {

    String sql = "INSERT INTO Ventas (Fecha, NombreProducto, Cantidad, PrecioVenta) VALUES (?, ?, ?, ?)";

    try (Connection con = Conexion.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setDate(1, java.sql.Date.valueOf(v.getFecha()));
        ps.setString(2, v.getNombreProducto());
        ps.setInt(3, v.getCantidad());
        ps.setBigDecimal(4, v.getPrecioVenta());

        return ps.executeUpdate() > 0;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}
}

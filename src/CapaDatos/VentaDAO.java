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

        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

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

    public List<Venta> listar() {

        List<Venta> lista = new ArrayList<>();
        String sql = "SELECT IdVenta, Fecha, NombreProducto, Cantidad, PrecioVenta FROM Ventas";

        try (Connection con = Conexion.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Venta v = new Venta();

                v.setIdVenta(rs.getInt("IdVenta"));

                // 🔥 Convertimos de java.sql.Date a LocalDate
                java.sql.Date fechaSql = rs.getDate("Fecha");
                if (fechaSql != null) {
                    v.setFecha(fechaSql.toLocalDate());
                }

                v.setNombreProducto(rs.getString("NombreProducto"));
                v.setCantidad(rs.getInt("Cantidad"));

                // 🔥 Usamos BigDecimal directamente
                v.setPrecioVenta(rs.getBigDecimal("PrecioVenta"));

                lista.add(v);
            }

        } catch (SQLException e) {
            System.out.println("Error listar ventas: " + e.getMessage());
        }

        return lista;
    }

}

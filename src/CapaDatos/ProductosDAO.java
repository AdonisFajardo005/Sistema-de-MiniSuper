/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaDatos;

import CapaModelo.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author adoni
 */
public class ProductosDAO {

    public boolean InsertarProdcutos(Producto p) {
        String sql = "INSERT INTO Productos (Codigo, Nombre, CantidadEnStock, Precio) VALUES (?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getCodigo()); // Codigo es String
            ps.setString(2, p.getNombre()); // Nombre es String
            ps.setInt(3, p.getCantidadEnStock()); // CantidadEnStock es INT
            ps.setBigDecimal(4, p.getPrecio()); // Precio es DECIMAL(10,2), se usa BigDecimal en Java

            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Error insertar producto: " + e.getMessage());
            return false;
        }
    }

    public List<Producto> listarProductos() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM Productos";

        try (Connection con = Conexion.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Producto p = new Producto();
                p.setCodigo(rs.getString("Codigo"));
                p.setNombre(rs.getString("Nombre"));
                p.setCantidadEnStock(rs.getInt("CantidadEnStock"));
                p.setPrecio(rs.getBigDecimal("Precio"));
                lista.add(p);
            }
        } catch (Exception e) {
            System.out.println("Error listar productos: " + e.getMessage());
        }
        return lista;
    }
}

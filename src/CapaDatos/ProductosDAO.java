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

    public boolean actualizarProducto(Producto p) {

        String sql = "UPDATE Productos SET Nombre=?, CantidadEnStock=?, Precio=? WHERE Codigo=?";

        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setInt(2, p.getCantidadEnStock());
            ps.setBigDecimal(3, p.getPrecio());
            ps.setString(4, p.getCodigo());

            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Error actualizar: " + e.getMessage());
            return false;
        }
    }
    
    public boolean eliminarProducto(String Codigo) {

    String sql = "DELETE FROM Productos WHERE Codigo = ?";

    try (Connection con = Conexion.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, Codigo);
        ps.executeUpdate();
        return true;

    } catch (Exception e) {
        System.out.println("Error eliminar: " + e.getMessage());
        return false;
    }
}
    public Producto buscarPorCodigo(String codigo) {
    String sql = "SELECT * FROM Productos WHERE Codigo = ?";
    
    try (Connection con = Conexion.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, codigo);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Producto p = new Producto();
            p.setCodigo(rs.getString("Codigo"));
            p.setNombre(rs.getString("Nombre"));
            p.setCantidadEnStock(rs.getInt("CantidadEnStock"));
            p.setPrecio(rs.getBigDecimal("Precio"));
            return p;
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
}
    public boolean actualizarStock(String codigo, int nuevaCantidad) {
    String sql = "UPDATE Productos SET CantidadEnStock = ? WHERE Codigo = ?";

    try (Connection con = Conexion.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, nuevaCantidad);
        ps.setString(2, codigo);

        return ps.executeUpdate() > 0;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}
    
      public List<Producto> buscarPorNombre(String texto) {

        List<Producto> lista = new ArrayList<>();

        String sql =
        "SELECT TOP 3 Codigo,Nombre FROM Productos WHERE Nombre LIKE ?";

        try {

            Connection con = Conexion.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, texto + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Producto p = new Producto();

                p.setCodigo(rs.getString("Codigo"));
                p.setNombre(rs.getString("Nombre"));

                lista.add(p);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }


    public Producto buscarExacto(String nombre) {

        Producto p = null;

    String sql =
    "SELECT Codigo, Nombre, Precio, CantidadEnStock FROM Productos WHERE Nombre = ?";

    try {

        Connection con = Conexion.getConnection();

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, nombre);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            p = new Producto();

            p.setCodigo(rs.getString("Codigo"));
            p.setNombre(rs.getString("Nombre"));
            p.setPrecio(rs.getBigDecimal("Precio"));
            p.setCantidadEnStock(rs.getInt("CantidadEnStock"));

        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return p;
    
    }
}

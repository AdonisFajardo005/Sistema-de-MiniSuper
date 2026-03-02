/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaDatos;

import CapaModelo.Compra;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 *
 * @author adoni
 */
public class ComprasDAO {

    public boolean insertar(Compra c) {
        
        String sql = "INSERT INTO Compras (Fecha, NombreProducto, Cantidad, PrecioCompra, Proveedor) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setDate(1, new java.sql.Date(c.getFecha().getTime()));
            ps.setString(2, c.getNombreProducto());
            ps.setInt(3, c.getCantidad());
            ps.setDouble(4, c.getPrecioCompra());
            ps.setString(5, c.getProveedor());
            
            ps.executeUpdate();
            return true;
            
        } catch (Exception e) {
            System.out.println("Error insertar compra: " + e.getMessage());
            return false;
        }
    }

    public List<Compra> listar() {
        
        List<Compra> lista = new ArrayList<>();
        String sql = "SELECT * FROM Compras";
        
        try (Connection con = Conexion.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            
            while (rs.next()) {
                
                Compra c = new Compra();
                
                c.setIdCompra(rs.getInt("IdCompra"));

                // 🔥 No necesita conversión extra
                c.setFecha(rs.getDate("Fecha"));
                
                c.setNombreProducto(rs.getString("NombreProducto"));
                c.setCantidad(rs.getInt("Cantidad"));
                c.setPrecioCompra(rs.getDouble("PrecioCompra"));
                c.setProveedor(rs.getString("Proveedor"));
                
                lista.add(c);
            }
            
        } catch (Exception e) {
            System.out.println("Error listar compras: " + e.getMessage());
        }
        
        return lista;
    }

    public boolean actualizar(Compra c) {
        // Solo actualizamos nombre, cantidad, precio y proveedor
        String sql = "UPDATE Compras SET NombreProducto=?, Cantidad=?, PrecioCompra=?, Proveedor=? WHERE IdCompra=?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getNombreProducto());
            ps.setInt(2, c.getCantidad());
            ps.setDouble(3, c.getPrecioCompra());
            ps.setString(4, c.getProveedor());
            ps.setInt(5, c.getIdCompra());

            int filas = ps.executeUpdate();
            if (filas == 0) {
                System.out.println("No se actualizó ninguna fila. Revisa el IdCompra: " + c.getIdCompra());
            }
            return filas > 0;

        } catch (Exception e) {
            System.out.println("Error actualizar compra: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int idCompra) {
        
        String sql = "DELETE FROM Compras WHERE IdCompra = ?";
        
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, idCompra);
            ps.executeUpdate();
            return true;
            
        } catch (Exception e) {
            System.out.println("Error eliminar compra: " + e.getMessage());
            return false;
        }
    }
    
}

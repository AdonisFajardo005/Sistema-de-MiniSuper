/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaDatos;

/**
 *
 * @author adoni
 */
import CapaModelo.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public boolean insertar(Cliente c) {
        String sql = "INSERT INTO Clientes (Cedula, Nombre, Celular, Direccion) VALUES (?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getCedula());
            ps.setString(2, c.getNombre());
            ps.setString(3, c.getCelular());
            ps.setString(4, c.getDireccion());

            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Error insertar: " + e.getMessage());
            return false;
        }
    }
    
    public List<Cliente> listar() {

    List<Cliente> lista = new ArrayList<>();
    String sql = "SELECT * FROM Clientes";

    try (Connection con = Conexion.getConnection();
         Statement st = con.createStatement();
         ResultSet rs = st.executeQuery(sql)) {

        while (rs.next()) {

            Cliente c = new Cliente();
            c.setCedula(rs.getString("Cedula"));
            c.setNombre(rs.getString("Nombre"));
            c.setCelular(rs.getString("Celular"));
            c.setDireccion(rs.getString("Direccion"));

            lista.add(c);
        }

    } catch (Exception e) {
        System.out.println("Error listar: " + e.getMessage());
    }

    return lista;
}
    
    public boolean actualizar(Cliente c) {

    String sql = "UPDATE Clientes SET Nombre=?, Celular=?, Direccion=? WHERE Cedula=?";

    try (Connection con = Conexion.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, c.getNombre());
        ps.setString(2, c.getCelular());
        ps.setString(3, c.getDireccion());
        ps.setString(4, c.getCedula());

        ps.executeUpdate();
        return true;

    } catch (Exception e) {
        System.out.println("Error actualizar: " + e.getMessage());
        return false;
    }
}
    
    public boolean eliminar(String cedula) {

    String sql = "DELETE FROM Clientes WHERE Cedula = ?";

    try (Connection con = Conexion.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, cedula);
        ps.executeUpdate();
        return true;

    } catch (Exception e) {
        System.out.println("Error eliminar: " + e.getMessage());
        return false;
    }
}
}
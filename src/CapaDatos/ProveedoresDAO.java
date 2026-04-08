/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaDatos;

import CapaModelo.Proveedor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author adoni
 */
public class ProveedoresDAO {

    public boolean insertar(Proveedor c) {
        String sql = "INSERT INTO Proveedores (Cedula, Nombre, Celular, Empresa) VALUES (?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getCedula());
            ps.setString(2, c.getNombre());
            ps.setString(3, c.getCelular());
            ps.setString(4, c.getEmpresa());

            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Error insertar: " + e.getMessage());
            return false;
        }
    }
    
     public List<String> listarProveedoresNombres() {

        List<String> lista = new ArrayList<>();
        String sql = "SELECT nombre FROM proveedores";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(rs.getString("nombre"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public List<Proveedor> listarProveedores() {

        List<Proveedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM Proveedores";

        try (Connection con = Conexion.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Proveedor c = new Proveedor();
                c.setCedula(rs.getString("Cedula"));
                c.setNombre(rs.getString("Nombre"));
                c.setCelular(rs.getString("Celular"));
                c.setEmpresa(rs.getString("Empresa"));

                lista.add(c);
            }

        } catch (Exception e) {
            System.out.println("Error listar: " + e.getMessage());
        }

        return lista;
    }

    public boolean actualizarProveedores(Proveedor c) {

        String sql = "UPDATE Proveedores SET Nombre=?, Celular=?, Empresa=? WHERE Cedula=?";

        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getNombre());
            ps.setString(2, c.getCelular());
            ps.setString(3, c.getEmpresa());
            ps.setString(4, c.getCedula());

            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Error actualizar: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarProveedor(String cedula) {

        String sql = "DELETE FROM Proveedores WHERE Cedula = ?";

        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cedula);
            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Error eliminar: " + e.getMessage());
            return false;
        }
    }

    public List<String> listarNombres() {

        List<String> lista = new ArrayList<>();
        String sql = "SELECT Nombre FROM Proveedores";

        try (Connection con = Conexion.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(rs.getString("Nombre"));
            }

        } catch (Exception e) {
            System.out.println("Error listar nombres proveedores: " + e.getMessage());
        }

        return lista;
    }
}

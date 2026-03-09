/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaDatos;

/**
 *
 * @author adoni
 */

import CapaModelo.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    

    // INSERTAR
    public boolean insertar(Usuario u) {
        String sql = "INSERT INTO Usuarios (Nombre, Usuario, Contrasena, Rol) VALUES (?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, u.getNombre());
            ps.setString(2, u.getUsuario());
            ps.setString(3, u.getContrasena());
            ps.setString(4, u.getRol());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error insertar usuario: " + e.getMessage());
            return false;
        }
    }

    // LISTAR
    public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM Usuarios";

        try (Connection con = Conexion.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setNombre(rs.getString("Nombre"));
                u.setUsuario(rs.getString("Usuario"));
                u.setContrasena(rs.getString("Contrasena"));
                u.setRol(rs.getString("Rol"));

                lista.add(u);
            }

        } catch (SQLException e) {
            System.out.println("Error listar usuarios: " + e.getMessage());
        }

        return lista;
    }

    // ACTUALIZAR (usando Usuario como PK)
    public boolean actualizar(Usuario u) {
        String sql = "UPDATE Usuarios SET Nombre=?, Contrasena=?, Rol=? WHERE Usuario=?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, u.getNombre());
            ps.setString(2, u.getContrasena());
            ps.setString(3, u.getRol());
            ps.setString(4, u.getUsuario());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error actualizar usuario: " + e.getMessage());
            return false;
        }
    }

    // ELIMINAR
    public boolean eliminar(String usuario) {
        String sql = "DELETE FROM Usuarios WHERE Usuario=?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario);
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error eliminar usuario: " + e.getMessage());
            return false;
        }
    }
    
    public Usuario validarLogin(String usuario, String password) {

    String sql = "SELECT * FROM Usuarios WHERE Usuario = ? AND Contrasena = ?";

    try (Connection con = Conexion.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, usuario);
        ps.setString(2, password);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Usuario u = new Usuario();
            u.setNombre(rs.getString("Nombre"));
            u.setUsuario(rs.getString("Usuario"));
            u.setContrasena(rs.getString("Contrasena"));
            u.setRol(rs.getString("Rol"));

            return u; // 🔥 devuelve usuario con rol
        }

    } catch (SQLException e) {
        System.out.println("Error login: " + e.getMessage());
    }

    return null;
}
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaDatos;


import java.sql.Connection;
import java.sql.Statement;

/**
 *
 * @author adoni
 */
public class NewClass {
    public static void main(String[] args) {

          try {
            Connection con = Conexion.getConnection();

            Statement st = con.createStatement();

            String sql = "INSERT INTO Clientes (Cedula, Nombre, Celular, Direccion) " +
                         "VALUES ('123456789', 'Juan Perez', '88887777', 'Nicoya Centro')";

            int filas = st.executeUpdate(sql);

            if (filas > 0) {
                System.out.println("Cliente insertado correctamente");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
 

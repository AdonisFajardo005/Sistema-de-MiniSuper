/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaModelo;

/**
 *
 * @author adoni
 */
public class Cliente {
 

    // Atributos
    private String cedula;
    private String nombre;
    private String celular;
    private String direccion;

    // Constructor vacío
    public Cliente() {
    }

    // Constructor con parámetros
    public Cliente(String cedula, String nombre, String celular, String direccion) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.celular = celular;
        this.direccion = direccion;
    }

    // Getters
    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCelular() {
        return celular;
    }

    public String getDireccion() {
        return direccion;
    }

    // Setters
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}


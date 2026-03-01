/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaModelo;

/**
 *
 * @author adoni
 */
public class Proveedor {

    // Atributos
    private String cedula;
    private String nombre;
    private String celular;
    private String empresa;

    // Constructor vacío
    public Proveedor() {
    }

    // Constructor con todos los atributos
    public Proveedor(String cedula, String nombre, String celular, String empresa) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.celular = celular;
        this.empresa = empresa;
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

    public String getEmpresa() {
        return empresa;
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

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
}
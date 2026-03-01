/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaModelo;

/**
 *
 * @author adoni
 */
public class Productos {

    // Atributos
    private String codigo;
    private String nombre;
    private int cantidadEnStock;
    private double precio;

    // Constructor vacío
    public Productos() {
    }

    // Constructor con todos los atributos
    public Productos(String codigo, String nombre, int cantidadEnStock, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidadEnStock = cantidadEnStock;
        this.precio = precio;
    }

    // Getters
    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidadEnStock() {
        return cantidadEnStock;
    }

    public double getPrecio() {
        return precio;
    }

    // Setters
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCantidadEnStock(int cantidadEnStock) {
        this.cantidadEnStock = cantidadEnStock;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}

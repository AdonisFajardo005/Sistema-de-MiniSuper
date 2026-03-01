/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaModelo;

import java.math.BigDecimal;

/**
 *
 * @author adoni
 */
public class Producto {

    // Atributos
    private String codigo;
    private String nombre;
    private int cantidadEnStock;
    private BigDecimal precio;

    // Constructor vacío
    public Producto() {
    }

    // Constructor con todos los atributos

    public Producto(String codigo, String nombre, int cantidadEnStock, BigDecimal precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidadEnStock = cantidadEnStock;
        this.precio = precio;
    }
    

    // Getters

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidadEnStock() {
        return cantidadEnStock;
    }

    public void setCantidadEnStock(int cantidadEnStock) {
        this.cantidadEnStock = cantidadEnStock;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
    
}

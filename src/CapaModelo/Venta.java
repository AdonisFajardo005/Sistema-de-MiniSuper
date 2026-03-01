/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaModelo;

/**
 *
 * @author adoni
 */
import java.time.LocalDate;

public class Venta {

    private int idVenta;
    private LocalDate fecha;
    private String nombreProducto;
    private int cantidad;
    private double precioVenta;

    // Constructor vacío
    public Venta() {
    }

    // Constructor para insertar (sin ID)
    public Venta(LocalDate fecha, String nombreProducto, int cantidad, double precioVenta) {
        this.fecha = fecha;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.precioVenta = precioVenta;
    }

    // Constructor para mostrar ventas (con ID)
    public Venta(int idVenta, LocalDate fecha, String nombreProducto, int cantidad, double precioVenta) {
        this.idVenta = idVenta;
        this.fecha = fecha;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.precioVenta = precioVenta;
    }

    // Getters y Setters
    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }
}

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
import java.util.Date;

public class Compra {

    // Atributos
    private int idCompra;
    private Date fecha;
    private String nombreProducto;
    private int cantidad;
    private double precioCompra;
    private String proveedor;

    // Constructor vacío
    public Compra() {
    }

    // Constructor sin ID (cuando vas a insertar)
    public Compra(Date fecha, String nombreProducto, int cantidad, double precioCompra, String proveedor) {
        this.fecha = fecha;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.precioCompra = precioCompra;
        this.proveedor = proveedor;
    }

    // Constructor con ID (cuando traes datos de la BD)
    public Compra(int idCompra, Date fecha, String nombreProducto, int cantidad, double precioCompra, String proveedor) {
        this.idCompra = idCompra;
        this.fecha = fecha;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.precioCompra = precioCompra;
        this.proveedor = proveedor;
    }

    // Getters
    public int getIdCompra() {
        return idCompra;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public String getProveedor() {
        return proveedor;
    }

    // Setters
    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }
}
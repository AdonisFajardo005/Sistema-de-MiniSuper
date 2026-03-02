/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaNegocio;

import CapaDatos.ProductosDAO;
import CapaModelo.Producto;
import java.util.List;

/**
 *
 * @author adoni
 */
public class ProductoService {

    private ProductosDAO dao = new ProductosDAO();

    public boolean guardarProductos(Producto p) {
        return dao.InsertarProdcutos(p);
    }

    public List<Producto> listarProducto() {
        return dao.listarProductos();
    }

    public boolean actualizarProducto(Producto p) {
        return dao.actualizarProducto(p);
    }
    
     public boolean eliminarProducto(String Codigo) {
    return dao.eliminarProducto(Codigo);
}
     public Producto buscarProducto(String codigo) {
    return dao.buscarPorCodigo(codigo);
}

public boolean descontarStock(String codigo, int cantidadVendida) {
    Producto p = dao.buscarPorCodigo(codigo);

    if (p == null) return false;

    int nuevoStock = p.getCantidadEnStock() - cantidadVendida;

    if (nuevoStock < 0) return false;

    return dao.actualizarStock(codigo, nuevoStock);
}
}

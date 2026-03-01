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
}

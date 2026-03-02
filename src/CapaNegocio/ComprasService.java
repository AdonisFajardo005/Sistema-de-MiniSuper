/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaNegocio;

import CapaDatos.ComprasDAO;
import CapaModelo.Compra;
import java.util.List;

/**
 *
 * @author adoni
 */
public class ComprasService {

    private ComprasDAO dao = new ComprasDAO();

    public boolean insertar(Compra c) {

        return dao.insertar(c);

    }
    public List<Compra> listarCompra() {
        return dao.listar();
    }

    public boolean actualizarCompra(Compra p) {
        return dao.actualizar(p);
    }
    
     public boolean eliminarCompra(int idCompra) {
    return dao.eliminar(idCompra);
}
}

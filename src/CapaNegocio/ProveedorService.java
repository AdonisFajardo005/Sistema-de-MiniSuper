/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaNegocio;

import CapaDatos.ProveedoresDAO;
import CapaModelo.Proveedor;
import java.util.List;

/**
 *
 * @author adoni
 */
public class ProveedorService {

    private ProveedoresDAO dao = new ProveedoresDAO();

    public boolean guardarProveedor(Proveedor c) {
        return dao.insertar(c);
    }

    public List<Proveedor> listarProveedores() {
        return dao.listarProveedores();
    }

    public boolean actualizarProveedor(Proveedor c) {
        return dao.actualizarProveedores(c);
    }

    public boolean eliminarProveedor(String cedula) {
        return dao.eliminarProveedor(cedula);
    }

    public List<String> listarNombres() {

        return dao.listarNombres();

    }

}

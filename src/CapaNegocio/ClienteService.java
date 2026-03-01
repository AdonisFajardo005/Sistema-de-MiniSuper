/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaNegocio;

import CapaDatos.ClienteDAO;
import CapaModelo.Cliente;
import java.util.List;

/**
 *
 * @author adoni
 */
public class ClienteService {

    private ClienteDAO dao = new ClienteDAO();

    public boolean guardarCliente(Cliente c) {
        return dao.insertar(c);
    }
    
    public List<Cliente> listarClientes() {
    return dao.listar();
}
    public boolean actualizarCliente(Cliente c) {
    return dao.actualizar(c);
}
    public boolean eliminarCliente(String cedula) {
    return dao.eliminar(cedula);
}
}

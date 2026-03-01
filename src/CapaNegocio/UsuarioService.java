/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaNegocio;

/**
 *
 * @author adoni
 */

import CapaDatos.UsuarioDAO;
import CapaModelo.Usuario;
import java.util.List;

public class UsuarioService {

    private UsuarioDAO dao = new UsuarioDAO();

    public boolean guardar(Usuario u) {
        return dao.insertar(u);
    }

    public List<Usuario> listar() {
        return dao.listar();
    }

    public boolean actualizar(Usuario u) {
        return dao.actualizar(u);
    }

    public boolean eliminar(String usuario) {
        return dao.eliminar(usuario);
    }
    public Usuario login(String usuario, String password) {
    return dao.validarLogin(usuario, password);
}
}
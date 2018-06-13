package controlador;

import modelo.Usuario;
import modelo.UsuarioDAO;
import vista.Registro;

public class ControladorConfiguracion {

    private Registro vista;
    private UsuarioDAO modelo;
    private Usuario usuario;

    public ControladorConfiguracion(Registro vista, UsuarioDAO modelo) {

        this.vista = vista;
        this.modelo = modelo;

    }

}

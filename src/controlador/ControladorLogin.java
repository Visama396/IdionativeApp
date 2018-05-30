package controlador;

import jdk.nashorn.internal.scripts.JO;
import modelo.PalabraDAO;
import modelo.Usuario;
import modelo.UsuarioDAO;
import vista.Diccionario;
import vista.InicioSesion;
import vista.Registro;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorLogin implements ActionListener {

    UsuarioDAO modelo;
    InicioSesion vista;
    String lang;

    public ControladorLogin(InicioSesion vista, UsuarioDAO modelo, String lang, String email) {
        this.vista = vista;
        this.modelo = modelo;
        this.lang = lang;

        this.vista.emailLabel.setText("Email");
        this.vista.emailField.setText(email);
        this.vista.passwordLabel.setText("Contraseña");
        this.vista.passwordField.setText("");
        this.vista.signinButton.setText("Iniciar sesión");
        this.vista.signinButton.addActionListener(this);
        this.vista.signinButton.setActionCommand("SIGNIN");
        this.vista.noAccountButton.setText("No tengo cuenta");
        this.vista.noAccountButton.addActionListener(this);
        this.vista.noAccountButton.setActionCommand("CREATEACCOUNT");
        this.vista.closeButton.setText("Cerrar");
        this.vista.closeButton.addActionListener(this);
        this.vista.closeButton.setActionCommand("CLOSE");

        this.vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.vista.pack();
        this.vista.setLocationRelativeTo(null);
        this.vista.setResizable(false);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton boton = (JButton) e.getSource();

            switch (boton.getActionCommand()) {
                case "SIGNIN":
                    boolean correcto = this.modelo.comprobarUsuario(this.vista.emailField.getText(), new String(this.vista.passwordField.getPassword()));
                    if (correcto) {
                        Diccionario view = new Diccionario("Diccionario");
                        PalabraDAO model = new PalabraDAO();
                        ControladorDiccionario controller = new ControladorDiccionario(view, model);
                        this.vista.setVisible(false);
                        view.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario incorrecto o no existente.\nCompruebe email y contraseña, o cree un usuario nuevo.", "Error al iniciar sesión", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case "CREATEACCOUNT":
                    Registro view = new Registro("Registro");
                    UsuarioDAO model = new UsuarioDAO();
                    ControladorRegistro controller = new ControladorRegistro(view, model, this.lang, this.vista.emailField.getText());
                    this.vista.setVisible(false);
                    view.setVisible(true);
                    break;
                case "CLOSE":
                    int opcion = JOptionPane.showConfirmDialog(null, "¿Quieres cerrar?", "Confirmar", JOptionPane.YES_NO_OPTION);
                    if (opcion == 0) {
                        System.exit(0);
                    }
                    break;
            }
            
        }
    }
}

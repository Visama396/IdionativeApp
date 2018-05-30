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
import java.util.ResourceBundle;
import java.util.Locale;

public class ControladorLogin implements ActionListener {

    UsuarioDAO modelo;
    InicioSesion vista;
    String lang;
    Locale loc;
    ResourceBundle rb;

    public ControladorLogin(InicioSesion vista, UsuarioDAO modelo, String lang, String email) {
        this.vista = vista;
        this.modelo = modelo;
        this.lang = lang;

        this.loc = new Locale(this.lang);
        this.rb = ResourceBundle.getBundle("locales.iniciosesion.locale", this.loc);

        this.vista.mainMenu.setText(rb.getString("options"));
        this.vista.languagesMenu.setText(rb.getString("langs"));
        this.vista.spanishItem.setText(rb.getString("spanish"));
        this.vista.spanishItem.addActionListener(this);
        this.vista.spanishItem.setActionCommand("ESP");
        this.vista.englishItem.setText(rb.getString("english"));
        this.vista.englishItem.addActionListener(this);
        this.vista.englishItem.setActionCommand("ENG");
        this.vista.japaneseItem.setText(rb.getString("japanese"));
        this.vista.japaneseItem.addActionListener(this);
        this.vista.japaneseItem.setActionCommand("JPN");
        this.vista.germanItem.setText(rb.getString("german"));
        this.vista.germanItem.addActionListener(this);
        this.vista.germanItem.setActionCommand("DEU");
        this.vista.closeItem.setText(rb.getString("close"));
        this.vista.closeItem.addActionListener(this);
        this.vista.closeItem.setActionCommand("CLOSE");

        this.vista.emailLabel.setText(rb.getString("email"));
        this.vista.emailField.setText(email);
        this.vista.passwordLabel.setText(rb.getString("password"));
        this.vista.passwordField.setText("");
        this.vista.signinButton.setText(rb.getString("login"));
        this.vista.signinButton.addActionListener(this);
        this.vista.signinButton.setActionCommand("SIGNIN");
        this.vista.noAccountButton.setText(rb.getString("noaccount"));
        this.vista.noAccountButton.addActionListener(this);
        this.vista.noAccountButton.setActionCommand("CREATEACCOUNT");

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
                        this.vista.dispose();
                        view.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario incorrecto o no existente.\nCompruebe email y contraseña, o cree un usuario nuevo.", "Error al iniciar sesión", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case "CREATEACCOUNT":
                    Registro view = new Registro("Registro");
                    UsuarioDAO model = new UsuarioDAO();
                    ControladorRegistro controller = new ControladorRegistro(view, model, this.lang, this.vista.emailField.getText());
                    this.vista.dispose();
                    view.setVisible(true);
                    break;
            }
            
        } else if (e.getSource() instanceof JMenuItem) {
            JMenuItem item = (JMenuItem) e.getSource();

            switch (item.getActionCommand()) {
                case "ESP":
                    break;
                case "ENG":
                    this.loc = new Locale("en");
                    InicioSesion view = new InicioSesion("Log in");
                    UsuarioDAO model = new UsuarioDAO();
                    ControladorLogin controller = new ControladorLogin(view, model, "en", this.vista.emailField.getText());
                    this.vista.dispose();
                    view.setVisible(true);
                    break;
                case "JPN":
                    break;
                case "DEU":
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

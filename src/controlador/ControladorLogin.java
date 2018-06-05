package controlador;

import jdk.nashorn.internal.scripts.JO;
import modelo.PalabraDAO;
import modelo.Usuario;
import modelo.UsuarioDAO;
import vista.Diccionario;
import vista.InicioSesion;
import vista.Registro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ResourceBundle;
import java.util.Locale;

public class ControladorLogin implements ActionListener, WindowListener {

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

        Font fuente = new Font("Arial Unicode MS", Font.PLAIN, 14);

        this.vista.mainMenu.setText(rb.getString("options"));
        this.vista.mainMenu.setFont(fuente);
        this.vista.languagesMenu.setText(rb.getString("langs"));
        this.vista.languagesMenu.setFont(fuente);
        this.vista.spanishItem.setText(rb.getString("spanish"));
        this.vista.spanishItem.setFont(fuente);
        this.vista.spanishItem.addActionListener(this);
        this.vista.spanishItem.setActionCommand("ESP");
        this.vista.englishItem.setText(rb.getString("english"));
        this.vista.englishItem.setFont(fuente);
        this.vista.englishItem.addActionListener(this);
        this.vista.englishItem.setActionCommand("ENG");
        this.vista.japaneseItem.setText(rb.getString("japanese"));
        this.vista.japaneseItem.setFont(fuente);
        this.vista.japaneseItem.addActionListener(this);
        this.vista.japaneseItem.setActionCommand("JPN");
        this.vista.germanItem.setText(rb.getString("german"));
        this.vista.germanItem.setFont(fuente);
        this.vista.germanItem.addActionListener(this);
        this.vista.germanItem.setActionCommand("DEU");
        this.vista.germanItem.setFont(fuente);
        this.vista.closeItem.setText(rb.getString("close"));
        this.vista.closeItem.addActionListener(this);
        this.vista.closeItem.setActionCommand("CLOSE");

        this.vista.emailLabel.setText(rb.getString("email"));
        this.vista.emailLabel.setFont(fuente);
        this.vista.emailField.setText(email);
        this.vista.passwordLabel.setText(rb.getString("password"));
        this.vista.passwordLabel.setFont(fuente);
        this.vista.passwordField.setText("");
        this.vista.signinButton.setText(rb.getString("login"));
        this.vista.signinButton.setFont(fuente);
        this.vista.signinButton.addActionListener(this);
        this.vista.signinButton.setActionCommand("SIGNIN");
        this.vista.noAccountButton.setText(rb.getString("noaccount"));
        this.vista.noAccountButton.setFont(fuente);
        this.vista.noAccountButton.addActionListener(this);
        this.vista.noAccountButton.setActionCommand("CREATEACCOUNT");

        this.vista.addWindowListener(this);
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
                        Diccionario view = new Diccionario(rb.getString("dict"));
                        PalabraDAO model = new PalabraDAO();
                        ControladorDiccionario controller = new ControladorDiccionario(view, model);
                        this.vista.dispose();
                        view.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, rb.getString("badlogin1") + "\n" + rb.getString("badlogin2"), "Error al iniciar sesión", JOptionPane.ERROR_MESSAGE);
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
                    InicioSesion viewes = new InicioSesion("Iniciar sesión");
                    UsuarioDAO modeles = new UsuarioDAO();
                    ControladorLogin controlleres = new ControladorLogin(viewes, modeles,"es", this.vista.emailField.getText());
                    this.vista.dispose();
                    viewes.setVisible(true);
                    break;
                case "ENG":
                    InicioSesion viewen = new InicioSesion("Log in");
                    UsuarioDAO modelen = new UsuarioDAO();
                    ControladorLogin controlleren = new ControladorLogin(viewen, modelen, "en", this.vista.emailField.getText());
                    this.vista.dispose();
                    viewen.setVisible(true);
                    break;
                case "JPN":
                    InicioSesion viewjp = new InicioSesion("ログイン");
                    UsuarioDAO modeljp = new UsuarioDAO();
                    ControladorLogin controllerjp = new ControladorLogin(viewjp, modeljp, "ja", this.vista.emailField.getText());
                    this.vista.dispose();
                    viewjp.setVisible(true);
                    break;
                case "DEU":
                    InicioSesion viewde = new InicioSesion("Anmelden");
                    UsuarioDAO modelde = new UsuarioDAO();
                    ControladorLogin controllerde = new ControladorLogin(viewde, modelde, "de", this.vista.emailField.getText());
                    this.vista.dispose();
                    viewde.setVisible(true);
                    break;
                case "CLOSE":
                    int opcion = JOptionPane.showConfirmDialog(null, rb.getString("closequest2"), rb.getString("closequest"), JOptionPane.YES_NO_OPTION);
                    if (opcion == 0) {
                        System.exit(0);
                    }
                    break;
            }
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        int option = JOptionPane.showConfirmDialog(null, "¿Quieres cerrar la aplicación?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (option == 0) {
            System.exit(0);
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}

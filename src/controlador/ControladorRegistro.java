package controlador;

import modelo.Idioma;
import modelo.IdiomaDAO;
import modelo.UsuarioDAO;
import vista.InicioSesion;
import vista.Registro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ControladorRegistro implements ActionListener {

    private Registro vista;
    private UsuarioDAO modelo;
    String lang;

    public ControladorRegistro(Registro vista, UsuarioDAO modelo, String lang, String email) {
        this.vista = vista;
        this.modelo = modelo;
        this.lang = lang;

        // Fuente en Linux "Noto Serif CJK JP"
        Font fuente = new Font("Arial Unicode MS", Font.PLAIN, 12);

        this.vista.emailLabel.setText("Email");
        this.vista.emailLabel.setFont(fuente);
        this.vista.emailField.setText(email);
        this.vista.emailField.setFont(fuente);
        this.vista.userLabel.setText("Nombre de usuario");
        this.vista.userLabel.setFont(fuente);
        this.vista.userField.setText("");
        this.vista.userField.setFont(fuente);
        this.vista.passwordLabel.setText("Contraseña");
        this.vista.passwordLabel.setFont(fuente);
        this.vista.passwordField.setText("");
        this.vista.passwordField.setFont(fuente);
        this.vista.nativeLangLabel.setText("Lengua materna");
        this.vista.nativeLangLabel.setFont(fuente);
        this.vista.genderLabel.setText("Sexo");
        this.vista.genderLabel.setFont(fuente);
        this.vista.spokenLangLabel.setText("Idioma(s) que hablas:");
        this.vista.spokenLangLabel.setFont(fuente);
        this.vista.learnLangLabel.setText("Idioma(s) que quieres aprender:");
        this.vista.learnLangLabel.setFont(fuente);
        this.vista.signupButton.setText("Registrarse");
        this.vista.signupButton.setFont(fuente);
        this.vista.signupButton.addActionListener(this);
        this.vista.signupButton.setActionCommand("SIGNUP");
        this.vista.returnButton.setText("Volver");
        this.vista.returnButton.setFont(fuente);
        this.vista.returnButton.addActionListener(this);
        this.vista.returnButton.setActionCommand("RETURN");
        this.vista.closeButton.setText("Cerrar");
        this.vista.closeButton.setFont(fuente);
        this.vista.closeButton.addActionListener(this);
        this.vista.closeButton.setActionCommand("CLOSE");
        this.vista.nativeLangBox.setFont(fuente);
        this.vista.spokenLangList.setFont(fuente);
        this.vista.spokenLangList.setLayoutOrientation(JList.VERTICAL_WRAP);
        this.vista.spokenLangList.setVisibleRowCount(3);
        this.vista.learnLangList.setFont(fuente);
        this.vista.learnLangList.setLayoutOrientation(JList.VERTICAL_WRAP);
        this.vista.learnLangList.setVisibleRowCount(3);
        completarOpciones();
        this.vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.vista.pack();
        this.vista.setLocationRelativeTo(null);
        this.vista.setResizable(false);

    }

    private void completarOpciones() {

        ArrayList<Idioma> idiomas = new IdiomaDAO().obtenerIdiomas();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
        for (Idioma idioma: idiomas) {
            listModel.addElement(idioma.getIdioma());
            comboBoxModel.addElement(idioma.getIdioma());
        }

        DefaultComboBoxModel<String> comboBoxModelGender = new DefaultComboBoxModel<>();
        comboBoxModelGender.addElement("Hombre");
        comboBoxModelGender.addElement("Mujer");
        comboBoxModelGender.addElement("No especificar");

        this.vista.genderBox.setModel(comboBoxModelGender);
        this.vista.nativeLangBox.setModel(comboBoxModel);
        this.vista.spokenLangList.setModel(listModel);
        this.vista.learnLangList.setModel(listModel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton boton = (JButton) e.getSource();

            switch (boton.getActionCommand()) {
                case "SIGNUP":
                    String email = this.vista.emailField.getText();
                    boolean emailbool = false;
                    String user = this.vista.userField.getText();
                    boolean userbool = false;
                    String pass = new String(this.vista.passwordField.getPassword());
                    boolean passbool = false;
                    String nativeLang = (String) this.vista.nativeLangBox.getSelectedItem();
                    boolean nativebool = false;
                    int gend = this.vista.genderBox.getSelectedIndex();
                    boolean genderbool = false;
                    List<String> spokenLangs = this.vista.spokenLangList.getSelectedValuesList();
                    List<String> learnLangs = this.vista.learnLangList.getSelectedValuesList();

                    if (email.length() > 5 && email.length() <=35) {
                        emailbool = true;
                    }

                    if (user.length() > 5 && user.length() <=25) {
                        userbool = true;
                    }

                    if (pass.length() > 5 && pass.length() <=25) {
                        passbool = true;
                    }

                    this.modelo.registrarUsuario(email, user, pass, null, nativeLang, spokenLangs, learnLangs);
                    break;
                case "RETURN":
                    InicioSesion view = new InicioSesion("Inicio sesión");
                    UsuarioDAO model = new UsuarioDAO();
                    ControladorLogin controller = new ControladorLogin(view, model, this.lang, this.vista.emailField.getText());
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

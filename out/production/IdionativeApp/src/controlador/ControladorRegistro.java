package controlador;

import modelo.Idioma;
import modelo.IdiomaDAO;
import modelo.PalabraDAO;
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
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class ControladorRegistro implements ActionListener, WindowListener {

    private Registro vista;
    private UsuarioDAO modelo;
    private String lang;
    private String email;
    private Locale loc;
    private ResourceBundle rb;

    public ControladorRegistro(Registro vista, UsuarioDAO modelo, String lang, String email) {
        this.vista = vista;
        this.modelo = modelo;
        this.lang = lang;
        this.email = email;

        this.loc = new Locale(this.lang);
        this.rb = ResourceBundle.getBundle("locales.registro.locale", this.loc);

        // Fuente en Linux "Noto Serif CJK JP"
        Font fuente = new Font("Arial Unicode MS", Font.PLAIN, 15);

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
        this.vista.closeItem.setFont(fuente);
        this.vista.closeItem.addActionListener(this);
        this.vista.closeItem.setActionCommand("CLOSE");

        this.vista.emailLabel.setText(rb.getString("email"));
        this.vista.emailLabel.setFont(fuente);
        this.vista.emailField.setText(email);
        this.vista.emailField.setFont(fuente);
        this.vista.emailField.setToolTipText("<html>- "+rb.getString("emailtooltip1")+"<br>- "+rb.getString("emailtooltip2")+"<br>- "+rb.getString("emailtooltip3")+"</html>");
        this.vista.userLabel.setText(rb.getString("user"));
        this.vista.userLabel.setFont(fuente);
        this.vista.userField.setText("");
        this.vista.userField.setFont(fuente);
        this.vista.userField.setToolTipText("<html>- Máximo 25 carácteres.<br>- Mínimo 5 carácteres</html>");
        this.vista.passwordLabel.setText(rb.getString("passwd"));
        this.vista.passwordLabel.setFont(fuente);
        this.vista.passwordField.setText("");
        this.vista.passwordField.setFont(fuente);
        this.vista.passwordField.setToolTipText("<html>- Máximo 25 carácteres.<br>- Mínimo 5 carácteres</html>");
        this.vista.nativeLangLabel.setText(rb.getString("mothertongue"));
        this.vista.nativeLangLabel.setFont(fuente);
        this.vista.genderLabel.setText(rb.getString("sex"));
        this.vista.genderLabel.setFont(fuente);
        this.vista.spokenLangLabel.setText(rb.getString("speaklangs"));
        this.vista.spokenLangLabel.setFont(fuente);
        this.vista.learnLangLabel.setText(rb.getString("learnlangs"));
        this.vista.learnLangLabel.setFont(fuente);
        this.vista.signupButton.setText(rb.getString("signup"));
        this.vista.signupButton.setFont(fuente);
        this.vista.signupButton.addActionListener(this);
        this.vista.signupButton.setActionCommand("SIGNUP");
        this.vista.returnButton.setText(rb.getString("return"));
        this.vista.returnButton.setFont(fuente);
        this.vista.returnButton.addActionListener(this);
        this.vista.returnButton.setActionCommand("RETURN");
        this.vista.nativeLangBox.setFont(fuente);
        this.vista.genderBox.setFont(fuente);
        this.vista.spokenLangList.setFont(fuente);
        this.vista.spokenLangList.setLayoutOrientation(JList.VERTICAL_WRAP);
        this.vista.spokenLangList.setVisibleRowCount(3);
        this.vista.learnLangList.setFont(fuente);
        this.vista.learnLangList.setLayoutOrientation(JList.VERTICAL_WRAP);
        this.vista.learnLangList.setVisibleRowCount(3);
        completarOpciones();

        this.vista.addWindowListener(this);
        this.vista.pack();
        this.vista.setLocationRelativeTo(null);
        this.vista.setResizable(false);

    }

    private void completarOpciones() {

        ArrayList<String> idiomas = new IdiomaDAO().obtenerIdiomas(lang);
        DefaultListModel<String> listModel = new DefaultListModel<>();
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();

        for (String idioma:idiomas) {
            listModel.addElement(idioma);
            comboBoxModel.addElement(idioma);
        }

        DefaultComboBoxModel<String> comboBoxModelGender = new DefaultComboBoxModel<>();
        comboBoxModelGender.addElement(rb.getString("man"));
        comboBoxModelGender.addElement(rb.getString("woman"));
        comboBoxModelGender.addElement(rb.getString("none"));

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
                    if (modelo.comprobarUsuario(this.vista.emailField.getText(), null)) {
                        JOptionPane.showMessageDialog(null, "Esta cuenta ya existe", "Correo encontrado", JOptionPane.ERROR_MESSAGE);
                    } else {
                        int resultado = 0;
                        String user = this.vista.userField.getText();
                        boolean userbool = false;
                        String email = this.vista.emailField.getText();
                        boolean emailbool = false;
                        String pass = new String(this.vista.passwordField.getPassword());
                        boolean passbool = false;
                        int gend = this.vista.genderBox.getSelectedIndex();
                        String gender;
                        int nativeLang = this.vista.nativeLangBox.getSelectedIndex();
                        int[] spokenLangs = this.vista.spokenLangList.getSelectedIndices();
                        int[] learnLangs = this.vista.learnLangList.getSelectedIndices();

                        if (email.length() >= 5 && email.length() <=35) {
                            emailbool = true;
                        }

                        if (user.length() >= 5 && user.length() <=25) {
                            userbool = true;
                        }

                        if (pass.length() >= 5 && pass.length() <=25) {
                            passbool = true;
                        }

                        if (gend == 0) {
                            gender = "H";
                        } else if (gend == 1) {
                            gender = "M";
                        } else {
                            gender = "N";
                        }

                        if (emailbool && userbool && passbool) {
                            resultado = this.modelo.registrarUsuario(email, user, pass, gender, nativeLang, spokenLangs, learnLangs);
                            JOptionPane.showMessageDialog(null, "Usuario creado", "Correcto", JOptionPane.INFORMATION_MESSAGE);
                            Diccionario view = new Diccionario("Diccionario");
                            PalabraDAO model = new PalabraDAO();
                            ControladorDiccionario controller = new ControladorDiccionario(view, model, email, this.lang);
                            this.vista.dispose();
                            view.setVisible(true);
                        } else {
                            String error="<html>";
                            if(!userbool) {
                                error+="- Usuario no válido<br>";
                            }
                            if(!emailbool) {
                                error+="- Email no válido<br>";
                            }
                            if(!passbool) {
                                error+="- Contraseña no válida<br>";
                            }
                            error+="</html>";
                            JOptionPane.showMessageDialog(null, error, "Datos no válidos", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    break;
                case "RETURN":
                    InicioSesion view = new InicioSesion(rb.getString("login"));
                    UsuarioDAO model = new UsuarioDAO();
                    ControladorLogin controller = new ControladorLogin(view, model, this.lang, this.vista.emailField.getText());
                    this.vista.dispose();
                    view.setVisible(true);
                    break;
            }
            
        } else if (e.getSource() instanceof JMenuItem) {
            JMenuItem item = (JMenuItem) e.getSource();

            switch (item.getActionCommand()) {
                case "ESP":
                    Registro viewes = new Registro("Registro");
                    UsuarioDAO modeles = new UsuarioDAO();
                    ControladorRegistro controlleres = new ControladorRegistro(viewes, modeles,"es", this.vista.emailField.getText());
                    this.vista.dispose();
                    viewes.setVisible(true);
                    break;
                case "ENG":
                    Registro viewen = new Registro("Sign up");
                    UsuarioDAO modelen = new UsuarioDAO();
                    ControladorRegistro controlleren = new ControladorRegistro(viewen, modelen, "en", this.vista.emailField.getText());
                    this.vista.dispose();
                    viewen.setVisible(true);
                    break;
                case "JPN":
                    Registro viewjp = new Registro("サインアップ");
                    UsuarioDAO modeljp = new UsuarioDAO();
                    ControladorRegistro controllerjp = new ControladorRegistro(viewjp, modeljp, "ja", this.vista.emailField.getText());
                    this.vista.dispose();
                    viewjp.setVisible(true);
                    break;
                case "DEU":
                    Registro viewde = new Registro("Registrieren");
                    UsuarioDAO modelde = new UsuarioDAO();
                    ControladorRegistro controllerde = new ControladorRegistro(viewde, modelde, "de", this.vista.emailField.getText());
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
        int option = JOptionPane.showConfirmDialog(null, rb.getString("closequest2"), rb.getString("closequest"), JOptionPane.YES_NO_OPTION);
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

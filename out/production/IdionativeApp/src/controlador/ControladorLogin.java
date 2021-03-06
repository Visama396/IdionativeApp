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
import java.io.*;
import java.util.ResourceBundle;
import java.util.Locale;

public class ControladorLogin implements ActionListener, WindowListener {

    private UsuarioDAO modelo;
    private InicioSesion vista;
    private String lang;
    private Locale loc;
    private ResourceBundle rb;
    private String email;

    private File file = new File("user.txt");
    private FileWriter save;
    private FileReader read;

    public ControladorLogin(InicioSesion vista, UsuarioDAO modelo, String lang, String email) {
        this.vista = vista;
        this.modelo = modelo;
        this.lang = lang;
        this.email = email;

        /**
         * ResourceBundle para coger el locale adecuado al idioma que se haya pasado por parámetro.
         */
        this.loc = new Locale(this.lang);
        this.rb = ResourceBundle.getBundle("locales.iniciosesion.locale", this.loc);

        String checkremember = null;
        String mail = null;
        String pass = null;

        /**
         * Comprueba que esté creado el archivo de user.txt con los datos del usuario.
         * La primera línea será T o F dependiendo de como quedará el checkbox la última vez que se inició sesión.
         */
        if (file.exists()) {
            try {
                read = new FileReader(file);
                BufferedReader br = new BufferedReader(read);
                String s;
                int casilla = 0;
                while ((s = br.readLine()) != null) {
                    if (casilla == 0) {
                        checkremember = s;
                    } else if(casilla == 1) {
                        mail = s;
                    } else {
                        pass = s;
                    }
                    casilla++;
                }
                if (checkremember.equals("T")) {
                    this.vista.rememberMeCheck.setSelected(true);
                    this.vista.emailField.setText(mail);
                    this.vista.passwordField.setText(pass);
                }
                read.close();
            } catch (IOException io) {

            }
        }

        /**
         * La fuente utilizada es Arial Unicode MS porque soporta carácteres del idioma japonés.
         */
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
        this.vista.passForgotItem.setText(rb.getString("forgot"));
        this.vista.passForgotItem.setFont(fuente);
        this.vista.passForgotItem.addActionListener(this);
        this.vista.passForgotItem.setActionCommand("FORGOT");

        this.vista.emailLabel.setText(rb.getString("email"));
        this.vista.emailLabel.setFont(fuente);
        this.vista.emailField.setFont(fuente);
        this.vista.passwordLabel.setText(rb.getString("password"));
        this.vista.passwordLabel.setFont(fuente);
        this.vista.passwordField.setFont(fuente);
        this.vista.rememberMeCheck.setText(rb.getString("remember"));
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
            /**
             * Entra aquí si se ha pulsado un JButton.
             */
            JButton boton = (JButton) e.getSource();

            switch (boton.getActionCommand()) {
                case "SIGNIN":
                    /**
                     * Entra aquí se ha hecho clic en el botón de Iniciar sesión.
                     */
                    boolean correcto = this.modelo.comprobarUsuario(this.vista.emailField.getText(), new String(this.vista.passwordField.getPassword()));
                    if (correcto) {

                        if (this.vista.rememberMeCheck.isSelected()) {
                            try {
                                save = new FileWriter(file);
                                save.write("T");
                                save.write("\r\n");
                                save.write(this.vista.emailField.getText());
                                save.write("\r\n");
                                save.write(new String(this.vista.passwordField.getPassword()));
                                save.write("\r\n");
                                save.close();
                            } catch (IOException io) {
                                io.printStackTrace();
                            }
                        } else {
                            if (file.exists()) {
                                try {
                                    save = new FileWriter(file);
                                    save.write("F");
                                    save.close();
                                } catch (IOException io) {
                                    io.printStackTrace();
                                }
                            }
                        }

                        Diccionario view = new Diccionario(rb.getString("dict"));
                        PalabraDAO model = new PalabraDAO();
                        ControladorDiccionario controller = new ControladorDiccionario(view, model, this.lang, this.vista.emailField.getText());
                        this.vista.dispose();
                        view.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, rb.getString("badlogin1") + "\n" + rb.getString("badlogin2"), rb.getString("badloginmes"), JOptionPane.WARNING_MESSAGE);
                    }
                    break;
                case "CREATEACCOUNT":
                    /**
                     * Entra aquí si se ha hecho clic en el botón de Crear cuenta.
                     */
                    Registro view = new Registro(rb.getString("signup"));
                    UsuarioDAO model = new UsuarioDAO();
                    ControladorRegistro controller = new ControladorRegistro(view, model, this.lang, this.vista.emailField.getText());
                    this.vista.dispose();
                    view.setVisible(true);
                    break;
            }
            
        } else if (e.getSource() instanceof JMenuItem) {
            /**
             * Entra aquí si se ha pulsado un JMenuItem.
             */
            JMenuItem item = (JMenuItem) e.getSource();

            switch (item.getActionCommand()) {
                case "ESP":
                    /**
                     * Para cambiar el idioma a Español.
                     */
                    InicioSesion viewes = new InicioSesion("Iniciar sesión");
                    UsuarioDAO modeles = new UsuarioDAO();
                    ControladorLogin controlleres = new ControladorLogin(viewes, modeles,"es", this.vista.emailField.getText());
                    this.vista.dispose();
                    viewes.setVisible(true);
                    break;
                case "ENG":
                    /**
                     * Cambiar el idioma a Inglés.
                     */
                    InicioSesion viewen = new InicioSesion("Sign in");
                    UsuarioDAO modelen = new UsuarioDAO();
                    ControladorLogin controlleren = new ControladorLogin(viewen, modelen, "en", this.vista.emailField.getText());
                    this.vista.dispose();
                    viewen.setVisible(true);
                    break;
                case "JPN":
                    /**
                     * Cambiar el idioma a Japonés.
                     */
                    InicioSesion viewjp = new InicioSesion("サインイン");
                    UsuarioDAO modeljp = new UsuarioDAO();
                    ControladorLogin controllerjp = new ControladorLogin(viewjp, modeljp, "ja", this.vista.emailField.getText());
                    this.vista.dispose();
                    viewjp.setVisible(true);
                    break;
                case "DEU":
                    /**
                     * Cambiar el idioma a Alemán.
                     */
                    InicioSesion viewde = new InicioSesion("Anmelden");
                    UsuarioDAO modelde = new UsuarioDAO();
                    ControladorLogin controllerde = new ControladorLogin(viewde, modelde, "de", this.vista.emailField.getText());
                    this.vista.dispose();
                    viewde.setVisible(true);
                    break;
                case "CLOSE":
                    /**
                     * Para cerrar la aplicación desde la barra de menú.
                     */
                    int opcion = JOptionPane.showConfirmDialog(null, rb.getString("closequest2"), rb.getString("closequest"), JOptionPane.YES_NO_OPTION);
                    if (opcion == 0) {
                        System.exit(0);
                    }
                    break;
                case "FORGOT":
                    /**
                     * Para recuperar la contraseña.
                     */
                    JFrame forgotFrame = new JFrame();
                    JTextField mailField = new JTextField(15);
                    JTextField userField = new JTextField(15);

                    Object[] fields = {"Email", mailField, "User", userField};

                    int option = JOptionPane.showConfirmDialog(forgotFrame, fields, rb.getString("rememberpass"), JOptionPane.OK_CANCEL_OPTION,  JOptionPane.INFORMATION_MESSAGE);

                    if (option == JOptionPane.OK_OPTION) {
                        String mail = mailField.getText();
                        String user = userField.getText();
                        String pass = this.modelo.recuperarPassword(mail, user);
                        if (pass != null) {
                            JOptionPane.showMessageDialog(null, rb.getString("urpass") + pass, null, JOptionPane.PLAIN_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, rb.getString("useremail"), null, JOptionPane.WARNING_MESSAGE);
                        }

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
        /**
         * Para cerrar la aplicación desde el icono de la X.
         */
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

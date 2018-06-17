package controlador;

import modelo.*;
import vista.Diccionario;
import vista.Registro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class ControladorConfiguracion implements ActionListener {

    private Registro vista;
    private UsuarioDAO modelo;
    private Usuario usuario;
    private String lang;
    private Locale loc;
    private ResourceBundle rb;

    public ControladorConfiguracion(Registro vista, UsuarioDAO modelo, String lang, String email) {

        this.vista = vista;
        this.modelo = modelo;
        this.lang = lang;
        this.loc = new Locale(this.lang);
        this.rb = ResourceBundle.getBundle("locales.configuracion.locale", this.loc);

        Font fuente = new Font("Arial Unicode MS", Font.PLAIN, 15);

        usuario = modelo.obtenerUsuario(email);

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
        this.vista.emailField.setText(usuario.getEmail());
        this.vista.emailField.setFont(fuente);
        this.vista.emailField.setEditable(false);
        this.vista.userLabel.setText(rb.getString("user"));
        this.vista.userLabel.setFont(fuente);
        this.vista.userField.setText(usuario.getUsername());
        this.vista.userField.setFont(fuente);
        this.vista.passwordLabel.setText(rb.getString("passwd"));
        this.vista.passwordLabel.setFont(fuente);
        this.vista.passwordField.setText(usuario.getPassword());
        this.vista.passwordField.setFont(fuente);
        this.vista.nativeLangLabel.setText(rb.getString("mothertongue"));
        this.vista.nativeLangLabel.setFont(fuente);
        this.vista.genderLabel.setText(rb.getString("sex"));
        this.vista.genderLabel.setFont(fuente);
        this.vista.spokenLangLabel.setText(rb.getString("speaklangs"));
        this.vista.spokenLangLabel.setFont(fuente);
        this.vista.learnLangLabel.setText(rb.getString("learnlangs"));
        this.vista.learnLangLabel.setFont(fuente);
        this.vista.nativeLangBox.setFont(fuente);
        this.vista.genderBox.setFont(fuente);
        this.vista.spokenLangList.setFont(fuente);
        this.vista.spokenLangList.setLayoutOrientation(JList.VERTICAL_WRAP);
        this.vista.spokenLangList.setVisibleRowCount(3);
        this.vista.learnLangList.setFont(fuente);
        this.vista.learnLangList.setLayoutOrientation(JList.VERTICAL_WRAP);
        this.vista.learnLangList.setVisibleRowCount(3);
        this.vista.signupButton.setText(rb.getString("changes"));
        this.vista.signupButton.setFont(fuente);
        this.vista.signupButton.addActionListener(this);
        this.vista.signupButton.setActionCommand("CONFIRM");
        this.vista.returnButton.setText(rb.getString("return"));
        this.vista.returnButton.setFont(fuente);
        this.vista.returnButton.addActionListener(this);
        this.vista.returnButton.setActionCommand("RETURN");

        completarOpciones();

        this.vista.pack();
        this.vista.setLocationRelativeTo(null);
        this.vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

        this.vista.nativeLangBox.setSelectedIndex(IdiomaDAO.codeToIndex(usuario.getNativeLang()));
        switch (this.usuario.getGender()) {
            case "H":
                this.vista.genderBox.setSelectedIndex(0);
                break;
            case "M":
                this.vista.genderBox.setSelectedIndex(1);
                break;
            case "N":
                this.vista.genderBox.setSelectedIndex(2);
        }

        int[] learnLangs = new int[usuario.getLearnLangs().size()];
        int cont = 0;
        for (Idioma idioma : usuario.getLearnLangs()) {
            learnLangs[cont] = IdiomaDAO.codeToIndex(idioma.getCodigo());
            cont++;
        }
        int[] speakLangs = new int[usuario.getSpokenLangs().size()];
        cont=0;
        for(Idioma idioma : usuario.getSpokenLangs()) {
            speakLangs[cont] = IdiomaDAO.codeToIndex(idioma.getCodigo());
            cont++;
        }

        this.vista.spokenLangList.setSelectedIndices(speakLangs);
        this.vista.learnLangList.setSelectedIndices(learnLangs);
    }

    private void volver() {
        Diccionario dict = new Diccionario(rb.getString("dictionary"));
        PalabraDAO model = new PalabraDAO();
        ControladorDiccionario cont = new ControladorDiccionario(dict, model, this.lang, this.vista.emailField.getText());
        this.vista.dispose();
        dict.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton b = (JButton) e.getSource();
            switch (b.getActionCommand()) {
                case "CONFIRM":
                    String gender = "N";
                    int gende = this.vista.genderBox.getSelectedIndex();
                    if (gende == 0) {
                        gender = "H";
                    } else if (gende == 1) {
                        gender = "M";
                    }
                    if ((this.vista.userField.getText().length() >= 5 && this.vista.userField.getText().length() <= 25) && (this.vista.passwordField.getPassword().length >= 5 && this.vista.passwordField.getPassword().length <= 25)) {
                        int result = modelo.actualizarUsuario(
                                this.vista.emailField.getText(),
                                this.vista.userField.getText(),
                                new String(this.vista.passwordField.getPassword()),
                                gender,
                                this.vista.nativeLangBox.getSelectedIndex(),
                                this.vista.spokenLangList.getSelectedIndices(),
                                this.vista.learnLangList.getSelectedIndices()
                        );
                        if (result == 1) {
                            volver();
                        }
                    } else {
                        // Error datos
                        String errores = "<html>";
                        if (this.vista.userField.getText().length() < 5 || this.vista.userField.getText().length() > 25) {
                            errores += "- " + rb.getString("usernovalid") + "<br>";
                        }
                        if (new String(this.vista.passwordField.getPassword()).length() < 5 || new String(this.vista.passwordField.getPassword()).length() > 25) {
                            errores += "- " + rb.getString("passnovalid") + "<br>";
                        }
                        errores += "</html>";
                        JOptionPane.showMessageDialog(null, errores, rb.getString("invaliddata"), JOptionPane.WARNING_MESSAGE);
                    }
                    break;
                case "RETURN":
                    if (JOptionPane.showConfirmDialog(null, rb.getString("returnquest"), rb.getString("closequest"), JOptionPane.YES_NO_OPTION) == 0) {
                        volver();
                    }
                    break;
            }
        } else if (e.getSource() instanceof JMenuItem) {
            JMenuItem i = (JMenuItem) e.getSource();
            switch (i.getActionCommand()) {
                case "ENG":
                    Registro regen = new Registro("Configuration");
                    UsuarioDAO modelen = new UsuarioDAO();
                    ControladorConfiguracion conten = new ControladorConfiguracion(regen, modelen, "en", this.vista.emailField.getText());
                    this.vista.dispose();
                    regen.setVisible(true);
                    break;
                case "ESP":
                    Registro reges = new Registro("Configuración");
                    UsuarioDAO modeles = new UsuarioDAO();
                    ControladorConfiguracion contes = new ControladorConfiguracion(reges, modeles, "es", this.vista.emailField.getText());
                    this.vista.dispose();
                    reges.setVisible(true);
                    break;
                case "JPN":
                    Registro regja = new Registro("設定");
                    UsuarioDAO modelja = new UsuarioDAO();
                    ControladorConfiguracion contja = new ControladorConfiguracion(regja, modelja, "ja", this.vista.emailField.getText());
                    this.vista.dispose();
                    regja.setVisible(true);
                    break;
                case "DEU":
                    Registro regde = new Registro("Configuration");
                    UsuarioDAO modelde = new UsuarioDAO();
                    ControladorConfiguracion contde = new ControladorConfiguracion(regde, modelde, "de", this.vista.emailField.getText());
                    this.vista.dispose();
                    regde.setVisible(true);
                    break;
                case "CLOSE":
                    if (JOptionPane.showConfirmDialog(null, rb.getString("closequest2"), rb.getString("closequest"), JOptionPane.YES_NO_OPTION) == 0) {
                        System.exit(0);
                    }
                    break;
            }
        }
    }
}

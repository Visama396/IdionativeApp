package controlador;

import modelo.Idioma;
import modelo.IdiomaDAO;
import modelo.Usuario;
import modelo.UsuarioDAO;
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

        this.vista.emailLabel.setText("Email");
        this.vista.emailLabel.setFont(fuente);
        this.vista.emailField.setText(usuario.getEmail());
        this.vista.emailField.setFont(fuente);
        this.vista.userLabel.setText("Usuario");
        this.vista.userLabel.setFont(fuente);
        this.vista.userField.setText(usuario.getUsername());
        this.vista.userField.setFont(fuente);
        this.vista.passwordLabel.setText("Contraseña");
        this.vista.passwordLabel.setFont(fuente);
        this.vista.passwordField.setText(usuario.getPassword());
        this.vista.passwordField.setFont(fuente);
        this.vista.nativeLangLabel.setText("Lengua materna");
        this.vista.nativeLangLabel.setFont(fuente);
        this.vista.genderLabel.setText("Sexo");
        this.vista.genderLabel.setFont(fuente);
        this.vista.spokenLangLabel.setText("Idiomas que hablas:");
        this.vista.learnLangLabel.setText("Idiomas que quieres aprender:");
        this.vista.spokenLangList.setFont(fuente);
        this.vista.spokenLangList.setLayoutOrientation(JList.VERTICAL_WRAP);
        this.vista.spokenLangList.setVisibleRowCount(3);
        this.vista.learnLangList.setFont(fuente);
        this.vista.learnLangList.setLayoutOrientation(JList.VERTICAL_WRAP);
        this.vista.learnLangList.setVisibleRowCount(3);
        this.vista.signupButton.setText("Confirmar cambios");
        this.vista.returnButton.setText("Volver");

        completarOpciones();

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

        int[] learnLangs = new int[usuario.getLearnLangs().size()];
        int cont = 0;
        for (Idioma idioma : usuario.getLearnLangs()) {
            learnLangs[cont] = IdiomaDAO.codeToIndex(idioma.getCodigo());
            System.out.println(idioma.getCodigo());
            cont++;
        }
        int[] speakLangs = new int[usuario.getSpokenLangs().size()];
        cont=0;
        for(Idioma idioma : usuario.getSpokenLangs()) {
            speakLangs[cont] = IdiomaDAO.codeToIndex(idioma.getCodigo());
            System.out.println(idioma.getCodigo());
            cont++;
        }

        this.vista.spokenLangList.setSelectedIndices(speakLangs);
        this.vista.learnLangList.setSelectedIndices(learnLangs);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

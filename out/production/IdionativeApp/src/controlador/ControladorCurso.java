package controlador;

import modelo.Curso;
import modelo.CursoDAO;
import modelo.Leccion;
import modelo.UsuarioDAO;
import vista.Cursos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class ControladorCurso implements ActionListener {

    private Cursos vista;
    private CursoDAO modelo;
    private String lang;
    private String email;
    private Locale loc;
    private ResourceBundle rb;

    public ControladorCurso (Cursos vista, CursoDAO modelo, String lang, String email) {
        this.vista = vista;
        this.modelo = modelo;
        this.lang = lang;
        this.email = email;

        this.loc = new Locale(this.lang);
        //this.rb = ResourceBundle.getBundle("locales.cursos.locale", this.loc);

        Font fuente = new Font("Arial Unicode MS", Font.PLAIN, 15);

        this.vista.cursosLabel.setText("Cursos");
        this.vista.leccionesLabel.setText("Lecciones");
        this.vista.leccionesLabel.setVisible(false);
        this.vista.editButton.setText("Editar");
        this.vista.editButton.setVisible(false);
        this.vista.jsContenido.setVisible(false);
        this.vista.leccionesComboBox.setVisible(false);
        this.vista.cursosComboBox.addActionListener(this);
        this.vista.leccionesComboBox.addActionListener(this);
        this.vista.editButton.addActionListener(this);

        cargarCursos();

        this.vista.setLocationRelativeTo(null);
        this.vista.pack();
    }

    private void cargarCursos() {

        // Los idiomas que el usuario quiera aprender se usarán para filtar los cursos encontrados en el idioma de la aplicación.
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ArrayList<String> idiomas_aprender = usuarioDAO.idiomasAprender(this.email);

        String l = "";
        switch (this.lang) {
            case "es":
                l = "esp";
                break;
            case "en":
                l = "eng";
                break;
            case "ja":
                l = "jpn";
                break;
            case "de":
                l = "deu";
                break;
        }

        // El idioma de la aplicación servirá para buscar cursos.
        ArrayList<Curso> cursos = this.modelo.buscarCursos(l, idiomas_aprender);

        DefaultComboBoxModel<Curso> model = new DefaultComboBoxModel<>();
        for (Curso c:cursos) {
            model.addElement(c);
        }

        this.vista.cursosComboBox.setModel(model);

    }

    private void cargarLecciones(Curso c) {
        ArrayList<Leccion> lecciones = modelo.buscarLecciones(c);
        DefaultComboBoxModel<Leccion> model = new DefaultComboBoxModel<>();
        for (Leccion l:lecciones) {
            model.addElement(l);
        }
        this.vista.leccionesComboBox.setModel(model);
        this.vista.leccionesLabel.setVisible(true);
        this.vista.leccionesComboBox.setVisible(true);
        this.vista.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vista.cursosComboBox) {
            Curso c = (Curso) this.vista.cursosComboBox.getSelectedItem();
            cargarLecciones(c);
        } else if (e.getSource() == this.vista.leccionesComboBox) {
            JTextPane contenido = new JTextPane();
            contenido.setContentType("text/html");
            contenido.setText(((Leccion) this.vista.leccionesComboBox.getSelectedItem()).getContentlec());
            this.vista.jsContenido.setViewportView(contenido);
            this.vista.jsContenido.setVisible(true);
            this.vista.editButton.setVisible(true);
            this.vista.pack();
        } else if (e.getSource() == this.vista.editButton) {
            JTextArea contenido = new JTextArea(((Leccion) this.vista.leccionesComboBox.getSelectedItem()).getContentlec());
            contenido.setLineWrap(true);
            contenido.setWrapStyleWord(true);
            this.vista.jsContenido.setViewportView(contenido);
            this.vista.editButton.setText("Confirmar cambios");
        }
    }
}

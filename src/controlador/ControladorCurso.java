package controlador;

import modelo.*;
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
        this.vista.cursosLabel.setFont(fuente);
        this.vista.leccionesLabel.setText("Lecciones");
        this.vista.leccionesLabel.setFont(fuente);
        this.vista.leccionesLabel.setVisible(false);
        this.vista.editButton.setText("Editar");
        this.vista.editButton.setFont(fuente);
        this.vista.editButton.setVisible(false);
        this.vista.jsContenido.setVisible(false);
        this.vista.leccionesComboBox.setVisible(false);
        this.vista.leccionesComboBox.setFont(fuente);
        this.vista.cursosComboBox.addActionListener(this);
        this.vista.cursosComboBox.setFont(fuente);
        this.vista.leccionesComboBox.addActionListener(this);
        this.vista.editButton.addActionListener(this);

        cargarCursos();

        this.vista.setLocationRelativeTo(null);
        this.vista.pack();
    }

    public void cargarCursos() {

        // Los idiomas que el usuario quiera aprender se usarán para filtar los cursos encontrados en el idioma de la aplicación.
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ArrayList<String> idiomas_aprender = usuarioDAO.idiomasAprender(this.email);

        // El idioma de la aplicación servirá para buscar cursos.
        String l = IdiomaDAO.appToCode(this.lang);

        ArrayList<Curso> cursos = this.modelo.buscarCursos(l, idiomas_aprender);
        DefaultComboBoxModel<Curso> model = new DefaultComboBoxModel<>();
        for (Curso c:cursos) {
            model.addElement(c);
        }

        this.vista.cursosComboBox.setModel(model);

    }

    private void cargarLecciones(Curso c) {
        ArrayList<Leccion> lecciones = modelo.buscarLecciones(c);
        if (lecciones.size() > 0) {
            DefaultComboBoxModel<Leccion> model = new DefaultComboBoxModel<>();
            for (Leccion l:lecciones) {
                model.addElement(l);
            }
            this.vista.leccionesComboBox.setModel(model);
            this.vista.leccionesLabel.setVisible(true);
            this.vista.leccionesComboBox.setVisible(true);
            this.vista.pack();
        } else {
            JOptionPane.showMessageDialog(null, "No se han añadido lecciones a este curso", "", JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vista.cursosComboBox) {
            Curso c = (Curso) this.vista.cursosComboBox.getSelectedItem();
            this.vista.descLabel.setText(c.getContenido_curso());
            this.vista.descLabel.setFont(new Font("Arial Unicode MS", Font.PLAIN, 13));
            cargarLecciones(c);
        } else if (e.getSource() == this.vista.leccionesComboBox) {
            JTextPane contenido = new JTextPane();
            contenido.setFont(new Font("Arial Unicode MS", Font.PLAIN, 15));
            contenido.setContentType("text/html");
            contenido.setText("<html>"+((Leccion) this.vista.leccionesComboBox.getSelectedItem()).getContentlec()+"</html>");
            this.vista.jsContenido.setViewportView(contenido);
            this.vista.jsContenido.setVisible(true);
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

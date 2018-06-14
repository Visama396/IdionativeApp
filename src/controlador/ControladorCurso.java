package controlador;

import modelo.Curso;
import modelo.CursoDAO;
import vista.Cursos;

import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class ControladorCurso {

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
        this.vista.tituloLabel.setText("<Titulo>");
        this.vista.autorFechaLabel.setText("<Autor> - <Fecha>");
        this.vista.contenidoLeccion.setText("Elije un curso y una lección");
        this.vista.showButton.setText("Mostrar");

        this.vista.setLocationRelativeTo(null);
        this.vista.pack();
    }

}

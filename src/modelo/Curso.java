package modelo;

public class Curso {

    private int id_curso;
    private String nombre_curso;
    private String contenido_curso;
    private String autor_curso;
    private String idioma_curso;
    private String idioma_aprender;

    public Curso(int id_curso, String nombre_curso, String contenido_curso, String autor_curso, String idioma_curso, String idioma_aprender) {
        this.id_curso = id_curso;
        this.nombre_curso = nombre_curso;
        this.contenido_curso = contenido_curso;
        this.autor_curso = autor_curso;
        this.idioma_curso = idioma_curso;
        this.idioma_aprender = idioma_aprender;
    }

    public int getId_curso() {
        return id_curso;
    }

    public void setId_curso(int id_curso) {
        this.id_curso = id_curso;
    }

    public String getNombre_curso() {
        return nombre_curso;
    }

    public void setNombre_curso(String nombre_curso) {
        this.nombre_curso = nombre_curso;
    }

    public String getContenido_curso() {
        return contenido_curso;
    }

    public void setContenido_curso(String contenido_curso) {
        this.contenido_curso = contenido_curso;
    }

    public String getAutor_curso() {
        return autor_curso;
    }

    public void setAutor_curso(String autor_curso) {
        this.autor_curso = autor_curso;
    }

    public String getIdioma_curso() {
        return idioma_curso;
    }

    public void setIdioma_curso(String idioma_curso) {
        this.idioma_curso = idioma_curso;
    }

    public String getIdioma_aprender() {
        return idioma_aprender;
    }

    public void setIdioma_aprender(String idioma_aprender) {
        this.idioma_aprender = idioma_aprender;
    }

    @Override
    public String toString() {
        return id_curso + " " + nombre_curso;
    }
}

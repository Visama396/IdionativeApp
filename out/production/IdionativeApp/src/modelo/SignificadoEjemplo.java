package modelo;

public class SignificadoEjemplo {

    private int id;
    private String significado;
    private String ejemplo;
    private String idioma;

    public SignificadoEjemplo(int id, String sig, String eje, String idioma) {
        this.id = id;
        this.significado = sig;
        this.ejemplo = eje;
        this.idioma = idioma;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSignificado() {
        return significado;
    }

    public void setSignificado(String significado) {
        this.significado = significado;
    }

    public String getEjemplo() {
        return ejemplo;
    }

    public void setEjemplo(String ejemplo) {
        this.ejemplo = ejemplo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
}

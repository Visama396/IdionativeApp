package modelo;

public class Idioma {

    private String codigo;
    private String idioma;
    private String pais;

    public Idioma(String cod, String idioma, String pais) {
        this.codigo = cod;
        this.idioma = idioma;
        this.pais = pais;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}

package modelo;

public class Leccion {

    private int idlec;
    private int idcurso;
    private String titulolec;
    private String contentlec;
    private String idiomalec;

    public Leccion(int idlec, int idcurso, String titulolec, String contentlec, String idiomalec) {
        this.idlec = idlec;
        this.idcurso = idcurso;
        this.titulolec = titulolec;
        this.contentlec = contentlec;
        this.idiomalec = idiomalec;
    }

    public int getIdlec() {
        return idlec;
    }

    public void setIdlec(int idlec) {
        this.idlec = idlec;
    }

    public int getIdcurso() {
        return idcurso;
    }

    public void setIdcurso(int idcurso) {
        this.idcurso = idcurso;
    }

    public String getTitulolec() {
        return titulolec;
    }

    public void setTitulolec(String titulolec) {
        this.titulolec = titulolec;
    }

    public String getContentlec() {
        return contentlec;
    }

    public void setContentlec(String contentlec) {
        this.contentlec = contentlec;
    }

    public String getIdiomalec() {
        return idiomalec;
    }

    public void setIdiomalec(String idiomalec) {
        this.idiomalec = idiomalec;
    }

    public String toString() {
        return idlec + " " + titulolec;
    }
}

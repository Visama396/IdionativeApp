package modelo;

public class Palabra {

    private int id_word;
    private String eng;
    private String esp;
    private String jpn;
    private String kana;
    private String deu;
    private String ptr;

    public Palabra(int id_word, String eng, String esp, String jpn, String kana, String deu, String ptr) {
        this.id_word = id_word;
        this.eng = eng;
        this.esp = esp;
        this.jpn = jpn;
        this.kana = kana;
        this.deu = deu;
        this.ptr = ptr;
    }

    public int getId_word() {
        return id_word;
    }

    public void setId_word(int id_word) {
        this.id_word = id_word;
    }

    public String getEng() {
        return eng;
    }

    public void setEng(String eng) {
        this.eng = eng;
    }

    public String getEsp() {
        return esp;
    }

    public void setEsp(String esp) {
        this.esp = esp;
    }

    public String getJpn() {
        return jpn;
    }

    public void setJpn(String jpn) {
        this.jpn = jpn;
    }

    public String getKana() {
        return kana;
    }

    public void setKana(String kana) {
        this.kana = kana;
    }

    public String getDeu() {
        return deu;
    }

    public void setDeu(String deu) {
        this.deu = deu;
    }

    public String getPtr() {
        return ptr;
    }

    public void setPtr(String ptr) {
        this.ptr = ptr;
    }

    @Override
    public String toString() {
        return "Palabra{" +
                "id_word=" + id_word +
                ", eng='" + eng + '\'' +
                ", esp='" + esp + '\'' +
                ", jpn='" + jpn + '\'' +
                ", kana='" + kana + '\'' +
                ", deu='" + deu + '\'' +
                ", ptr='" + ptr + '\'' +
                '}';
    }
}

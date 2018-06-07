package modelo;

import java.util.ArrayList;

public class Usuario {

    private String email;
    private String username;
    private String password;
    private String nativeLang;
    private String gender; // M mujer, H hombre, N no especificado/nulo
    private String tipo; // A adming, P profesor, E estudiante
    private ArrayList<Idioma> spokenLangs;
    private ArrayList<Idioma> learnLangs;

    public Usuario(String email, String username, String password, String nativeLang, String gender, String tipo, ArrayList<Idioma> spokenLangs, ArrayList<Idioma> learnLangs) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.nativeLang = nativeLang;
        this.gender = gender;
        this.tipo = tipo;
        this.spokenLangs = spokenLangs;
        this.learnLangs = learnLangs;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNativeLang() {
        return nativeLang;
    }

    public void setNativeLang(String nativeLang) {
        this.nativeLang = nativeLang;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public ArrayList<Idioma> getSpokenLangs() {
        return spokenLangs;
    }

    public void setSpokenLangs(ArrayList<Idioma> spokenLangs) {
        this.spokenLangs = spokenLangs;
    }

    public ArrayList<Idioma> getLearnLangs() {
        return learnLangs;
    }

    public void setLearnLangs(ArrayList<Idioma> learnLangs) {
        this.learnLangs = learnLangs;
    }
}

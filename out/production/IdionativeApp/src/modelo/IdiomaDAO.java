package modelo;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class IdiomaDAO {

    private Conexion con;

    public IdiomaDAO() {
        con = new Conexion();
    }

    public static int codeToIndex(String code) {
        int ind = 0;

        switch (code) {
            case "eng":
                ind = 0;
                break;
            case "esp":
                ind = 1;
                break;
            case "jpn":
                ind = 2;
                break;
            case "deu":
                ind = 3;
                break;
            case "ptr":
                ind = 4;
                break;
        }

        return ind;
    }

    public static String indexToCode(int index) {

        String lang="";

        switch (index) {
            case 0:
                lang = "eng";
                break;
            case 1:
                lang = "esp";
                break;
            case 2:
                lang = "jpn";
                break;
            case 3:
                lang = "deu";
                break;
            case 4:
                lang = "ptr";
        }

        return lang;

    }

    public ArrayList<String> obtenerIdiomas(String lang) {
        ArrayList<String> idiomas = new ArrayList<>();
        Connection conn = con.createConnection();

        PreparedStatement ps = null;
        ResultSet rs=null;

        try {
            switch (lang) {
                case "en":
                    ps = conn.prepareStatement("SELECT ENG FROM PALABRAS WHERE ID_PALABRA IN (SELECT PALABRA_REFERENCIA FROM IDIOMAS)");
                    break;
                case "es":
                    ps = conn.prepareStatement("SELECT ESP FROM PALABRAS WHERE ID_PALABRA IN (SELECT PALABRA_REFERENCIA FROM IDIOMAS)");
                    break;
                case "ja":
                    ps = conn.prepareStatement("SELECT JPN FROM PALABRAS WHERE ID_PALABRA IN (SELECT PALABRA_REFERENCIA FROM IDIOMAS)");
                    break;
                case "de":
                    ps = conn.prepareStatement("SELECT DEU FROM PALABRAS WHERE ID_PALABRA IN (SELECT PALABRA_REFERENCIA FROM IDIOMAS)");
                default:
                    conn.prepareStatement("SELECT ESP FROM PALABRAS WHERE ID_PALABRA IN (SELECT PALABRA_REFERENCIA FROM IDIOMAS)");
                    break;
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                idiomas.add(rs.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "IdiomaDAO: SQLException", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException e) {
            //
        }

        return idiomas;
    }

}

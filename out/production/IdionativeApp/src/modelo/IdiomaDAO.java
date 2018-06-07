package modelo;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class IdiomaDAO {

    private Conexion con;

    public IdiomaDAO() {
        con = new Conexion();
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

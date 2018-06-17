package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignificadoEjemploDAO {

    private Conexion con;

    public SignificadoEjemploDAO() {
        con = new Conexion();
    }

    public SignificadoEjemplo obtenerSignificado(int id, String lang) {
        SignificadoEjemplo sig = null;
        Connection conn = con.createConnection();
        ResultSet rs = null;

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM SIGNIFICADO_EJEMPLO WHERE ID_SIG = ? AND IDIOMA_SIG = ?");
            ps.setInt(1, id);
            ps.setString(2, lang);
            rs = ps.executeQuery();
            if (rs.next()) {
                sig = new SignificadoEjemplo(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sig;

    }

    public int actualizarSignificado(int idsig, String sig, String ej, String lang) {
        int result = 0;
        Connection conn = con.createConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE SIGNIFICADO_EJEMPLO SET SIGNIFICADO = ?, EJEMPLO = ? WHERE ID_SIG = ? AND IDIOMA_SIG = ?");
            ps.setString(1, sig);
            ps.setString(2, ej);
            ps.setInt(3, idsig);
            ps.setString(4, lang);
            result += ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int addSignificado(int id, String sig, String ej, String lang) {
        int result = 0;
        Connection conn = con.createConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO SIGNIFICADO_EJEMPLO VALUES(?, ?, ?, ?)");
            ps.setInt(1, id);
            ps.setString(2, sig);
            ps.setString(3, ej);
            ps.setString(4, lang);
            result += ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}

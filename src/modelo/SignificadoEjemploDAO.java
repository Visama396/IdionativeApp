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
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM SIGNIFICADO_EJEMPLOS WHERE ID_SIG = ? AND IDIOMA_SIG = ?");
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

}

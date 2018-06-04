package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PalabraDAO {

    private Conexion con;

    public PalabraDAO() {
        con = new Conexion();
    }

    public Palabra buscarPalabra(int idword) {
        Connection conn = con.createConnection();
        Palabra devolver = null;

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM palabras WHERE ID_PALABRA = ?");
            ps.setInt(1, idword);

            ResultSet rs = ps.executeQuery();
            rs.next();
            devolver = new Palabra(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8),
                    Palabra.Word_types.NOUN
            );
        } catch (SQLException e) {
            System.out.println(e);
        }

        return devolver;

    }
}

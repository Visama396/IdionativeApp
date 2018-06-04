package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PalabraDAO {

    private Conexion con;

    public PalabraDAO() {
        con = new Conexion();
    }

    public ArrayList<Palabra> cargarDiccionario() {
        ArrayList<Palabra> dict = new ArrayList<>();
        Connection conn = con.createConnection();

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM PALABRAS");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Palabra p = new Palabra(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7));
                dict.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dict;
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
                    rs.getString(7)
            );
        } catch (SQLException e) {
            System.out.println(e);
        }

        return devolver;

    }
}

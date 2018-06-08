package modelo;

import javax.swing.*;
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

    public int siguientePalabra() {
        Connection conn = con.createConnection();
        ResultSet rs = null;
        int max = 0;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT MAX(ID_PALABRA) FROM PALABRAS");
            rs = ps.executeQuery();
            if(rs.next()) {
                max = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {

            }
            con.closeConnection(conn);
        }

        return max;
    }

    public Palabra buscarPalabra (String word) {
        Palabra p = null;
        Connection conn = con.createConnection();
        ResultSet rs = null;

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM PALABRAS WHERE ENG = INITCAP(?) OR ESP = INITCAP(?) OR JPN = INITCAP(?) OR KANA = INITCAP(?) OR DEU = INITCAP(?) OR PTR = INITCAP(?)");
            ps.setString(1, word);
            ps.setString(2, word);
            ps.setString(3, word);
            ps.setString(4, word);
            ps.setString(5, word);
            ps.setString(6, word);

            rs = ps.executeQuery();
            if (rs.next())
                p = new Palabra(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            con.closeConnection(conn);
        }

        return p;
    }

    public int eliminarPalabra(int id) {
        Connection conn = con.createConnection();
        PreparedStatement ps = null;
        int result = 0;

        try {
            ps = conn.prepareStatement("DELETE FROM PALABRAS WHERE ID_PALABRA = ?");
            ps.setInt(1, id);

            result = ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "PalabraDAO: SQLException", JOptionPane.ERROR_MESSAGE);
        } finally {
            con.closeConnection(conn);
        }

        return result;
    }

    public int actualizarPalabra(int id, String eng, String esp, String jpn, String kana, String deu, String ptr) {
        Connection conn = con.createConnection();
        PreparedStatement ps = null;
        int result = 0;

        try {
            ps = conn.prepareStatement("UPDATE PALABRAS SET ENG = INITCAP(?), ESP = INITCAP(?), JPN = INITCAP(?), KANA = INITCAP(?), DEU = INITCAP(?), PTR = INITCAP(?) WHERE ID_PALABRA = ?");
            ps.setString(1, eng);
            ps.setString(2, esp);
            ps.setString(3, jpn);
            ps.setString(4, kana);
            ps.setString(5, deu);
            ps.setString(6, ptr);
            ps.setInt(7, id);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            con.closeConnection(conn);
        }

        return result;
    }

    public int insertarPalabra(String eng, String esp, String jpn, String kana, String deu, String ptr) {
        Connection conn = con.createConnection();
        PreparedStatement ps=null;
        int result = 0;

        try {

            ps = conn.prepareStatement("INSERT INTO palabras (id_palabra, eng, esp, jpn, kana, deu, ptr) VALUES(INITCAP(?), INITCAP(?), INITCAP(?), INITCAP(?), INITCAP(?), INITCAP(?), INITCAP(?))");
            ps.setInt(1, siguientePalabra()+1);
            ps.setString(2, eng);
            ps.setString(3, esp);
            ps.setString(4, jpn);
            ps.setString(5, kana);
            ps.setString(6, deu);
            ps.setString(7, ptr);

            result = ps.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "PalabraDAO: SQLException", JOptionPane.ERROR_MESSAGE);
        } finally {
            con.closeConnection(conn);
        }

        return result;
    }

    public ArrayList<Palabra> cargarDiccionario() {
        ArrayList<Palabra> dict = new ArrayList<>();
        Connection conn = con.createConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;

        try {
            ps = conn.prepareStatement("SELECT * FROM PALABRAS ORDER BY ID_PALABRA");
            rs = ps.executeQuery();
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
            JOptionPane.showMessageDialog(null, e.getMessage(), "PalabraDAO: SQLException", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "PalabraDAO: SQLException", JOptionPane.ERROR_MESSAGE);
            }
            con.closeConnection(conn);
        }

        return dict;
    }

    public Palabra buscarPalabra(int idword) {
        Connection conn = con.createConnection();
        Palabra devolver = null;
        PreparedStatement ps=null;
        ResultSet rs=null;

        try {
            ps = conn.prepareStatement("SELECT * FROM palabras WHERE ID_PALABRA = ?");
            ps.setInt(1, idword);

            rs = ps.executeQuery();
            if(rs.next()) {
                devolver = new Palabra(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7)
                );
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "PalabraDAO1: SQLException", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "PalabraDAO2: SQLException", JOptionPane.ERROR_MESSAGE);
            }
            con.closeConnection(conn);
        }

        return devolver;

    }
}

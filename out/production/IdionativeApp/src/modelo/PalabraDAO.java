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

    public void eliminarPalabra(int id) {
        Connection conn = con.createConnection();
        PreparedStatement ps = null;
        int result = 0;

        try {
            ps = conn.prepareStatement("DELETE FROM PALABRAS WHERE ID_PALABRA = ?");
            ps.setInt(1, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "PalabraDAO: SQLException", JOptionPane.ERROR_MESSAGE);
        } finally {
            con.closeConnection(conn);
        }
    }

    public void actualizarPalabra(int id, String eng, String esp, String jpn, String kana, String deu, String ptr) {

    }

    public int insertarPalabra(String eng, String esp, String jpn, String kana, String deu, String ptr) {
        Connection conn = con.createConnection();
        PreparedStatement ps=null;
        int result = 0;

        try {

            ps = conn.prepareStatement("INSERT INTO palabras (id_palabra, eng, esp, jpn, kana, deu, ptr) VALUES(?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, cargarDiccionario().size()+1);
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

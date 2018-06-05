package modelo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UsuarioDAO {

    private Conexion con;

    public UsuarioDAO() {
        con = new Conexion();
    }

    public int registrarUsuario(String email, String username, String password, String gender, String nativeLang, List<String> spokenLangs, List<String> learnLangs) {

        int resultado = 0;

        Connection conn = con.createConnection();

        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO usuarios(email, usuario, passwd, genero, nativo) VALUES(?, ?, ?, ?, ?)");
            ps.setString(1, email);
            ps.setString(2, username);
            ps.setString(3, password);
            ps.setString(4, gender);
            ps.setString(5, nativeLang);

            resultado = ps.executeUpdate();

            PreparedStatement spokenps = conn.prepareStatement("INSERT INTO APRENDE_IDIOMAS VALUES (?, ?)");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "UsuarioDAO: SQLException", JOptionPane.ERROR_MESSAGE);
        } finally {
            con.closeConnection(conn);
        }

        return resultado;

    }

    public boolean comprobarUsuario(String email, String password) {

        boolean valido = false;

        Connection conn = con.createConnection();
        ResultSet rs=null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT passwd FROM usuarios WHERE email = ?");
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.getString(1).equals(password)) {
                    valido = true;
                } else {
                    valido = false;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "UsuarioDAO: SQLException", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getClass(), "UsuarioDAO", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "UsuarioDAO: SQLException", JOptionPane.ERROR_MESSAGE);
            }
            con.closeConnection(conn);
        }


        return valido;
    }

}

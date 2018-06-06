package modelo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDAO {

    private Conexion con;

    public UsuarioDAO() {
        con = new Conexion();
    }

    private String indexToCode(int index) {

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

    public int registrarUsuario(String email, String username, String password, String gender, int nativeL, int[] spokenLangs, int[] learnLangs) {

        int resultado = 0;

        Connection conn = con.createConnection();

        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO usuarios(email, usuario, passwd, genero, nativo) VALUES(?, ?, ?, ?, ?)");
            ps.setString(1, email);
            ps.setString(2, username);
            ps.setString(3, password);
            ps.setString(4, gender);
            ps.setString(5, indexToCode(nativeL));
            resultado += ps.executeUpdate();

            PreparedStatement spokenps = conn.prepareStatement("INSERT INTO HABLA_IDIOMAS VALUES(?, ?)");
            for (int index:spokenLangs) {
                spokenps.clearParameters();
                spokenps.setString(1, email);
                spokenps.setString(2, indexToCode(index));
                resultado += spokenps.executeUpdate();
            }

            PreparedStatement learnps = conn.prepareStatement("INSERT INTO APRENDE_IDIOMAS VALUES(?, ?)");
            for (int index:learnLangs) {
                learnps.clearParameters();
                learnps.setString(1, email);
                learnps.setString(2, indexToCode(index));
                resultado += learnps.executeUpdate();
            }

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

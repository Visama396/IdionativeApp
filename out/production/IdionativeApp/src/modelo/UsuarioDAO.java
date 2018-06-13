package modelo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDAO {

    private Conexion con;

    public boolean esProfesor(String email) {
        boolean p = false;
        Connection conn = con.createConnection();
        ResultSet rs = null;

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT TIPO FROM USUARIOS WHERE EMAIL = ?");
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.getString(1).equals("P")) {
                    p = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return p;
    }

    public boolean esAdmin(String email) {
        boolean a = false;

        Connection conn = con.createConnection();
        ResultSet rs = null;

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT TIPO FROM USUARIOS WHERE EMAIL = ?");
            ps.setString(1, email);
            rs = ps.executeQuery();
            if(rs.next()) {
                if (rs.getString(1).equals("A")) {
                    a = true;
                }
            }

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

        return a;
    }

    public UsuarioDAO() {
        con = new Conexion();
    }

    public void eliminarUsuario(String email) {
        Connection conn = con.createConnection();

        try {
            PreparedStatement pshi = conn.prepareStatement("DELETE FROM HABLA_IDIOMAS WHERE USUARIO_HI = ?");
            pshi.setString(1, email);
            pshi.executeUpdate();

            PreparedStatement psai = conn.prepareStatement("DELETE FROM APRENDE_IDIOMAS WHERE USUARIO_AI = ?");
            psai.setString(1, email);
            psai.executeUpdate();

            PreparedStatement ps = conn.prepareStatement("DELETE FROM USUARIOS WHERE EMAIL = ?");
            ps.setString(1, email);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String obtenerNombreUsuario(String email) {
        String user = "";
        Connection conn = con.createConnection();
        ResultSet rs = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT USUARIO FROM USUARIOS WHERE EMAIL = ?");
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) user = rs.getString(1);
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

        return user;
    }

    public Usuario obtenerUsuario(String email) {
        Usuario u = null;
        Connection conn = con.createConnection();
        ResultSet rsUser = null;
        ResultSet rsspoken = null;
        ResultSet rslearn = null;
        try {
            ArrayList<Idioma> spokenLangs = new ArrayList<>();
            PreparedStatement psspoken = conn.prepareStatement("SELECT * FROM IDIOMAS WHERE CODIGO IN (SELECT IDIOMA_AI FROM APRENDE_IDIOMAS WHERE USUARIO_AI = ?)");
            psspoken.setString(1, email);
            rsspoken = psspoken.executeQuery();
            while (rsspoken.next()) {
                spokenLangs.add(new Idioma(
                        rsspoken.getString(1),
                        rsspoken.getString(2),
                        rsspoken.getString(3)
                ));
            }

            ArrayList<Idioma> learnLangs = new ArrayList<>();
            PreparedStatement pslearn = conn.prepareStatement("SELECT * FROM IDIOMAS WHERE CODIGO IN (SELECT IDIOMA_HI FROM HABLA_IDIOMAS WHERE USUARIO_HI = ?)");
            pslearn.setString(1, email);
            rslearn = pslearn.executeQuery();
            while (rslearn.next()) {
                learnLangs.add(new Idioma(
                        rslearn.getString(1),
                        rslearn.getString(2),
                        rslearn.getString(3)
                ));
            }

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM USUARIOS WHERE EMAIL = ?");
            ps.setString(1, email);
            rsUser = ps.executeQuery();
            if (rsUser.next()) {
                u = new Usuario(
                        rsUser.getString(1),
                        rsUser.getString(2),
                        rsUser.getString(3),
                        rsUser.getString(6),
                        rsUser.getString(5),
                        rsUser.getString(4),
                        spokenLangs,
                        learnLangs
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return u;
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
            ps.setString(5, IdiomaDAO.indexToCode(nativeL));
            resultado += ps.executeUpdate();

            PreparedStatement spokenps = conn.prepareStatement("INSERT INTO HABLA_IDIOMAS VALUES(?, ?)");
            for (int index:spokenLangs) {
                spokenps.clearParameters();
                spokenps.setString(1, email);
                spokenps.setString(2, IdiomaDAO.indexToCode(index));
                resultado += spokenps.executeUpdate();
            }

            PreparedStatement learnps = conn.prepareStatement("INSERT INTO APRENDE_IDIOMAS VALUES(?, ?)");
            for (int index:learnLangs) {
                learnps.clearParameters();
                learnps.setString(1, email);
                learnps.setString(2, IdiomaDAO.indexToCode(index));
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
        int existe=0;

        if (password == null) {
            Connection conn = con.createConnection();
            ResultSet rs = null;
            try {
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM usuarios WHERE email = ?");
                ps.setString(1, email);
                rs = ps.executeQuery();
                while(rs.next()) {
                    existe++;
                }
                if (existe == 1) {
                    valido = true;
                }
            } catch (SQLException e) {

            }
        } else {
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
        }


        return valido;
    }

}

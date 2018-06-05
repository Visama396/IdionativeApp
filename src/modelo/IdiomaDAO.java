package modelo;

import oracle.jdbc.proxy.annotation.Pre;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class IdiomaDAO {

    private Conexion con;

    public IdiomaDAO() {
        con = new Conexion();
    }

    public ArrayList<Idioma> obtenerIdiomas() {
        ArrayList<Idioma> idiomas = new ArrayList<>();

        Connection conn = con.createConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;

        try {
            ps = conn.prepareStatement("SELECT * FROM idiomas");
            rs = ps.executeQuery();
            while (rs.next()) {
                idiomas.add(new Idioma(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "UsuarioDAO: SQLException", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "UsuarioDAO: SQLException", JOptionPane.ERROR_MESSAGE);
            }
            con.closeConnection(conn);
        }

        return idiomas;
    }

}

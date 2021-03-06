package modelo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private Connection con;

    public Conexion() {
    }

    public Connection createConnection() {

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@188.77.96.232:1521:xe", "hr", "oracle");
            //con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.143:1521:xe", "hr", "oracle");
            //con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "oracle");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No se ha podido encontrar el driver de Oracle", "Conexion: ClassNotFoundException", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Conexion: SQLException", JOptionPane.ERROR_MESSAGE);
        }

        return con;
    }

    public void closeConnection(Connection con) {
        try {
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Excepción de SQL", JOptionPane.ERROR_MESSAGE);
        }
    }

}

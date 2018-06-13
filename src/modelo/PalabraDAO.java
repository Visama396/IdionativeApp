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

    private int typeToIndex(String tipo) {
        int i = -1;

        switch (tipo) {
            case "N":
                i = 0;
                break;
            case "A":
                i = 1;
                break;
            case "Adv":
                i = 2;
                break;
            case "V":
                i = 3;
                break;
            case "Pron":
                i = 4;
                break;
            case "Prep":
                i = 5;
                break;
            case "Art":
                i = 6;
                break;
            case "Conj":
                i = 7;
                break;
            case "Interj":
                i = 8;
                break;
            case "NaAdj":
                i = 9;
                break;
            case "IAdj":
                i = 10;
                break;
            case "RV":
                i = 11;
                break;
            case "IV":
                i = 12;
                break;
            case "VT":
                i = 13;
                break;
            case "VI":
                i = 14;
                break;
            case "GV":
                i = 15;
                break;
            case "IcV":
                i = 16;
                break;
            case "SpV":
                i = 17;
                break;
            case "Part":
                i = 18;
                break;
            case "Det":
                i = 19;
                break;
        }

        return i;
    }

    private String indexToType(int i) {
        String tipo = "";

        switch (i) {
            case 0:
                // Es sustantivo
                tipo = "N";
                break;
            case 1:
                // Es adjetivo
                tipo = "A";
                break;
            case 2:
                // Es adverbio
                tipo = "Adv";
                break;
            case 3:
                // Es verbo
                tipo = "V";
                break;
            case 4:
                // Es pronombre
                tipo = "Pron";
                break;
            case 5:
                // Es preposición
                tipo = "Prep";
                break;
            case 6:
                // Es artículo
                tipo = "Art";
                break;
            case 7:
                // Es conjunción
                tipo = "Conj";
                break;
            case 8:
                // Es interjección
                tipo = "Interj";
                break;
            case 9:
                // Es adjetivo-na
                tipo = "NaAdj";
                break;
            case 10:
                // Es adjetivo-i
                tipo = "IAdj";
                break;
            case 11:
                tipo = "RV";
                break;
            case 12:
                tipo = "IV";
                break;
            case 13:
                tipo = "VT";
                break;
            case 14:
                tipo = "VI";
                break;
            case 15:
                tipo = "GV";
                break;
            case 16:
                tipo = "IcV";
                break;
            case 17:
                tipo = "SpV";
                break;
            case 18:
                tipo = "Part";
                break;
            case 19:
                tipo = "Det";
                break;
        }

        return tipo;
    }

    public int siguienteID() {
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

        return max+1;
    }

    public int[] buscarTipos (int id) {
        Connection conn = con.createConnection();
        ResultSet rs = null;
        int[] indiceTipos = null;

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT tipo FROM tipos_palabras WHERE idtipo_palabra = ?");
            ps.setInt(1, id);
            ArrayList<Integer> indices = new ArrayList<Integer>();
            rs = ps.executeQuery();
            while (rs.next()) {
                indices.add(typeToIndex(rs.getString(1)));
            }
            int in = 0;
            indiceTipos = new int[indices.size()];
            for (int i: indices) {
                indiceTipos[in] = i;
            }
        } catch (SQLException e) {

        }

        return indiceTipos;
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
            PreparedStatement pstipos = conn.prepareStatement("DELETE FROM TIPOS_PALABRAS WHERE IDTIPO_PALABRA = ?");
            pstipos.setInt(1, id);
            pstipos.executeUpdate();

            PreparedStatement pssig = conn.prepareStatement("DELETE FROM SIGNIFICADO_EJEMPLOS WHERE ID_SIG = ?");
            pssig.setInt(1, id);
            pssig.executeUpdate();

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

    public int actualizarPalabra(int id, String eng, String esp, String jpn, String kana, String deu, String ptr, int[] tipo) {
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

            PreparedStatement pstipos1 = conn.prepareStatement("DELETE FROM TIPOS_PALABRAS WHERE IDTIPO_PALABRA = ?");
            pstipos1.setInt(1, id);
            pstipos1.executeUpdate();

            PreparedStatement pstipos2 = conn.prepareStatement("INSERT INTO TIPOS_PALABRAS VALUES(?, ?)");
            for (int i:tipo) {
                pstipos2.clearParameters();
                pstipos2.setInt(1, id);
                pstipos2.setString(2, indexToType(i));
                pstipos2.executeQuery();
            }
        } catch (SQLException e) {
            con.closeConnection(conn);
        }

        return result;
    }

    public int insertarPalabra(String eng, String esp, String jpn, String kana, String deu, String ptr, int[] tipos) {
        Connection conn = con.createConnection();
        PreparedStatement ps=null;
        int result = 0;
        int idnuevo = siguienteID();

        try {

            ps = conn.prepareStatement("INSERT INTO palabras (id_palabra, eng, esp, jpn, kana, deu, ptr) VALUES(?, INITCAP(?), INITCAP(?), INITCAP(?), INITCAP(?), INITCAP(?), INITCAP(?))");
            ps.setInt(1, idnuevo);
            ps.setString(2, eng);
            ps.setString(3, esp);
            ps.setString(4, jpn);
            ps.setString(5, kana);
            ps.setString(6, deu);
            ps.setString(7, ptr);

            result = ps.executeUpdate();

            PreparedStatement pstipos = conn.prepareStatement("INSERT INTO tipos_palabras VALUES (?, ?)");
            for (int i:tipos) {
                pstipos.clearParameters();
                pstipos.setInt(1, idnuevo);
                pstipos.setString(2, indexToType(i));
                pstipos.executeQuery();
            }

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

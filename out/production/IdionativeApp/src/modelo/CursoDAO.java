package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CursoDAO {

    Conexion con;

    public CursoDAO() {
        con = new Conexion();
    }

    public ArrayList<Curso> buscarCursos(String lang, ArrayList<String> learn_lang) {
        ArrayList<Curso> cursos = new ArrayList<>();
        Connection conn = con.createConnection();
        ResultSet rs = null;

        try {
            String learnlangs = "";
            for (String s : learn_lang) {
                learnlangs += "'"+s+"',";
            }
            String sql = "SELECT * FROM CURSOS WHERE IDIOMA_CURSO = ? AND IDIOMA_APRENDER IN ("+(learnlangs.substring(0, learnlangs.length()-1))+")";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, lang);
            rs = ps.executeQuery();
            while (rs.next()) {
                cursos.add(new Curso(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cursos;
    }

    public ArrayList<Leccion> buscarLecciones(Curso c) {
        ArrayList<Leccion> lecciones = new ArrayList<>();
        Connection conn = con.createConnection();
        ResultSet rs = null;

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM LECCIONES WHERE ID_CURSO_LECC = ? AND IDIOMA_LECC = ?");
            ps.setInt(1, c.getId_curso());
            ps.setString(2, c.getIdioma_curso());
            rs = ps.executeQuery();
            while (rs.next()) {
                lecciones.add(new Leccion(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lecciones;
    }
}

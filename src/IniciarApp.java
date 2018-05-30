import controlador.ControladorLogin;
import modelo.UsuarioDAO;
import vista.InicioSesion;

import javax.swing.*;
import java.awt.*;

/**
 * <h1>Aplicación de Idionative</h1>
 * <h2>Swing</h2>
 * <p>
 * Usos:
 * Mantenimiento del diccionario de la base de datos, de palabras en sus idiomas.
 * Añadir, modificar o eliminar palabras.
 * Posibilidad de ver los usuarios registrados.
 * Crear o eliminar cursos de la base de datos.
 * Añadir, modificar o eliminar lecciones de los cursos mediante un editor HTML.
 * </p>
 *
 * @author Víctor Sánchez Martínez
 * @version 0.0.1
 * @since 2018-05-27
 */

public class IniciarApp {

    public static void main(String[] args) {

        /**
         * Código para poner el LookAndFeel in Nimbus
         */

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        /**
         * Primera ventana de Inicio de sesión
         */

        InicioSesion view = new InicioSesion("Iniciar Sesion");
        UsuarioDAO model = new UsuarioDAO();
        ControladorLogin controlador = new ControladorLogin(view, model, "esp", "");
        view.setVisible(true);
    }

}

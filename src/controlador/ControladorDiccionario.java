package controlador;

import modelo.PalabraDAO;
import vista.Diccionario;

import javax.swing.*;
import java.awt.event.*;

public class ControladorDiccionario implements ActionListener, FocusListener, WindowListener {

    private Diccionario vista;
    private PalabraDAO modelo;

    public ControladorDiccionario(Diccionario vista, PalabraDAO modelo) {
        this.vista = vista;
        this.modelo = modelo;

        this.vista.idWordLabel.setText("ID Palabra:");
        this.vista.searchWordButton.setText("Buscar palabra");
        this.vista.searchWordButton.addActionListener(this);
        this.vista.searchWordButton.setActionCommand("SEARCHWORD");
        this.vista.loadDictionaryButton.setText("Cargar diccionario");
        this.vista.loadDictionaryButton.addActionListener(this);
        this.vista.loadDictionaryButton.setActionCommand("LOADDICT");
        this.vista.moreActionsButton.setText("+ Más");
        this.vista.moreActionsButton.setActionCommand("MOREACTIONS");
        this.vista.moreActionsButton.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        this.vista.moreActionsButton.setContentAreaFilled(false);
        this.vista.moreActionsButton.addActionListener(this);
        this.vista.moreActionsLabel.setText("Acciones:");
        this.vista.idwordLabel2.setText("ID");
        this.vista.englishLabel.setText("Inglés");
        this.vista.spanishLabel.setText("Español");
        this.vista.japaneseLabel.setText("Japonés");
        this.vista.kanaLabel.setText("Kana");
        this.vista.germanLabel.setText("Alemán");
        this.vista.portugueseLabel.setText("Portugués");
        this.vista.koreanLabel.setText("Coreano");
        this.vista.wordTypeLabel.setText("Tipo de palabra");
        this.vista.insertButton.setText("Insertar");
        this.vista.updateButton.setText("Actualizar");
        this.vista.removeButton.setText("Eliminar");
        this.vista.acceptButton.setText("Aceptar");
        this.vista.closeButton.setText("Cerrar");
        this.vista.closeButton.addActionListener(this);
        this.vista.closeButton.setActionCommand("HIDEMOREACTIONS");

        this.vista.addWindowListener(this);

        this.vista.pack();
        this.vista.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton boton = (JButton) e.getSource();

            switch (boton.getActionCommand()) {
                case "SEARCHWORD":
                    break;
                case "LOADDICT":
                    break;
                case "MOREACTIONS":
                    this.vista.moreActionsPane.setVisible(true);
                    this.vista.pack();
                    this.vista.setLocationRelativeTo(null);
                    break;
                case "HIDEMOREACTIONS":
                    this.vista.moreActionsPane.setVisible(false);
                    this.vista.pack();
                    this.vista.setLocationRelativeTo(null);
                    break;
            }
        }
    }

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        int option = JOptionPane.showConfirmDialog(null, "¿Quieres cerrar la aplicación?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (option == 0) {
            System.exit(0);
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}

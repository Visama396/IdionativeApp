package controlador;

import modelo.Palabra;
import modelo.PalabraDAO;
import vista.Diccionario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ControladorDiccionario implements ActionListener, FocusListener, WindowListener {

    private Diccionario vista;
    private PalabraDAO modelo;
    private Palabra p;
    private DefaultTableModel defaultTableModel = new DefaultTableModel(
            new String[] {
                    "ID",
                    "Inglés",
                    "Español",
                    "Japonés",
                    "Kana",
                    "Alemán",
                    "Portugués"},
            0);

    public ControladorDiccionario(Diccionario vista, PalabraDAO modelo) {
        this.vista = vista;
        this.modelo = modelo;

        this.vista.idWordLabel.setText("ID Palabra:");
        this.vista.idWordField.setToolTipText("Valor numérico");
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
        this.vista.idWordLabel2.setText("ID");
        this.vista.englishLabel.setText("Inglés");
        this.vista.spanishLabel.setText("Español");
        this.vista.japaneseLabel.setText("Japonés");
        this.vista.kanaLabel.setText("Kana");
        this.vista.germanLabel.setText("Alemán");
        this.vista.portugueseLabel.setText("Portugués");
        this.vista.wordTypeLabel.setText("Tipo de palabra");
        this.vista.insertButton.setText("Insertar");
        this.vista.updateButton.setText("Actualizar");
        this.vista.removeButton.setText("Eliminar");
        this.vista.confirmButton.setText("Continuar");
        this.vista.confirmButton.setActionCommand("QUERY");
        this.vista.confirmButton.addActionListener(this);
        this.vista.idWordField2.addFocusListener(this);
        this.vista.addWindowListener(this);

        this.vista.jsDicc.setVisible(false);
        this.vista.bottomPane.setVisible(false);

        this.vista.pack();
        this.vista.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton boton = (JButton) e.getSource();

            switch (boton.getActionCommand()) {
                case "SEARCHWORD":
                    if (this.vista.jsDicc.isVisible()) {
                        this.vista.jsDicc.setVisible(false);
                    }
                    if (defaultTableModel.getRowCount() > 0) {
                        defaultTableModel.setRowCount(0);
                    }
                    try {
                        p = modelo.buscarPalabra(Integer.parseInt(this.vista.idWordField.getText()));
                        defaultTableModel.addRow(
                                new String[] {
                                        p.getId_word()+"",
                                        p.getEng(),
                                        p.getEsp(),
                                        p.getJpn(),
                                        p.getKana(),
                                        p.getDeu(),
                                        p.getPtr()}
                        );
                        this.vista.dictionaryTable.setModel(defaultTableModel);
                        this.vista.setVisible(false);
                        this.vista.jsDicc.setVisible(true);
                        this.vista.pack();
                        this.vista.setLocationRelativeTo(null);
                        this.vista.setVisible(true);

                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(null, "Por favor, introduzca un id numérico válido.", "Diccionario: NumberFormatException", JOptionPane.ERROR_MESSAGE);
                    } catch (NullPointerException npe) {
                        JOptionPane.showMessageDialog(null, "No se ha podido encontrar esa palabra.", "Diccionario: NullPointerException", JOptionPane.ERROR_MESSAGE);
                    }

                    break;
                case "LOADDICT":
                    if (this.vista.jsDicc.isVisible()) {
                        this.vista.jsDicc.setVisible(false);
                    }
                    if (defaultTableModel.getRowCount() > 0) {
                        defaultTableModel.setRowCount(0);
                    }
                    ArrayList<Palabra> dict = modelo.cargarDiccionario();
                    for (Palabra pal:dict) {
                        defaultTableModel.addRow(new String[] {
                                pal.getId_word()+"",
                                pal.getEng(),
                                pal.getEsp(),
                                pal.getJpn(),
                                pal.getKana(),
                                pal.getDeu(),
                                pal.getPtr()
                        });
                    }
                    this.vista.dictionaryTable.setModel(defaultTableModel);
                    this.vista.setVisible(false);
                    this.vista.jsDicc.setVisible(true);
                    this.vista.pack();
                    this.vista.setLocationRelativeTo(null);
                    this.vista.setVisible(true);
                    break;
                case "MOREACTIONS":
                    this.vista.bottomPane.setVisible(true);
                    this.vista.moreActionsButton.setText("- Menos");
                    this.vista.moreActionsButton.setActionCommand("HIDEMOREACTIONS");
                    this.vista.pack();
                    this.vista.setLocationRelativeTo(null);
                    break;
                case "HIDEMOREACTIONS":
                    this.vista.bottomPane.setVisible(false);
                    this.vista.moreActionsButton.setText("+ Más");
                    this.vista.moreActionsButton.setActionCommand("MOREACTIONS");
                    this.vista.pack();
                    this.vista.setLocationRelativeTo(null);
                    break;
                case "QUERY":
                    if(this.vista.insertButton.isSelected()) {
                        int result = this.modelo.insertarPalabra(
                                Integer.parseInt(this.vista.idWordField2.getText()),
                                this.vista.englishField.getText(),
                                this.vista.spanishField.getText(),
                                this.vista.japaneseField.getText(),
                                this.vista.kanaField.getText(),
                                this.vista.germanField.getText(),
                                this.vista.portugueseField.getText()
                        );
                        if(result==1) {
                            this.vista.confirmLabel.setForeground(Color.GREEN);
                            this.vista.confirmLabel.setText("Se ha insertado correctamente");
                        } else {
                            this.vista.confirmLabel.setForeground(Color.RED);
                            this.vista.confirmLabel.setText("No se ha insertado la palabra");
                        }
                        this.vista.idWordField2.setText("");
                        this.vista.englishField.setText("");
                        this.vista.spanishField.setText("");
                        this.vista.japaneseField.setText("");
                        this.vista.kanaField.setText("");
                        this.vista.germanField.setText("");
                        this.vista.portugueseField.setText("");
                    } else if(this.vista.updateButton.isSelected()) {
                        // Si es un update
                    } else {
                        // Si es un delete
                    }
                    break;
            }
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        this.vista.englishField.setText("");
        this.vista.spanishField.setText("");
        this.vista.japaneseField.setText("");
        this.vista.kanaField.setText("");
        this.vista.germanField.setText("");
        this.vista.portugueseField.setText("");
        this.vista.confirmLabel.setText("");
        this.vista.confirmLabel.setForeground(Color.BLACK);
    }

    @Override
    public void focusLost(FocusEvent e) {
        try {
            p = this.modelo.buscarPalabra(Integer.parseInt(this.vista.idWordField2.getText()));
            if (p != null) {
                this.vista.englishField.setText(p.getEng());
                this.vista.spanishField.setText(p.getEsp());
                this.vista.japaneseField.setText(p.getJpn());
                this.vista.kanaField.setText(p.getKana());
                this.vista.germanField.setText(p.getDeu());
                this.vista.portugueseField.setText(p.getPtr());
                this.vista.confirmLabel.setForeground(Color.GREEN);
                this.vista.confirmLabel.setText("Palabra encontrada");
            } else {
                this.vista.confirmLabel.setForeground(Color.RED);
                this.vista.confirmLabel.setText("Palabra no encontrada");
            }
        } catch (NumberFormatException nfe) {
            this.vista.confirmLabel.setForeground(Color.RED);
            this.vista.confirmLabel.setText("Id no válido");
        }
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

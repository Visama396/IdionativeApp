package controlador;

import modelo.Palabra;
import modelo.PalabraDAO;
import vista.Diccionario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class ControladorDiccionario implements ActionListener, FocusListener, WindowListener, MouseListener {

    private Diccionario vista;
    private PalabraDAO modelo;
    private Palabra p;
    private String lang;
    private Locale loc;
    private ResourceBundle rb;
    private String email;

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

    public ControladorDiccionario(Diccionario vista, PalabraDAO modelo, String lang, String email) {
        this.vista = vista;
        this.modelo = modelo;
        this.email = email;
        this.lang = lang;
        this.loc = new Locale(this.lang);
        this.rb = ResourceBundle.getBundle("locales.iniciosesion.locale", this.loc);

        Font fuente = new Font("Arial Unicode MS", Font.PLAIN, 15);

        this.vista.idWordLabel.setText("ID Palabra:");
        this.vista.idWordLabel.setFont(fuente);
        this.vista.idWordField.setToolTipText("Valor numérico");
        this.vista.idWordField.setFont(fuente);
        this.vista.wordLabel.setText("Palabra: ");
        this.vista.wordLabel.setFont(fuente);
        this.vista.wordTextField.setFont(fuente);
        this.vista.searchWordButton.setText("Buscar palabra");
        this.vista.searchWordButton.setFont(fuente);
        this.vista.searchWordButton.addActionListener(this);
        this.vista.searchWordButton.setActionCommand("SEARCHWORD");
        this.vista.loadDictionaryButton.setText("Cargar diccionario");
        this.vista.loadDictionaryButton.setFont(fuente);
        this.vista.loadDictionaryButton.addActionListener(this);
        this.vista.loadDictionaryButton.setActionCommand("LOADDICT");
        this.vista.moreActionsButton.setText("+ Más");
        this.vista.moreActionsButton.setFont(fuente);
        this.vista.moreActionsButton.setActionCommand("MOREACTIONS");
        this.vista.moreActionsButton.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        this.vista.moreActionsButton.setContentAreaFilled(false);
        this.vista.moreActionsButton.addActionListener(this);
        this.vista.moreActionsLabel.setText("Acciones:");
        this.vista.moreActionsLabel.setFont(fuente);
        this.vista.idWordLabel2.setText("ID");
        this.vista.idWordLabel2.setFont(fuente);
        this.vista.englishLabel.setText("Inglés");
        this.vista.englishLabel.setFont(fuente);
        this.vista.spanishLabel.setText("Español");
        this.vista.spanishLabel.setFont(fuente);
        this.vista.japaneseLabel.setText("Japonés");
        this.vista.japaneseLabel.setFont(fuente);
        this.vista.kanaLabel.setText("Kana");
        this.vista.kanaLabel.setFont(fuente);
        this.vista.germanLabel.setText("Alemán");
        this.vista.germanLabel.setFont(fuente);
        this.vista.portugueseLabel.setText("Portugués");
        this.vista.portugueseLabel.setFont(fuente);
        this.vista.wordTypeLabel.setText("Tipo de palabra");
        this.vista.wordTypeLabel.setFont(fuente);
        this.vista.insertButton.setText("Insertar");
        this.vista.insertButton.setActionCommand("INS");
        this.vista.insertButton.setFont(fuente);
        this.vista.insertButton.addFocusListener(this);
        this.vista.updateButton.setText("Actualizar");
        this.vista.updateButton.setActionCommand("UPD");
        this.vista.updateButton.setFont(fuente);
        this.vista.updateButton.addFocusListener(this);
        this.vista.removeButton.setText("Eliminar");
        this.vista.removeButton.setActionCommand("REM");
        this.vista.removeButton.setFont(fuente);
        this.vista.removeButton.addFocusListener(this);
        this.vista.confirmButton.setText("Continuar");
        this.vista.confirmButton.setFont(fuente);
        this.vista.confirmButton.setActionCommand("QUERY");
        this.vista.confirmButton.addActionListener(this);
        this.vista.confirmButton.setEnabled(false);
        this.vista.cleanButton.setText("Limpiar");
        this.vista.cleanButton.setFont(fuente);
        this.vista.cleanButton.setActionCommand("CLEAN");
        this.vista.cleanButton.addActionListener(this);
        this.vista.confirmLabel.setFont(fuente);
        this.vista.idWordField2.addFocusListener(this);
        this.vista.dictionaryTable.setFont(new Font("Arial Unicode MS", Font.PLAIN, 13));
        this.vista.dictionaryTable.setAutoCreateRowSorter(true);
        this.vista.addWindowListener(this);

        this.vista.jsDicc.setVisible(false);
        this.vista.bottomPane.setVisible(false);

        this.vista.englishField.addFocusListener(this);
        this.vista.englishField.setFont(fuente);
        this.vista.spanishField.addFocusListener(this);
        this.vista.spanishField.setFont(fuente);
        this.vista.japaneseField.addFocusListener(this);
        this.vista.japaneseField.setFont(fuente);
        this.vista.kanaField.addFocusListener(this);
        this.vista.kanaField.setFont(fuente);
        this.vista.germanField.addFocusListener(this);
        this.vista.germanField.setFont(fuente);
        this.vista.portugueseField.addFocusListener(this);
        this.vista.portugueseField.setFont(fuente);

        this.vista.pack();
        this.vista.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton boton = (JButton) e.getSource();

            switch (boton.getActionCommand()) {
                case "SEARCHWORD":
                    if (this.vista.idWordField.getText().length() >= 1) {
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
                            p = modelo.buscarPalabra(this.vista.idWordField.getText());
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
                        } catch (NullPointerException npe) {
                            JOptionPane.showMessageDialog(null, "No se ha podido encontrar esa palabra.", "Diccionario: NullPointerException", JOptionPane.ERROR_MESSAGE);
                            this.vista.setVisible(false);
                            this.vista.jsDicc.setVisible(false);
                            this.vista.pack();
                            this.vista.setLocationRelativeTo(null);
                            this.vista.setVisible(true);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No se ha introducido ID o palabra válida");
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
                        if (this.vista.englishField.getText().length()>1 || this.vista.spanishField.getText().length() > 1 || this.vista.japaneseField.getText().length() > 1 || this.vista.kanaField.getText().length() > 1 || this.vista.germanField.getText().length() > 1 || this.vista.portugueseField.getText().length() > 1) {
                            int result = this.modelo.insertarPalabra(
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
                        } else {
                            this.vista.confirmLabel.setForeground(Color.RED);
                            this.vista.confirmLabel.setText("No ha introducido ningún valor");
                        }
                    } else if(this.vista.updateButton.isSelected()) {
                        int result = this.modelo.actualizarPalabra(
                                Integer.parseInt(this.vista.idWordField2.getText()),
                                this.vista.englishField.getText(),
                                this.vista.spanishField.getText(),
                                this.vista.japaneseField.getText(),
                                this.vista.kanaField.getText(),
                                this.vista.germanField.getText(),
                                this.vista.portugueseField.getText()
                        );

                        if (result == 1) {
                            this.vista.confirmLabel.setForeground(Color.GREEN);
                            this.vista.confirmLabel.setText("Se ha actualizado correctamente");
                        }
                    } else {
                        int opt = JOptionPane.showConfirmDialog(null, "¿Quieres eliminar esta palabra?", "Confirmar", JOptionPane.YES_NO_OPTION);
                        if (opt == 0) {
                            int result = this.modelo.eliminarPalabra(Integer.parseInt(this.vista.idWordField2.getText()));
                            if (result == 1) {
                                this.vista.confirmLabel.setForeground(Color.GREEN);
                                this.vista.confirmLabel.setText("");
                            }
                        }
                    }
                    break;
                case "CLEAN":
                    this.vista.idWordField2.setText("");
                    this.vista.englishField.setText("");
                    this.vista.spanishField.setText("");
                    this.vista.japaneseField.setText("");
                    this.vista.kanaField.setText("");
                    this.vista.germanField.setText("");
                    this.vista.portugueseField.setText("");
                    this.vista.confirmLabel.setText("");
                    this.vista.confirmLabel.setForeground(Color.BLACK);
                    break;
            }
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() instanceof JRadioButton) {
            JRadioButton buton = (JRadioButton) e.getSource();
            switch (buton.getActionCommand()) {
                case "INS":
                    this.vista.confirmButton.setEnabled(false);
                    this.vista.idWordField2.setEditable(false);
                    break;
                case "UPD":
                    this.vista.confirmButton.setEnabled(false);
                    this.vista.idWordField2.setEditable(true);
                    break;
                case "REM":
                    this.vista.confirmButton.setEnabled(false);
                    this.vista.idWordField2.setEditable(true);
                    break;
            }
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource()==this.vista.englishField) {
            if (this.vista.insertButton.isSelected()) {
                this.vista.confirmButton.setEnabled(true);
            } else if (this.vista.updateButton.isSelected()) {

            } else if (this.vista.removeButton.isSelected()) {

            }
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

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

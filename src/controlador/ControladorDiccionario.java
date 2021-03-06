package controlador;

import modelo.*;
import vista.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class ControladorDiccionario implements ActionListener, FocusListener, WindowListener, MouseListener, KeyListener {

    private Diccionario vista;
    private PalabraDAO modelo;
    private Palabra p;
    private String lang;
    private Locale loc;
    private ResourceBundle rb;
    private String email;
    private UsuarioDAO modeloUser;
    private PalabraSigEj showMoreInfo;
    private SignificadoEjemploDAO sigmodel = new SignificadoEjemploDAO();

    private DefaultTableModel defaultTableModel;

    public ControladorDiccionario(Diccionario vista, PalabraDAO modelo, String lang, String email) {
        this.vista = vista;
        this.modelo = modelo;
        this.email = email;
        this.lang = lang;
        modeloUser = new UsuarioDAO();
        this.loc = new Locale(this.lang);
        this.rb = ResourceBundle.getBundle("locales.diccionario.locale", this.loc);

        Font fuente = new Font("Arial Unicode MS", Font.PLAIN, 15);

        defaultTableModel = new DefaultTableModel(
                new String[] {
                        "ID",
                        rb.getString("eng"),
                        rb.getString("esp"),
                        rb.getString("jpn"),
                        rb.getString("kan"),
                        rb.getString("deu"),
                        rb.getString("ptr")},
                0);

        this.vista.wordLabel.setText(rb.getString("word"));
        this.vista.wordLabel.setFont(fuente);
        this.vista.wordField.setToolTipText(rb.getString("wordtooltip"));
        this.vista.wordField.setFont(fuente);
        this.vista.searchWordButton.setText(rb.getString("search"));
        this.vista.searchWordButton.setFont(fuente);
        this.vista.searchWordButton.addActionListener(this);
        this.vista.searchWordButton.setActionCommand("SEARCHWORD");
        this.vista.loadDictionaryButton.setText(rb.getString("load"));
        this.vista.loadDictionaryButton.setFont(fuente);
        this.vista.loadDictionaryButton.addActionListener(this);
        this.vista.loadDictionaryButton.setActionCommand("LOADDICT");
        this.vista.moreActionsButton.setText(rb.getString("more"));
        this.vista.moreActionsButton.setFont(fuente);
        this.vista.moreActionsButton.setActionCommand("MOREACTIONS");
        this.vista.moreActionsButton.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        this.vista.moreActionsButton.setContentAreaFilled(false);
        this.vista.moreActionsButton.addActionListener(this);
        this.vista.moreActionsLabel.setText(rb.getString("actions"));
        this.vista.moreActionsLabel.setFont(fuente);
        if (modeloUser.esAdmin(this.email) || modeloUser.esProfesor(this.email)) {
            this.vista.moreActionsButton.setVisible(true);
        } else {
            this.vista.moreActionsButton.setVisible(false);
        }
        this.vista.idWordLabel2.setText("ID");
        this.vista.idWordLabel2.setFont(fuente);
        this.vista.englishLabel.setText(rb.getString("eng"));
        this.vista.englishLabel.setFont(fuente);
        this.vista.spanishLabel.setText(rb.getString("esp"));
        this.vista.spanishLabel.setFont(fuente);
        this.vista.japaneseLabel.setText(rb.getString("jpn"));
        this.vista.japaneseLabel.setFont(fuente);
        this.vista.kanaLabel.setText(rb.getString("kan"));
        this.vista.kanaLabel.setFont(fuente);
        this.vista.germanLabel.setText(rb.getString("deu"));
        this.vista.germanLabel.setFont(fuente);
        this.vista.portugueseLabel.setText(rb.getString("ptr"));
        this.vista.portugueseLabel.setFont(fuente);
        this.vista.wordTypeLabel.setText(rb.getString("type"));
        this.vista.wordTypeLabel.setFont(fuente);
        this.vista.insertButton.setText(rb.getString("insert"));
        this.vista.insertButton.setActionCommand("INS");
        this.vista.insertButton.setFont(fuente);
        this.vista.insertButton.addFocusListener(this);
        this.vista.updateButton.setText(rb.getString("update"));
        this.vista.updateButton.setActionCommand("UPD");
        this.vista.updateButton.setFont(fuente);
        this.vista.updateButton.addFocusListener(this);
        this.vista.removeButton.setText(rb.getString("delete"));
        this.vista.removeButton.setActionCommand("REM");
        this.vista.removeButton.setFont(fuente);
        this.vista.removeButton.addFocusListener(this);
        this.vista.confirmButton.setText(rb.getString("continue"));
        this.vista.confirmButton.setFont(fuente);
        this.vista.confirmButton.setActionCommand("QUERY");
        this.vista.confirmButton.addActionListener(this);
        this.vista.confirmButton.setEnabled(false);
        this.vista.cleanButton.setText(rb.getString("clean"));
        this.vista.cleanButton.setFont(fuente);
        this.vista.cleanButton.setActionCommand("CLEAN");
        this.vista.cleanButton.addActionListener(this);
        this.vista.confirmLabel.setFont(fuente);
        this.vista.idWordField2.addKeyListener(this);
        this.vista.idWordField2.setFont(fuente);
        this.vista.idWordField2.setEnabled(false);
        this.vista.dictionaryTable.setRowHeight(30);
        this.vista.dictionaryTable.setFont(fuente);
        this.vista.dictionaryTable.setAutoCreateRowSorter(false);
        this.vista.dictionaryTable.addMouseListener(this);
        this.vista.addWindowListener(this);

        this.vista.jsDictionary.setVisible(false);
        this.vista.bottomPane.setVisible(false);

        this.vista.englishField.addKeyListener(this);
        this.vista.englishField.setFont(fuente);
        this.vista.spanishField.addKeyListener(this);
        this.vista.spanishField.setFont(fuente);
        this.vista.japaneseField.addKeyListener(this);
        this.vista.japaneseField.setFont(fuente);
        this.vista.kanaField.addKeyListener(this);
        this.vista.kanaField.setFont(fuente);
        this.vista.germanField.addKeyListener(this);
        this.vista.germanField.setFont(fuente);
        this.vista.portugueseField.addKeyListener(this);
        this.vista.portugueseField.setFont(fuente);
        this.vista.wordTypeList.setVisibleRowCount(3);
        this.vista.wordTypeList.setFont(fuente);
        rellenarListaTipos();

        this.vista.mainMenu.setText(rb.getString("start"));
        this.vista.mainMenu.setFont(fuente);
        this.vista.languagesMenu.setText(rb.getString("langs"));
        this.vista.languagesMenu.setFont(fuente);
        this.vista.spanishItem.setText(rb.getString("esp"));
        this.vista.spanishItem.setFont(fuente);
        this.vista.spanishItem.addActionListener(this);
        this.vista.spanishItem.setActionCommand("ESP");
        this.vista.englishItem.setText(rb.getString("eng"));
        this.vista.englishItem.setFont(fuente);
        this.vista.englishItem.addActionListener(this);
        this.vista.englishItem.setActionCommand("ENG");
        this.vista.japaneseItem.setText(rb.getString("jpn"));
        this.vista.japaneseItem.setFont(fuente);
        this.vista.japaneseItem.addActionListener(this);
        this.vista.japaneseItem.setActionCommand("JPN");
        this.vista.germanItem.setText(rb.getString("deu"));
        this.vista.germanItem.setFont(fuente);
        this.vista.germanItem.addActionListener(this);
        this.vista.germanItem.setActionCommand("DEU");
        this.vista.closeItem.setText(rb.getString("close"));
        this.vista.closeItem.setFont(fuente);
        this.vista.closeItem.addActionListener(this);
        this.vista.closeItem.setActionCommand("CLOSE");
        this.vista.cursos.setText(rb.getString("courses"));
        this.vista.cursos.setFont(fuente);
        this.vista.cursos.addActionListener(this);
        this.vista.cursos.setActionCommand("COURSES");
        this.vista.userMenu.setText(rb.getString("welcome") + " " + modeloUser.obtenerNombreUsuario(this.email));
        this.vista.userMenu.setFont(fuente);
        this.vista.userSettingsItem.setText(rb.getString("conf"));
        this.vista.userSettingsItem.setFont(fuente);
        this.vista.userSettingsItem.addActionListener(this);
        this.vista.userSettingsItem.setActionCommand("SETTINGS");
        this.vista.logoutItem.setText(rb.getString("logout"));
        this.vista.logoutItem.setFont(fuente);
        this.vista.logoutItem.addActionListener(this);
        this.vista.logoutItem.setActionCommand("LOGOUT");
        this.vista.deleteUserItem.setText(rb.getString("deluser"));
        this.vista.deleteUserItem.setFont(fuente);
        this.vista.deleteUserItem.addActionListener(this);
        this.vista.deleteUserItem.setActionCommand("DELUSER");

        this.vista.pack();
        this.vista.setLocationRelativeTo(null);
    }

    private void rellenarListaTipos() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addElement(rb.getString("noun"));
        listModel.addElement(rb.getString("adjective"));
        listModel.addElement(rb.getString("adverb"));
        listModel.addElement(rb.getString("verb"));
        listModel.addElement(rb.getString("pronoun"));
        listModel.addElement(rb.getString("preposition"));
        listModel.addElement(rb.getString("article"));
        listModel.addElement(rb.getString("conjunction"));
        listModel.addElement(rb.getString("interjection"));
        listModel.addElement(rb.getString("nadjective"));
        listModel.addElement(rb.getString("iadjective"));
        listModel.addElement(rb.getString("regverb"));
        listModel.addElement(rb.getString("irregverb"));
        listModel.addElement(rb.getString("transverb"));
        listModel.addElement(rb.getString("intransverb"));
        listModel.addElement(rb.getString("godanverb"));
        listModel.addElement(rb.getString("ichidanverb"));
        listModel.addElement(rb.getString("speverb"));
        listModel.addElement(rb.getString("particle"));
        listModel.addElement(rb.getString("deter"));
        this.vista.wordTypeList.setModel(listModel);
    }

    private void fillFields(Object o) {
        try {
            try {
                p = this.modelo.buscarPalabra(Integer.parseInt(((JTextField) o).getText()));
                this.vista.idWordField2.setText(p.getId_word()+"");
                this.vista.englishField.setText(p.getEng());
                this.vista.spanishField.setText(p.getEsp());
                this.vista.japaneseField.setText(p.getJpn());
                this.vista.kanaField.setText(p.getKana());
                this.vista.germanField.setText(p.getDeu());
                this.vista.portugueseField.setText(p.getPtr());
                this.vista.wordTypeList.setSelectedIndices(this.modelo.buscarTipos(Integer.parseInt(((JTextField) o).getText())));
            } catch (NumberFormatException nfe) {
                p = this.modelo.buscarPalabra(((JTextField) o).getText());
                this.vista.idWordField2.setText(p.getId_word()+"");
                this.vista.englishField.setText(p.getEng());
                this.vista.spanishField.setText(p.getEsp());
                this.vista.japaneseField.setText(p.getJpn());
                this.vista.kanaField.setText(p.getKana());
                this.vista.germanField.setText(p.getDeu());
                this.vista.portugueseField.setText(p.getPtr());
                this.vista.wordTypeList.setSelectedIndices(this.modelo.buscarTipos(Integer.parseInt(this.vista.idWordField2.getText())));
            }
        } catch (NullPointerException e) {
            this.vista.confirmLabel.setForeground(Color.RED);
            this.vista.confirmLabel.setText(rb.getString("wordnotfound"));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton boton = (JButton) e.getSource();

            switch (boton.getActionCommand()) {
                case "SEARCHWORD":
                    if (this.vista.wordField.getText().length() >= 1) {
                        if (this.vista.jsDictionary.isVisible()) {
                            this.vista.jsDictionary.setVisible(false);
                        }
                        if (defaultTableModel.getRowCount() > 0) {
                            defaultTableModel.setRowCount(0);
                        }
                        try {
                            try {
                                p = modelo.buscarPalabra(Integer.parseInt(this.vista.wordField.getText()));
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
                                this.vista.jsDictionary.setVisible(true);
                                this.vista.pack();
                                this.vista.setLocationRelativeTo(null);
                                this.vista.setVisible(true);

                            } catch (NumberFormatException nfe) {
                                p = modelo.buscarPalabra(this.vista.wordField.getText());
                                defaultTableModel.addRow(new String[] {p.getId_word()+"",p.getEng(),p.getEsp(),p.getJpn(),p.getKana(),p.getDeu(),p.getPtr()});
                                this.vista.dictionaryTable.setModel(defaultTableModel);
                                this.vista.setVisible(false);
                                this.vista.jsDictionary.setVisible(true);
                                this.vista.pack();
                                this.vista.setLocationRelativeTo(null);
                                this.vista.setVisible(true);
                            }
                        }  catch (NullPointerException npe) {
                            JOptionPane.showMessageDialog(null, rb.getString("wordnotfound"), "", JOptionPane.WARNING_MESSAGE);
                            this.vista.setVisible(false);
                            this.vista.jsDictionary.setVisible(false);
                            this.vista.pack();
                            this.vista.setLocationRelativeTo(null);
                            this.vista.setVisible(true);
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, rb.getString("invaliddata"));
                    }

                    break;
                case "LOADDICT":
                    if (this.vista.jsDictionary.isVisible()) {
                        this.vista.jsDictionary.setVisible(false);
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
                    this.vista.jsDictionary.setVisible(true);
                    this.vista.pack();
                    this.vista.setLocationRelativeTo(null);
                    this.vista.setVisible(true);
                    break;
                case "MOREACTIONS":
                    this.vista.bottomPane.setVisible(true);
                    this.vista.moreActionsButton.setText(rb.getString("less"));
                    this.vista.moreActionsButton.setActionCommand("HIDEMOREACTIONS");
                    this.vista.pack();
                    this.vista.setLocationRelativeTo(null);
                    break;
                case "HIDEMOREACTIONS":
                    this.vista.bottomPane.setVisible(false);
                    this.vista.moreActionsButton.setText(rb.getString("more"));
                    this.vista.moreActionsButton.setActionCommand("MOREACTIONS");
                    this.vista.pack();
                    this.vista.setLocationRelativeTo(null);
                    break;
                case "EDIT":
                    showMoreInfo.significadoArea.setEditable(true);
                    showMoreInfo.ejemploArea.setEditable(true);
                    showMoreInfo.continueButton.setText(rb.getString("confirm"));
                    showMoreInfo.continueButton.setActionCommand("CONFIRM");
                    break;
                case "CONFIRM":
                    int id = Integer.parseInt(this.vista.dictionaryTable.getValueAt(this.vista.dictionaryTable.getSelectedRow(), 0)+"");
                    String sig = this.showMoreInfo.significadoArea.getText();
                    String ej = this.showMoreInfo.ejemploArea.getText();
                    if (this.sigmodel.actualizarSignificado(id, sig, ej, columnToCode()) == 1) {
                        JOptionPane.showMessageDialog(null, rb.getString("modifiedcorrect"), "", JOptionPane.INFORMATION_MESSAGE);
                        showMoreInfo.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, rb.getString("modifiederror"), "", JOptionPane.WARNING_MESSAGE);
                    }
                    break;
                case "ADD":
                    int id2 = Integer.parseInt(this.vista.dictionaryTable.getValueAt(this.vista.dictionaryTable.getSelectedRow(), 0)+"");
                    String sig2 = this.showMoreInfo.significadoArea.getText();
                    String ej2 = this.showMoreInfo.ejemploArea.getText();

                    if (this.sigmodel.addSignificado(id2, sig2, ej2, columnToCode()) == 1) {
                        JOptionPane.showMessageDialog(null, rb.getString("addedcorrect"), "", JOptionPane.INFORMATION_MESSAGE);
                        showMoreInfo.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, rb.getString("addederror"), "", JOptionPane.WARNING_MESSAGE);
                    }
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
                                    this.vista.portugueseField.getText(),
                                    this.vista.wordTypeList.getSelectedIndices()
                            );
                            if(result==1) {
                                this.vista.confirmLabel.setForeground(Color.GREEN);
                                this.vista.confirmLabel.setText(rb.getString("inscorrect"));
                            } else {
                                this.vista.confirmLabel.setForeground(Color.RED);
                                this.vista.confirmLabel.setText(rb.getString("insincorrect"));
                            }
                            this.vista.idWordField2.setText("");
                            this.vista.englishField.setText("");
                            this.vista.spanishField.setText("");
                            this.vista.japaneseField.setText("");
                            this.vista.kanaField.setText("");
                            this.vista.germanField.setText("");
                            this.vista.portugueseField.setText("");
                            this.vista.wordTypeList.clearSelection();
                        } else {
                            this.vista.confirmLabel.setForeground(Color.RED);
                            this.vista.confirmLabel.setText(rb.getString("nodata"));
                        }
                    } else if(this.vista.updateButton.isSelected()) {
                        if (this.vista.idWordField2.getText().length()>=1 || this.vista.englishField.getText().length()>1 || this.vista.spanishField.getText().length() > 1 || this.vista.japaneseField.getText().length() > 1 || this.vista.kanaField.getText().length() > 1 || this.vista.germanField.getText().length() > 1 || this.vista.portugueseField.getText().length() > 1) {
                            int result = this.modelo.actualizarPalabra(
                                    Integer.parseInt(this.vista.idWordField2.getText()),
                                    this.vista.englishField.getText(),
                                    this.vista.spanishField.getText(),
                                    this.vista.japaneseField.getText(),
                                    this.vista.kanaField.getText(),
                                    this.vista.germanField.getText(),
                                    this.vista.portugueseField.getText(),
                                    this.vista.wordTypeList.getSelectedIndices()
                            );

                            if (result == 1) {
                                this.vista.confirmLabel.setForeground(Color.GREEN);
                                this.vista.confirmLabel.setText(rb.getString("updcorrect"));
                            } else {
                                this.vista.confirmLabel.setForeground(Color.RED);
                                this.vista.confirmLabel.setText(rb.getString("updincorrect"));
                            }
                            this.vista.idWordField2.setText("");
                            this.vista.englishField.setText("");
                            this.vista.spanishField.setText("");
                            this.vista.japaneseField.setText("");
                            this.vista.kanaField.setText("");
                            this.vista.germanField.setText("");
                            this.vista.portugueseField.setText("");
                            this.vista.wordTypeList.clearSelection();
                        } else {
                            this.vista.confirmLabel.setForeground(Color.RED);
                            this.vista.confirmLabel.setText(rb.getString("nodata"));
                        }
                    } else {
                        if (this.vista.idWordField2.getText().length()>= 1) {
                            int opt = JOptionPane.showConfirmDialog(null, rb.getString("delquestion"), rb.getString("confirm"), JOptionPane.YES_NO_OPTION);
                            if (opt == 0) {
                                int result = this.modelo.eliminarPalabra(Integer.parseInt(this.vista.idWordField2.getText()));
                                if (result == 1) {
                                    this.vista.confirmLabel.setForeground(Color.GREEN);
                                    this.vista.confirmLabel.setText(rb.getString("delcorrect"));
                                }
                            }
                        } else {
                            this.vista.confirmLabel.setForeground(Color.RED);
                            this.vista.confirmLabel.setText(rb.getString("nodata"));
                        }
                    }
                    this.vista.idWordField2.setEditable(true);
                    this.vista.confirmButton.setEnabled(false);
                    break;
                case "CLEAN":
                    this.vista.idWordField2.setText("");
                    if (this.vista.updateButton.isSelected()) this.vista.idWordField2.setEditable(true);
                    this.vista.englishField.setText("");
                    this.vista.spanishField.setText("");
                    this.vista.japaneseField.setText("");
                    this.vista.kanaField.setText("");
                    this.vista.germanField.setText("");
                    this.vista.portugueseField.setText("");
                    this.vista.confirmLabel.setText("");
                    this.vista.confirmLabel.setForeground(Color.BLACK);
                    this.vista.confirmButton.setEnabled(false);
                    this.vista.wordTypeList.clearSelection();
                    break;
            }
        } else if (e.getSource() instanceof JMenuItem) {
            JMenuItem item = (JMenuItem) e.getSource();
            switch (item.getActionCommand()) {
                case "ENG":
                    Diccionario viewen = new Diccionario("Dictionary");
                    PalabraDAO modelen = new PalabraDAO();
                    ControladorDiccionario controlleren = new ControladorDiccionario(viewen, modelen, "en", this.email);
                    this.vista.dispose();
                    viewen.setVisible(true);
                    break;
                case "ESP":
                    Diccionario viewes = new Diccionario("Dictionary");
                    PalabraDAO modeles = new PalabraDAO();
                    ControladorDiccionario controlleres = new ControladorDiccionario(viewes, modeles, "es", this.email);
                    this.vista.dispose();
                    viewes.setVisible(true);
                    break;
                case "JPN":
                    Diccionario viewja = new Diccionario("Dictionary");
                    PalabraDAO modelja = new PalabraDAO();
                    ControladorDiccionario controllerja = new ControladorDiccionario(viewja, modelja, "ja", this.email);
                    this.vista.dispose();
                    viewja.setVisible(true);
                    break;
                case "DEU":
                    Diccionario viewde = new Diccionario("Dictionary");
                    PalabraDAO modelde = new PalabraDAO();
                    ControladorDiccionario controllerde = new ControladorDiccionario(viewde, modelde, "de", this.email);
                    this.vista.dispose();
                    viewde.setVisible(true);
                    break;
                case "CLOSE":
                    int opt = JOptionPane.showConfirmDialog(null,  rb.getString("closequest"), rb.getString("confirm"), JOptionPane.YES_NO_OPTION);
                    if (opt == 0) {
                        System.exit(0);
                    }
                    break;
                case "SETTINGS":
                    Registro view = new Registro(rb.getString("conf"));
                    ControladorConfiguracion configuracion = new ControladorConfiguracion(view, modeloUser, this.lang, this.email);
                    this.vista.dispose();
                    view.setVisible(true);
                    break;
                case "DELUSER":
                    opt = JOptionPane.showConfirmDialog(null, rb.getString("delquest"), rb.getString("confirm"), JOptionPane.YES_NO_OPTION);
                    if (opt == 0) {
                        modeloUser.eliminarUsuario(this.email);
                        InicioSesion inicioSesion = new InicioSesion(rb.getString("login"));
                        UsuarioDAO model = new UsuarioDAO();
                        ControladorLogin controller = new ControladorLogin(inicioSesion, model, this.lang, "");
                        this.vista.dispose();
                        inicioSesion.setVisible(true);
                    }
                    break;
                case "COURSES":
                    CursoDAO cursoDAO = new CursoDAO();
                    String l = IdiomaDAO.appToCode(this.lang);
                    ArrayList<String> aiList = modeloUser.idiomasAprender(this.email);
                    try {
                        if (cursoDAO.buscarCursos(l, aiList).size() > 0) {
                            Cursos cursos = new Cursos("Cursos");
                            ControladorCurso controladorCurso = new ControladorCurso(cursos, cursoDAO, this.lang, this.email);
                            cursos.setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(null, "No hay cursos disponibles", "", JOptionPane.WARNING_MESSAGE);
                        }
                    } catch (StringIndexOutOfBoundsException siob) {
                        JOptionPane.showMessageDialog(null, "No ha seleccionado idiomas que quiera aprender", "", JOptionPane.WARNING_MESSAGE);
                    }

                    break;
                case "LOGOUT":
                    InicioSesion vistaInicio = new InicioSesion(rb.getString("login"));
                    ControladorLogin controladorLogin = new ControladorLogin(vistaInicio, modeloUser, this.lang, "");
                    this.vista.dispose();
                    vistaInicio.setVisible(true);
                    break;
            }
        }
    }

    /**
     * Este método solo se usará cuando se vaya a abrir la vista del significado de una palabra.
     * @return Un String del código del idioma seleccionado
     */
    private String columnToCode() {
        String l = "";
        switch (this.vista.dictionaryTable.getSelectedColumn()) {
            case 1:
                l = "eng";
                break;
            case 2:
                l = "esp";
                break;
            case 3:
            case 4:
                l = "jpn";
                break;
            case 5:
                l = "deu";
                break;
            case 6:
                l = "ptr";
                break;
        }
        return l;
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() instanceof JRadioButton) {
            JRadioButton buton = (JRadioButton) e.getSource();
            switch (buton.getActionCommand()) {
                case "INS":
                    this.vista.confirmButton.setEnabled(false);
                    this.vista.idWordField2.setEnabled(false);
                    this.vista.englishField.setEditable(true);
                    this.vista.spanishField.setEditable(true);
                    this.vista.japaneseField.setEditable(true);
                    this.vista.kanaField.setEditable(true);
                    this.vista.germanField.setEditable(true);
                    this.vista.portugueseField.setEditable(true);
                    break;
                case "UPD":
                    this.vista.confirmButton.setEnabled(false);
                    this.vista.idWordField2.setEnabled(false);
                    this.vista.englishField.setEditable(true);
                    this.vista.spanishField.setEditable(true);
                    this.vista.japaneseField.setEditable(true);
                    this.vista.kanaField.setEditable(true);
                    this.vista.germanField.setEditable(true);
                    this.vista.portugueseField.setEditable(true);
                    break;
                case "REM":
                    this.vista.confirmButton.setEnabled(false);
                    this.vista.idWordField2.setEnabled(true);
                    this.vista.idWordField2.setEditable(true);
                    this.vista.englishField.setEditable(false);
                    this.vista.spanishField.setEditable(false);
                    this.vista.japaneseField.setEditable(false);
                    this.vista.kanaField.setEditable(false);
                    this.vista.germanField.setEditable(false);
                    this.vista.portugueseField.setEditable(false);
                    break;
            }
        }
    }

    @Override
    public void focusLost(FocusEvent e) {

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        int option = JOptionPane.showConfirmDialog(null, rb.getString("closequest"), rb.getString("confirm"), JOptionPane.YES_NO_OPTION);
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
        if (e.getSource() instanceof JTable) {

            if (this.vista.dictionaryTable.getSelectedColumn() != 0) {
                showMoreInfo = new PalabraSigEj(rb.getString("moreinfo"));
                // Mostrar palabra
                String palabra;
                String tipo = "";

                try {

                    /**
                     * Buscar la fila y columna seleccionadas para conseguir la palabra de la cual se quiere saber significado o ver ejemplo.
                     */
                    int row = this.vista.dictionaryTable.getSelectedRow();
                    int col = this.vista.dictionaryTable.getSelectedColumn();
                    palabra = this.vista.dictionaryTable.getValueAt(row, col).toString();
                    this.showMoreInfo.wordLabel.setText(palabra);
                    this.showMoreInfo.wordLabel.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20));

                    /**
                     * Buscar el id de la palabra seleccionada para ver que tipo de palabra es.
                     */
                    int id = Integer.parseInt(this.vista.dictionaryTable.getValueAt(row, 0)+"");
                    this.vista.wordTypeList.setSelectedIndices(this.modelo.buscarTipos(id));
                    List<String> tipos = this.vista.wordTypeList.getSelectedValuesList();
                    for (String s:tipos) {
                        tipo += s + ", ";
                    }
                    this.vista.wordTypeList.clearSelection();
                    try {
                        tipo = tipo.substring(0, tipo.length()-2);
                        showMoreInfo.typeWordLabel.setText(tipo);
                    } catch (StringIndexOutOfBoundsException siob) {
                        showMoreInfo.typeWordLabel.setText("");
                    }
                    showMoreInfo.typeWordLabel.setFont(new Font("Arial Unicode MS", Font.PLAIN, 16));

                    showMoreInfo.significado.setText(rb.getString("meaning"));
                    showMoreInfo.significado.setFont(new Font("Arial Unicode MS", Font.PLAIN, 15));
                    showMoreInfo.ejemplo.setText(rb.getString("example"));
                    showMoreInfo.ejemplo.setFont(new Font("Arial Unicode MS", Font.PLAIN, 15));

                    SignificadoEjemplo sig = sigmodel.obtenerSignificado(id, columnToCode());
                    try {
                        /**
                         * Aquí se comprueba que exista una entrada del significado o ejemplo de la palabra seleccionada.
                         */
                        showMoreInfo.significadoArea.setText(sig.getSignificado());
                        showMoreInfo.significadoArea.setFont(new Font("Arial Unicode MS", Font.PLAIN, 15));

                        showMoreInfo.ejemploArea.setText(sig.getEjemplo());
                        showMoreInfo.ejemploArea.setFont(new Font("Arial Unicode MS", Font.PLAIN, 15));

                        showMoreInfo.significadoArea.setEditable(false);
                        showMoreInfo.ejemploArea.setEditable(false);

                        if (this.modeloUser.esAdmin(this.email) || this.modeloUser.esProfesor(this.email)) {
                            showMoreInfo.continueButton.setText(rb.getString("edit"));
                            showMoreInfo.continueButton.setFont(new Font("Arial Unicode MS", Font.PLAIN, 15));
                            showMoreInfo.continueButton.setActionCommand("EDIT");
                            showMoreInfo.continueButton.addActionListener(this);
                        } else {
                            showMoreInfo.continueButton.setVisible(false);
                        }

                        showMoreInfo.pack();
                        showMoreInfo.setLocationRelativeTo(null);
                        showMoreInfo.setVisible(true);
                    } catch (NullPointerException npe) {
                        if (this.modeloUser.esProfesor(this.email) || this.modeloUser.esAdmin(this.email)) {
                            int opt = JOptionPane.showConfirmDialog(null, rb.getString("nomeanexam1"), "", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                            if (opt == 0) {
                                showMoreInfo.continueButton.setText(rb.getString("add"));
                                showMoreInfo.continueButton.setActionCommand("ADD");
                                showMoreInfo.continueButton.addActionListener(this);
                                showMoreInfo.pack();
                                showMoreInfo.setLocationRelativeTo(null);
                                showMoreInfo.setVisible(true);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, rb.getString("nomeanexam2"), "", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                } catch (NullPointerException npe) {
                    JOptionPane.showMessageDialog(null, rb.getString("noword"), "", JOptionPane.WARNING_MESSAGE);
                }

            }

        }
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

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (e.getSource()==this.vista.idWordField2 || e.getSource()==this.vista.englishField || e.getSource()==this.vista.spanishField || e.getSource()==this.vista.japaneseField || e.getSource()==this.vista.kanaField || e.getSource()==this.vista.germanField || e.getSource()==this.vista.portugueseField) {
                if (this.vista.insertButton.isSelected()) {
                    this.vista.confirmButton.setEnabled(true);
                } else if (this.vista.updateButton.isSelected()) {
                    this.vista.confirmButton.setEnabled(true);
                    this.vista.idWordField2.setEditable(false);
                    fillFields(e.getSource());
                } else if (this.vista.removeButton.isSelected()) {
                    this.vista.confirmButton.setEnabled(true);
                    fillFields(e.getSource());
                }
            }
        }
    }
}

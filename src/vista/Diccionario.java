package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Diccionario extends JFrame {

    public Diccionario(String title) {

        // Componentes del primer panel
        idWordLabel = new JLabel();
        idWordField = new JTextField(10);
        searchWordButton = new JButton();
        loadDictionaryButton = new JButton();
        diccionario = new JTable();
        moreActionsButton = new JButton();


        // Componentes del segundo panel
        moreActionsLabel = new JLabel();
        idwordLabel2 = new JLabel();
        englishLabel = new JLabel();
        spanishLabel = new JLabel();
        japaneseLabel = new JLabel();
        kanaLabel = new JLabel();
        germanLabel = new JLabel();
        portugueseLabel = new JLabel();
        koreanLabel = new JLabel();
        wordTypeLabel = new JLabel();
        idWordField2 = new JTextField(15);
        englishField = new JTextField(15);
        spanishField = new JTextField(15);
        japaneseField = new JTextField(15);
        kanaField = new JTextField(15);
        germanField = new JTextField(15);
        portugueseField = new JTextField(15);
        koreanField = new JTextField(15);
        wordTypeBox = new JComboBox();

        buttonGroup = new ButtonGroup();
        insertButton = new JRadioButton();
        buttonGroup.add(insertButton);
        updateButton = new JRadioButton();
        buttonGroup.add(updateButton);
        removeButton = new JRadioButton();
        buttonGroup.add(removeButton);

        acceptButton = new JButton();
        closeButton = new JButton();

        // Componiendo layout del segundo panel
        moreActionsPane = new JPanel();
        layout2 = new GroupLayout(moreActionsPane);
        moreActionsPane.setLayout(layout2);

        layout2.setAutoCreateGaps(true);

        layout2.setHorizontalGroup(
                layout2.createSequentialGroup()
                        .addGroup(layout2.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(moreActionsLabel)
                                .addComponent(idwordLabel2)
                                .addComponent(idWordField2)
                                .addComponent(acceptButton))
                        .addGroup(layout2.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(insertButton)
                                .addComponent(englishLabel)
                                .addComponent(englishField))
                        .addGroup(layout2.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(updateButton)
                                .addComponent(spanishLabel)
                                .addComponent(spanishField))
                        .addGroup(layout2.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(removeButton)
                                .addComponent(japaneseLabel)
                                .addComponent(japaneseField))
                        .addGroup(layout2.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(kanaLabel)
                                .addComponent(kanaField))
                        .addGroup(layout2.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(germanLabel)
                                .addComponent(germanField))
                        .addGroup(layout2.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(portugueseLabel)
                                .addComponent(portugueseField))
                        .addGroup(layout2.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(koreanLabel)
                                .addComponent(koreanField))
                        .addGroup(layout2.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(wordTypeLabel)
                                .addComponent(wordTypeBox)
                                .addComponent(closeButton))
        );

        layout2.setVerticalGroup(
                layout2.createSequentialGroup()
                        .addGroup(layout2.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(moreActionsLabel)
                                .addComponent(insertButton)
                                .addComponent(updateButton)
                                .addComponent(removeButton))
                        .addGroup(layout2.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(idwordLabel2)
                                .addComponent(englishLabel)
                                .addComponent(spanishLabel)
                                .addComponent(japaneseLabel)
                                .addComponent(kanaLabel)
                                .addComponent(germanLabel)
                                .addComponent(portugueseLabel)
                                .addComponent(koreanLabel)
                                .addComponent(wordTypeLabel))
                        .addGroup(layout2.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(idWordField2)
                                .addComponent(englishField)
                                .addComponent(spanishField)
                                .addComponent(japaneseField)
                                .addComponent(kanaField)
                                .addComponent(germanField)
                                .addComponent(portugueseField)
                                .addComponent(koreanField)
                                .addComponent(wordTypeBox))
                        .addGroup(layout2.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(acceptButton)
                                .addComponent(closeButton))
        );

        // Componiendo layout del primer panel
        mainPane = new JPanel();
        layout = new GroupLayout(mainPane);
        mainPane.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(idWordLabel)
                                .addComponent(diccionario)
                                .addComponent(moreActionsButton))
                        .addComponent(idWordField, 75, 85, 90)
                        .addComponent(searchWordButton)
                        .addComponent(loadDictionaryButton)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(idWordLabel)
                                .addComponent(idWordField)
                                .addComponent(searchWordButton)
                                .addComponent(loadDictionaryButton))
                        .addComponent(diccionario)
                        .addComponent(moreActionsButton)
        );


        rootPane = new JPanel(new BorderLayout());
        rootPane.add(mainPane, BorderLayout.CENTER);
        rootPane.add(moreActionsPane, BorderLayout.SOUTH);
        moreActionsPane.setVisible(false);
        this.add(rootPane);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setTitle(title);

    }

    public JLabel idWordLabel;
    private JTextField idWordField;
    public JButton searchWordButton;
    public JButton loadDictionaryButton;
    private JTable diccionario;
    private DefaultTableModel defaultTableModel;
    public JButton moreActionsButton;
    private JPanel rootPane;
    private JPanel mainPane;
    public JPanel moreActionsPane;
    private GroupLayout layout;
    private GroupLayout layout2;
    public JLabel moreActionsLabel;
    public JLabel idwordLabel2;
    public JLabel englishLabel;
    public JLabel spanishLabel;
    public JLabel japaneseLabel;
    public JLabel kanaLabel;
    public JLabel germanLabel;
    public JLabel portugueseLabel;
    public JLabel koreanLabel;
    public JLabel wordTypeLabel;
    public JTextField idWordField2;
    public JTextField englishField;
    public JTextField spanishField;
    public JTextField japaneseField;
    public JTextField kanaField;
    public JTextField germanField;
    public JTextField portugueseField;
    public JTextField koreanField;
    public JComboBox wordTypeBox;
    public ButtonGroup buttonGroup;
    public JRadioButton insertButton;
    public JRadioButton updateButton;
    public JRadioButton removeButton;
    public JButton acceptButton;
    public JButton closeButton;

}

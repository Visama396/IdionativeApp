package vista;

import javax.swing.*;

public class Diccionario extends JFrame {

    public Diccionario(String title) {

        actionsGroup = new ButtonGroup();
        topPane = new JPanel();
        topLayout = new GroupLayout(topPane);
        topPane.setLayout(topLayout);
        idWordLabel = new JLabel();
        idWordField = new JTextField(10);
        searchWordButton = new JButton();
        loadDictionaryButton = new JButton();
        jsDicc = new JScrollPane();
        dictionaryTable = new JTable();
        jsDicc.setViewportView(dictionaryTable);
        moreActionsButton = new JButton();
        bottomPane = new JPanel();
        botLayout = new GroupLayout(bottomPane);
        bottomPane.setLayout(botLayout);
        moreActionsLabel = new JLabel();
        insertButton = new JRadioButton();
        actionsGroup.add(insertButton);
        insertButton.setSelected(true);
        updateButton = new JRadioButton();
        actionsGroup.add(updateButton);
        removeButton = new JRadioButton();
        actionsGroup.add(removeButton);
        idWordLabel2 = new JLabel();
        englishLabel = new JLabel();
        spanishLabel = new JLabel();
        japaneseLabel = new JLabel();
        kanaLabel = new JLabel();
        germanLabel = new JLabel();
        portugueseLabel = new JLabel();
        wordTypeLabel = new JLabel();
        idWordField2 = new JTextField(10);
        englishField = new JTextField(10);
        spanishField = new JTextField(10);
        japaneseField = new JTextField(10);
        kanaField = new JTextField(10);
        germanField = new JTextField(10);
        portugueseField = new JTextField(10);
        wordTypeField = new JTextField(10);
        confirmButton = new JButton();
        confirmLabel = new JLabel();

        botLayout.setHorizontalGroup(
                botLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(botLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(botLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(botLayout.createSequentialGroup()
                                        .addComponent(moreActionsLabel)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(insertButton)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(updateButton)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(removeButton))
                                .addGroup(botLayout.createSequentialGroup()
                                        .addGroup(botLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(idWordField2, GroupLayout.DEFAULT_SIZE,  GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                                                .addComponent(idWordLabel2, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(botLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(englishLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(englishField, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(botLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(spanishLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(spanishField, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(botLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(japaneseLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(japaneseField, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(botLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(kanaLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(kanaField, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(botLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(germanLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(germanField, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(botLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(portugueseLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(portugueseField, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        )
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(botLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(wordTypeLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(wordTypeField, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap(23, Short.MAX_VALUE))
                .addGroup(botLayout.createSequentialGroup()
                        .addComponent(confirmButton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(confirmLabel)
                        .addContainerGap())
        );

        botLayout.setVerticalGroup(
                botLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(botLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(botLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(moreActionsLabel)
                                .addComponent(insertButton)
                                .addComponent(updateButton)
                                .addComponent(removeButton))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(botLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(idWordLabel2)
                                .addComponent(englishLabel)
                                .addComponent(spanishLabel)
                                .addComponent(japaneseLabel)
                                .addComponent(kanaLabel)
                                .addComponent(germanLabel)
                                .addComponent(portugueseLabel)
                                .addComponent(wordTypeLabel))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(botLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(idWordField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                                .addComponent(englishField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                                .addComponent(spanishField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                                .addComponent(japaneseField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                                .addComponent(kanaField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                                .addComponent(germanField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                                .addComponent(portugueseField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                                .addComponent(wordTypeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(botLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(confirmButton)
                                .addComponent(confirmLabel)))
        );

        // Layout de arriba
        topLayout.setHorizontalGroup(
                topLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, topLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(topLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(bottomPane, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jsDicc, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addGroup(GroupLayout.Alignment.LEADING, topLayout.createSequentialGroup()
                                        .addGroup(topLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(topLayout.createSequentialGroup()
                                                        .addComponent(idWordLabel)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(idWordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(searchWordButton)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(loadDictionaryButton))
                                                .addComponent(moreActionsButton))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
        );

        topLayout.setVerticalGroup(
                topLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(topLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(topLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(idWordLabel)
                                .addComponent(idWordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(searchWordButton)
                                .addComponent(loadDictionaryButton))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jsDicc, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(moreActionsButton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bottomPane, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
        );

        mainPane = new JPanel();
        mainLayout = new GroupLayout(mainPane);
        mainPane.setLayout(mainLayout);

        mainLayout.setHorizontalGroup(
                mainLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(topPane, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        mainLayout.setVerticalGroup(
                mainLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(topPane, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        this.add(mainPane);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setTitle(title);

    }

    public JMenu mainMenu;
    private JMenuBar menuBar;
    public JMenu languagesMenu;
    public JMenuItem spanishItem;
    public JMenuItem englishItem;
    public JMenuItem japaneseItem;
    public JMenuItem germanItem;
    public JMenuItem closeItem;

    private ButtonGroup actionsGroup;
    private JPanel topPane;
    private GroupLayout topLayout;
    public JLabel idWordLabel;
    public JTextField idWordField;
    public JButton searchWordButton;
    public JButton loadDictionaryButton;
    public JScrollPane jsDicc;
    public JTable dictionaryTable;
    public JButton moreActionsButton;
    public JPanel bottomPane;
    private GroupLayout botLayout;
    public JLabel moreActionsLabel;
    public JRadioButton insertButton;
    public JRadioButton updateButton;
    public JRadioButton removeButton;
    public JLabel idWordLabel2;
    public JLabel englishLabel;
    public JLabel spanishLabel;
    public JLabel japaneseLabel;
    public JLabel kanaLabel;
    public JLabel germanLabel;
    public JLabel portugueseLabel;
    public JLabel wordTypeLabel;
    public JTextField idWordField2;
    public JTextField englishField;
    public JTextField spanishField;
    public JTextField japaneseField;
    public JTextField kanaField;
    public JTextField germanField;
    public JTextField portugueseField;
    private JTextField wordTypeField;
    public JButton confirmButton;
    public JLabel confirmLabel;
    private JPanel mainPane;
    private GroupLayout mainLayout;

}

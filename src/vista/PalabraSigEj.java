package vista;

import javax.swing.*;

public class PalabraSigEj extends JFrame {

    public PalabraSigEj(String title) {

        wordLabel = new JLabel();
        wordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        wordLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        typeWordLabel = new JLabel();
        typeWordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        typeWordLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        significado = new JLabel();
        ejemplo = new JLabel();
        jsSignificado = new JScrollPane();
        jsEjemplo = new JScrollPane();
        significadoArea = new JTextArea(5, 20);
        significadoArea.setLineWrap(true);
        significadoArea.setWrapStyleWord(true);
        jsSignificado.setViewportView(significadoArea);
        ejemploArea = new JTextArea(5, 20);
        ejemploArea.setLineWrap(true);
        ejemploArea.setWrapStyleWord(true);
        jsEjemplo.setViewportView(ejemploArea);
        continueButton = new JButton();
        mainPane = new JPanel();
        layout = new GroupLayout(mainPane);
        mainPane.setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(wordLabel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(continueButton)
                                        .addComponent(jsSignificado, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                                        .addComponent(jsEjemplo)
                                        .addComponent(typeWordLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(ejemplo)
                                                        .addComponent(significado))
                                                .addGap(0 , 0, Short.MAX_VALUE)))))
                        .addContainerGap())
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(wordLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(typeWordLabel)
                        .addGap(23, 23, 23)
                        .addComponent(significado)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jsSignificado, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(ejemplo)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jsEjemplo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(continueButton)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        this.add(mainPane);
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
    }

    public JLabel wordLabel;
    public JLabel typeWordLabel;
    public JLabel significado;
    public JLabel ejemplo;
    private JScrollPane jsSignificado;
    private JScrollPane jsEjemplo;
    public JTextArea significadoArea;
    public JTextArea ejemploArea;
    public JButton continueButton;
    private GroupLayout layout;
    private JPanel mainPane;
}

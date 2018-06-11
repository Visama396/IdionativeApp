package vista;

import javax.swing.*;

public class PalabraSigEj extends JFrame {

    public PalabraSigEj(String title) {

        wordLabel = new JLabel("Palabra");
        typeWordLabel = new JLabel("Sustantivo");
        significado = new JLabel("Significado");
        ejemplo = new JLabel("Ejemplo");
        jsSignificado = new JScrollPane();
        jsEjemplo = new JScrollPane();
        significadoArea = new JTextArea("Es la unidad mínima del texto", 5, 25);
        jsSignificado.setViewportView(significadoArea);
        ejemploArea = new JTextArea("Esta palabra tiene muchas letras", 5, 25);
        jsEjemplo.setViewportView(ejemploArea);
        mainPane = new JPanel();
        layout = new GroupLayout(mainPane);
        mainPane.setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(155, 155, 155)
                                                                .addComponent(wordLabel))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(149, 149, 149)
                                                                .addComponent(typeWordLabel)))
                                                .addGap(0, 144, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jsSignificado, javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jsEjemplo)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(ejemplo)
                                                                .addGap(0, 0, Short.MAX_VALUE)))))
                                .addContainerGap())
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(significado)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(wordLabel)
                                .addGap(18, 18, 18)
                                .addComponent(typeWordLabel)
                                .addGap(16, 16, 16)
                                .addComponent(significado)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jsSignificado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11)
                                .addComponent(ejemplo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jsEjemplo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        this.add(mainPane);
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
    }

    private JLabel wordLabel;
    private JLabel typeWordLabel;
    private JLabel significado;
    private JLabel ejemplo;
    private JScrollPane jsSignificado;
    private JScrollPane jsEjemplo;
    private JTextArea significadoArea;
    private JTextArea ejemploArea;
    private GroupLayout layout;
    private JPanel mainPane;
}

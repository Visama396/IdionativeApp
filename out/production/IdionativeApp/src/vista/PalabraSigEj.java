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
        separadorSignificado = new JSeparator();
        separadorEjemplo = new JSeparator();
        mainPane = new JPanel();
        layout = new GroupLayout(mainPane);
        mainPane.setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                        .addGap(175, 175, 175)
                                                        .addComponent(wordLabel))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(149, 149, 149)
                                                                .addComponent(typeWordLabel)))
                                                .addGap(0, 144, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(jsSignificado, GroupLayout.Alignment.TRAILING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(significado)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(separadorSignificado))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(ejemplo)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(separadorEjemplo))
                                                        .addComponent(ejemploArea))))
                                .addContainerGap())
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(wordLabel)
                        .addGap(18, 18, 18)
                        .addComponent(typeWordLabel)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(15, 15, 15)
                                        .addComponent(significado))
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(23, 23, 23)
                                        .addComponent(separadorSignificado, GroupLayout.PREFERRED_SIZE, 7, GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jsSignificado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(ejemplo)
                                .addComponent(separadorEjemplo, GroupLayout.PREFERRED_SIZE, 7, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jsEjemplo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );


        this.add(mainPane);
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
    }

    public static void main(String[] args) {
        PalabraSigEj ev = new PalabraSigEj("Datos adicionales");
        ev.setVisible(true);
    }

    private JLabel wordLabel;
    private JLabel typeWordLabel;
    private JLabel significado;
    private JLabel ejemplo;
    private JScrollPane jsSignificado;
    private JScrollPane jsEjemplo;
    private JTextArea significadoArea;
    private JTextArea ejemploArea;
    private JSeparator separadorSignificado;
    private JSeparator separadorEjemplo;
    private GroupLayout layout;
    private JPanel mainPane;
}

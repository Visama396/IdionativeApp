package vista;

import modelo.Curso;
import modelo.Leccion;

import javax.swing.*;

public class Cursos extends JFrame {

    public Cursos(String title) {

        cursosLabel = new JLabel();
        leccionesLabel = new JLabel();
        cursosComboBox = new JComboBox<>();
        leccionesComboBox = new JComboBox<>();
        editButton = new JButton();
        jsContenido = new JScrollPane();
        mainPane = new JPanel();
        layout = new GroupLayout(mainPane);
        mainPane.setLayout(layout);

        layout.setAutoCreateContainerGaps(true);
        layout.setAutoCreateGaps(true);

        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(editButton))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(jsContenido)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(cursosLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(cursosComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                                                .addComponent(leccionesLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(leccionesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cursosLabel)
                                        .addComponent(cursosComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(leccionesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(leccionesLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(editButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jsContenido, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                                .addContainerGap())
        );

        this.add(mainPane);
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    public JLabel cursosLabel;
    public JLabel leccionesLabel;
    public JComboBox<Curso> cursosComboBox;
    public JComboBox<Leccion> leccionesComboBox;
    public JButton editButton;
    private JPanel mainPane;
    public JScrollPane jsContenido;
    private GroupLayout layout;

}

package vista;

import modelo.Curso;
import modelo.Leccion;

import javax.swing.*;

public class Cursos extends JFrame {

    public Cursos(String title) {

        cursosLabel = new JLabel();
        leccionesLabel = new JLabel();
        descLabel = new JLabel();
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
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(editButton))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(jsContenido)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(cursosLabel)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(cursosComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                                                .addComponent(leccionesLabel)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(leccionesComboBox, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(descLabel, GroupLayout.Alignment.LEADING))
                                .addContainerGap())
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(cursosLabel)
                                        .addComponent(cursosComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(leccionesComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(leccionesLabel))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(descLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(editButton)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jsContenido, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
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
    public JLabel descLabel;
    public JButton editButton;
    private JPanel mainPane;
    public JScrollPane jsContenido;
    private GroupLayout layout;

}

package vista;

import javax.swing.*;

/**
 * Vista de Inicio de sesión
 */

public class InicioSesion extends JFrame {

    /**
     * El constructor se encarga de colocar los componentes del Frame en el layout, luego desde el controlador se añade el texto a cada componente.
     * @param title Al crear el Frame en los parámetros se pone el título de la ventana.
     */

    public InicioSesion(String title) {

        emailLabel = new JLabel();
        emailField = new JTextField(15);
        passwordLabel = new JLabel();
        passwordField = new JPasswordField(15);
        signinButton = new JButton();
        signupButton = new JButton();
        noAccountButton = new JButton();
        goBackButton = new JButton();
        closeButton = new JButton();

        mainPane = new JPanel();
        layout = new GroupLayout(mainPane);
        mainPane.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(emailLabel)
                                .addComponent(passwordLabel)
                                .addComponent(signinButton))
                        .addGap(15, 20 , 30)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(emailField)
                                .addComponent(passwordField)
                                .addComponent(noAccountButton)
                                .addComponent(closeButton))
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(emailLabel)
                                .addComponent(emailField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(passwordLabel)
                                .addComponent(passwordField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(signinButton)
                                .addComponent(noAccountButton))
                        .addComponent(closeButton)
        );

        this.add(mainPane);
        this.setTitle(title);

    }

    public JLabel emailLabel;
    public JTextField emailField;
    public JLabel passwordLabel;
    public JPasswordField passwordField;

    public JButton signinButton;
    public JButton signupButton;
    public JButton noAccountButton;
    public JButton goBackButton;
    public JButton closeButton;
    private JPanel mainPane;
    private GroupLayout layout;

}

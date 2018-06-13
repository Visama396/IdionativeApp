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

        menuBar = new JMenuBar();

        mainMenu = new JMenu();
        languagesMenu = new JMenu();
        spanishItem = new JMenuItem();
        englishItem = new JMenuItem();
        japaneseItem = new JMenuItem();
        germanItem = new JMenuItem();
        closeItem = new JMenuItem();

        languagesMenu.add(spanishItem);
        languagesMenu.add(englishItem);
        languagesMenu.add(japaneseItem);
        languagesMenu.add(germanItem);

        mainMenu.add(languagesMenu);
        mainMenu.add(closeItem);

        menuBar.add(mainMenu);

        emailLabel = new JLabel();
        emailField = new JTextField(15);
        passwordLabel = new JLabel();
        passwordField = new JPasswordField(15);
        signinButton = new JButton();
        signupButton = new JButton();
        noAccountButton = new JButton();
        goBackButton = new JButton();
        rememberMeCheck = new JCheckBox();

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
                                .addComponent(noAccountButton))
                        .addGap(15, 20 , 30)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(emailField)
                                .addComponent(passwordField)
                                .addComponent(rememberMeCheck)
                                .addComponent(signinButton))
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
                                .addComponent(rememberMeCheck))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(noAccountButton)
                                .addComponent(signinButton))
        );

        this.setJMenuBar(menuBar);
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

    public JLabel emailLabel;
    public JTextField emailField;
    public JLabel passwordLabel;
    public JPasswordField passwordField;
    public JCheckBox rememberMeCheck;

    public JButton signinButton;
    public JButton signupButton;
    public JButton noAccountButton;
    public JButton goBackButton;
    private JPanel mainPane;
    private GroupLayout layout;

}

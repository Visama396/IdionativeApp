package vista;

import modelo.Idioma;

import javax.swing.*;

public class Registro extends JFrame {

    public Registro(String title) {

        emailLabel = new JLabel();
        emailField = new JTextField(15);
        userLabel = new JLabel();
        userField = new JTextField(15);
        passwordLabel = new JLabel();
        passwordField = new JPasswordField(15);

        signupButton = new JButton();
        returnButton = new JButton();
        closeButton = new JButton();

        nativeLangLabel = new JLabel();
        genderLabel = new JLabel();
        spokenLangLabel = new JLabel();
        learnLangLabel = new JLabel();

        nativeLangBox = new JComboBox<>();
        genderBox = new JComboBox<>();
        spokenLangList = new JList<>();
        JScrollPane jspSpoken = new JScrollPane(spokenLangList);
        learnLangList = new JList<>();
        JScrollPane jspLearn = new JScrollPane(learnLangList);

        mainPane = new JPanel();
        layout = new GroupLayout(mainPane);
        mainPane.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(userLabel)
                                .addComponent(emailLabel)
                                .addComponent(passwordLabel)
                                .addComponent(genderLabel)
                                .addComponent(nativeLangLabel)
                                .addComponent(spokenLangLabel)
                                .addComponent(learnLangLabel)
                                .addComponent(signupButton))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(jspSpoken)
                                .addComponent(jspLearn)
                                .addComponent(returnButton))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(userField)
                                .addComponent(emailField)
                                .addComponent(passwordField)
                                .addComponent(genderBox)
                                .addComponent(nativeLangBox)

                                .addComponent(closeButton))
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup().
                        addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(userLabel)
                                .addComponent(userField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(emailLabel)
                                .addComponent(emailField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(passwordLabel)
                                .addComponent(passwordField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(genderLabel)
                                .addComponent(genderBox))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(nativeLangLabel)
                                .addGap(50)
                                .addComponent(nativeLangBox))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(spokenLangLabel)
                                .addComponent(jspSpoken))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(learnLangLabel)
                                .addComponent(jspLearn))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(signupButton)
                                .addComponent(returnButton)
                                .addComponent(closeButton))
        );

        this.add(mainPane);
        this.setTitle(title);
    }

    public JLabel emailLabel;
    public JTextField emailField;
    public JLabel userLabel;
    public JTextField userField;
    public JLabel passwordLabel;
    public JPasswordField passwordField;
    public JLabel nativeLangLabel;
    public JLabel genderLabel;
    public JLabel spokenLangLabel;
    public JLabel learnLangLabel;
    public JComboBox<String> nativeLangBox;
    public JComboBox<String> genderBox;
    public JList<String> spokenLangList;
    public JList<String> learnLangList;
    public JButton signupButton;
    public JButton returnButton;
    public JButton closeButton;
    private JPanel mainPane;
    private GroupLayout layout;

}

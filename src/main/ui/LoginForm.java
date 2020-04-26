package ui;

import model.Account;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the login page of the app
public class LoginForm {
    private JFrame loginFrame;
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private ImageIcon ubcLogoImage;
    private JLabel ubcLogoLabel;

    // EFFECTS: constructs the login page and makes it visible to user
    public LoginForm() {
        initialize();
        loginFrame.setVisible(true);
    }

    // EFFECTS: initializes all components of page
    private void initialize() {
        loginFrame = new JFrame("Login");
        loginFrame.setBounds(100, 100, 450, 300);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.getContentPane().setLayout(null);

        JLabel appNameLabel = new JLabel("BCS Schedule Sharer");
        appNameLabel.setFont(new Font("Helvetica", Font.PLAIN, 20));
        appNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        appNameLabel.setBounds(140, 35, 225, 26);
        loginFrame.getContentPane().add(appNameLabel);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(103, 109, 76, 16);
        loginFrame.getContentPane().add(usernameLabel);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(103, 157, 61, 16);
        loginFrame.getContentPane().add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(227, 152, 130, 26);
        loginFrame.getContentPane().add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(85, 217, 117, 29);
        loginFrame.getContentPane().add(loginButton);
        loginAction(loginButton);

        usernameTextField = new JTextField();
        usernameTextField.setBounds(227, 104, 130, 26);
        loginFrame.getContentPane().add(usernameTextField);
        usernameTextField.setColumns(10);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(240, 217, 117, 29);
        loginFrame.getContentPane().add(registerButton);
        registerAction(registerButton);

        JPanel loginPanel = new JPanel();
        loginPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        loginPanel.setBounds(75, 89, 302, 106);
        loginFrame.getContentPane().add(loginPanel);

        ubcLogoImage = new ImageIcon("./data/ubclogo.png");
        ubcLogoLabel = new JLabel(ubcLogoImage);
        ubcLogoLabel.setBounds(62, 0, 100, 100);
        loginFrame.getContentPane().add(ubcLogoLabel);
    }

    // EFFECTS: adds action listener for login button
    //          when pressed, it should initialize the main app page, if the account exists
    public void loginAction(JButton loginButton) {
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {

                try {
                    Account account = new Account(usernameTextField.getText(),
                            String.valueOf(passwordField.getPassword()));

                    if (account.getStudentID() != null) {
                        loginFrame.setVisible(false);
                        ScheduleSharerForm scheduleSharerForm = new ScheduleSharerForm(account);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Username and password were not found in our records.");
                    }
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Error: " + e1,
                            "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }

    // EFFECTS: adds action listener for login button
    //          when pressed, it should initialize the register page
    public void registerAction(JButton registerButton) {
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {

                try {
                    loginFrame.setVisible(false);
                    RegisterForm registerForm = new RegisterForm();

                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Error: " + e1,
                            "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }

}


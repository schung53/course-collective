package ui;

import model.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the 'register new account' page of the app
public class RegisterForm {
    private JFrame registerFrame;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JTextField cohortTextField;
    private JTextField emailTextField;
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private ImageIcon ubcLogoImage;
    private JLabel ubcLogoLabel;

    // EFFECTS: constructs the register page and makes it visible to user
    public RegisterForm() {
        initialize();
        registerFrame.setVisible(true);
    }

    // EFFECTS: initializes all components of page
    private void initialize() {
        registerFrame = new JFrame("Register");
        registerFrame.setBounds(100, 100, 400, 500);
        registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registerFrame.getContentPane().setLayout(null);

        JLabel appNameLabel = new JLabel("BCS Schedule Sharer");
        appNameLabel.setFont(new Font("Helvetica", Font.PLAIN, 20));
        appNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        appNameLabel.setBounds(100, 42, 262, 27);
        registerFrame.getContentPane().add(appNameLabel);

        firstNameTextField = new JTextField();
        firstNameTextField.setBounds(49, 143, 130, 26);
        registerFrame.getContentPane().add(firstNameTextField);
        firstNameTextField.setColumns(10);

        lastNameTextField = new JTextField();
        lastNameTextField.setBounds(216, 143, 130, 26);
        registerFrame.getContentPane().add(lastNameTextField);
        lastNameTextField.setColumns(10);

        cohortTextField = new JTextField();
        cohortTextField.setBounds(49, 213, 130, 26);
        registerFrame.getContentPane().add(cohortTextField);
        cohortTextField.setColumns(10);

        emailTextField = new JTextField();
        emailTextField.setBounds(49, 281, 297, 26);
        registerFrame.getContentPane().add(emailTextField);
        emailTextField.setColumns(10);

        JLabel firstNameLabel = new JLabel("First Name");
        firstNameLabel.setBounds(49, 122, 87, 16);
        registerFrame.getContentPane().add(firstNameLabel);

        JLabel lastNameLabel = new JLabel("Last Name");
        lastNameLabel.setBounds(216, 122, 87, 16);
        registerFrame.getContentPane().add(lastNameLabel);

        JLabel cohortLabel = new JLabel("BCS Cohort/Entry Year (e.g. 2019)");
        cohortLabel.setBounds(49, 194, 228, 16);
        registerFrame.getContentPane().add(cohortLabel);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(50, 264, 61, 16);
        registerFrame.getContentPane().add(emailLabel);

        usernameTextField = new JTextField();
        usernameTextField.setBounds(49, 349, 130, 26);
        registerFrame.getContentPane().add(usernameTextField);
        usernameTextField.setColumns(10);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(49, 332, 75, 16);
        registerFrame.getContentPane().add(usernameLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(216, 349, 130, 26);
        registerFrame.getContentPane().add(passwordField);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(216, 332, 61, 16);
        registerFrame.getContentPane().add(passwordLabel);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(49, 416, 117, 29);
        registerFrame.getContentPane().add(registerButton);
        registrationAction(registerButton);

        JButton backToLoginButton = new JButton("Back to login");
        backToLoginButton.setBounds(229, 416, 117, 29);
        registerFrame.getContentPane().add(backToLoginButton);
        backToLoginAction(backToLoginButton);

        JPanel registerPanel = new JPanel();
        registerPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        registerPanel.setBounds(38, 105, 321, 287);
        registerFrame.getContentPane().add(registerPanel);

        ubcLogoImage = new ImageIcon("./data/ubclogo.png");
        ubcLogoLabel = new JLabel(ubcLogoImage);
        ubcLogoLabel.setBounds(46, 4, 100, 100);
        registerFrame.getContentPane().add(ubcLogoLabel);
    }

    // EFFECTS: adds action listener for the register button
    //          when pressed, it creates a new account/student in database and initializes main app
    public void registrationAction(JButton registerButton) {
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {

                try {
                    Account account = new Account(usernameTextField.getText(),
                            String.valueOf(passwordField.getPassword()));
                    Student student = new Student(firstNameTextField.getText(), lastNameTextField.getText(),
                            cohortTextField.getText(), emailTextField.getText());

                    account.registerAccount();
                    student.registerStudent(account.getAccountID());

                    if (account.getStudentID() != null) {
                        registerFrame.setVisible(false);
                        ScheduleSharerForm scheduleSharerForm = new ScheduleSharerForm(account);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Registration was unsuccessful. Please try again.");
                    }
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Error: " + e1,
                            "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }

    // EFFECTS: adds action listener for the back to login button
    //          when pressed, the register page closes and login page opens
    public void backToLoginAction(JButton backToLoginButton) {
        backToLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {

                try {
                    registerFrame.setVisible(false);
                    LoginForm loginForm = new LoginForm();

                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Error: " + e1,
                            "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }
}

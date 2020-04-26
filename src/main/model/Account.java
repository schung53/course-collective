package model;

import database.*;
import persistence.Saveable;

import java.io.PrintWriter;
import java.sql.SQLException;

// Represents an account for the application
public class Account implements Saveable {
    private String username;
    private String password;
    private AccountDAO accountDAO;
    private Student student;
    private StudentDAO studentDAO;
    private CourseDAO courseDAO;

    // EFFECTS: constructs an account and establishes database connection
    public Account(String username, String password) throws SQLException {
        this.username = username;
        this.password = password;
        accountDAO = new AccountDAO();
    }

    // EFFECTS: uses account DAO to retrieve studentID associated with account
    public String getStudentID() {
        return accountDAO.getStudentID(username);
    }

    // EFFECTS: uses account DAO to retrieve accountID
    public String getAccountID() {
        String accountID = accountDAO.getAccountID(username);
        return accountID;
    }

    // EFFECTS: retrieves student object associated with account in database
    public Student getStudent() throws SQLException {
        studentDAO = new StudentDAO();
        String studentID = getStudentID();
        student = studentDAO.convertStudentIDToStudent(studentID);
        return student;
    }

    // EFFECTS: registers new account in database
    public void registerAccount() throws SQLException {
        accountDAO.registerUsernamePassword(username, password);
    }

    // EFFECTS: implementation of save method from Saveable class
    public void save(PrintWriter printWriter) {
        try {
            courseDAO = new CourseDAO();
            printWriter.print(getStudent().getFirstName() + " " + getStudent().getLastName());
            printWriter.print("\nYOUR COURSES:\nSUBJECT | COURSE NO. | SECTION | TERM");
            printWriter.print(courseDAO.getStringRegistrationFromAccount(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

package model;

import database.StudentDAO;

import java.sql.SQLException;

// Represents a particular student - could be user or peer
public class Student {
    private String firstName;
    private String lastName;
    private String bcsYear;
    private String email;
    private StudentDAO studentDAO;

    // EFFECTS: constructs a student and establishes database connection
    public Student(String first, String last, String bcsYear, String email) throws SQLException {
        this.firstName = first;
        this.lastName = last;
        this.bcsYear = bcsYear;
        this.email = email;
        studentDAO = new StudentDAO();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBcsYear() {
        return bcsYear;
    }

    public String getEmail() {
        return email;
    }

    // EFFECTS: uses student DAO to retrieve studentID from database
    public String getStudentID() {
        return studentDAO.getStudentID(firstName, lastName, bcsYear);
    }

    // REQUIRES: valid accountID that exists in database and does not currently have an associated student
    // EFFECTS: associates student with the given accountID
    public void registerStudent(String accountID) throws SQLException {
        studentDAO.registerStudent(firstName, lastName, bcsYear, email, accountID);
    }
}

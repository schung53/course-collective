package database;

import model.Course;
import model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Database Access Object class pertaining to the student schema
public class StudentDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/schedulesharer";
    private String jdbcUsername = "root";
    private String jdbcPassword = "password";
    private Connection studentConn;

    // EFFECTS: constructs student Database Access Object and connects to database
    public StudentDAO() throws SQLException {
        studentConn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    // REQUIRES: given parameters must match a student in database
    // EFFECTS: retrieves studentID of associated student as string
    public String getStudentID(String firstName, String lastName, String bcsYear) {
        String studentID = null;
        String query = queryFormerFromStudent(firstName, lastName, bcsYear);
        Statement myStatement;
        ResultSet myResultSet = null;

        try {
            myStatement = studentConn.createStatement();
            myResultSet = myStatement.executeQuery(query);

            while (myResultSet.next()) {
                studentID = myResultSet.getString("studentID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentID;
    }

    // EFFECTS: forms SQL query to select given student
    private String queryFormerFromStudent(String firstName, String lastName, String bcsYear) {
        String query = "SELECT * FROM student WHERE ";

        if (firstName != null) {
            query = query + "firstName = \"" + firstName + "\" AND ";
        }

        if (lastName != null) {
            query = query + "lastName = \"" + lastName + "\" AND ";
        }

        if (bcsYear != null) {
            query = query + "bcsYear = \"" + bcsYear + "\";";
        }

        return query;
    }

    // REQUIRES: valid course in database with at least one student registered in course
    // EFFECTS: returns list of students that are taking the course
    public List peersFromCourse(Course course) throws SQLException {
        List listOfStudents = new ArrayList<>();
        String query = "SELECT * FROM registration WHERE courseID = \""
                + retrieveCourseValue(course, "courseID") + "\";";
        Statement myStatement = null;
        ResultSet myResultSet = null;

        myStatement = studentConn.createStatement();
        myResultSet = myStatement.executeQuery(query);

        while (myResultSet.next()) {
            Student student = convertRegistrationRowToStudent(myResultSet);
            listOfStudents.add(student);
        }
        return listOfStudents;
    }

    // EFFECTS: returns value (e.g. courseID, subject, courseNo) of course from database
    public String retrieveCourseValue(Course course, String value) throws SQLException {
        String retrievedCourseValue = null;

        Statement myStatement = studentConn.createStatement();

        ResultSet myResultSet = myStatement.executeQuery("SELECT * FROM course WHERE subject = \""
                + course.getSubject() + "\" AND courseNo = \"" + course.getCourseNo()
                + "\" AND section = \"" + course.getSection() + "\" AND term = \"" + course.getTerm() + "\"");

        while (myResultSet.next()) {
            retrievedCourseValue = myResultSet.getString(value);
        }
        return retrievedCourseValue;
    }

    // EFFECTS: converts row from result of query to a student object
    public Student convertRegistrationRowToStudent(ResultSet myResultSet) throws SQLException {
        String studentID = myResultSet.getString("studentID");
        Student student = convertStudentIDToStudent(studentID);
        return student;
    }

    // REQUIRES: studentID must exist in database
    // EFFECTS: returns student that has studentID associated with it
    public Student convertStudentIDToStudent(String studentID) throws SQLException {
        String query = "SELECT * FROM student WHERE studentID = \"" + studentID + "\";";
        Statement myStatement = null;
        ResultSet myResultSet = null;
        Student student = new Student(null, null, null, null);

        myStatement = studentConn.createStatement();
        myResultSet = myStatement.executeQuery(query);

        while (myResultSet.next()) {
            student = convertStudentRowToStudent(myResultSet);
        }
        return student;
    }

    // EFFECTS: converts singular row from student table query to student
    public Student convertStudentRowToStudent(ResultSet myResultSet) throws SQLException {
        String firstName = myResultSet.getString("firstName");
        String lastName = myResultSet.getString("lastName");
        String bcsYear = myResultSet.getString("bcsYear");
        String email = myResultSet.getString("email");

        Student student = new Student(firstName, lastName, bcsYear, email);

        return student;
    }

    // REQUIRES: accountID must exist in database but student must not yet exist
    // EFFECTS: adds new student with associated accountID to student database
    public void registerStudent(String firstName, String lastName, String bcsYear,
                                String email, String accountID) throws SQLException {
        Statement myStatement = studentConn.createStatement();

        myStatement.executeUpdate("INSERT INTO student(firstName, lastName, bcsYear, email, accountID) "
                + "VALUES(\"" + firstName + "\", \"" + lastName + "\", \""
                + bcsYear + "\", \"" + email + "\", \"" + accountID + "\");");
    }

}

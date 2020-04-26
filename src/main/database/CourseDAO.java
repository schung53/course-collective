package database;

import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Database Access Object class pertaining to the course and registration schemas
public class CourseDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/schedulesharer";
    private String jdbcUsername = "root";
    private String jdbcPassword = "password";
    private Connection courseConn;

    // EFFECTS: constructs course Database Access Object and connects to database
    public CourseDAO() throws SQLException {
        courseConn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    // REQUIRES: valid account that exists in database
    // EFFECTS: returns list of courses associated with account
    public List<Course> getRegistrationFromAccount(Account account) throws SQLException {
        List<Course> listOfCourses = new ArrayList<>();
        String query = "SELECT * FROM registration WHERE studentID = \"" + account.getStudentID() + "\";";
        Statement myStatement = null;
        ResultSet myResultSet = null;

        myStatement = courseConn.createStatement();
        myResultSet = myStatement.executeQuery(query);

        while (myResultSet.next()) {
            Course course = convertRegistrationRowToCourse(myResultSet);
            listOfCourses.add(course);
        }
        return listOfCourses;
    }

    // REQUIRES: valid account that exists in database
    // EFFECTS: returns student's courses in the form of a string
    public String getStringRegistrationFromAccount(Account account) throws SQLException {
        String scheduleString = "";
        List<Course> listOfCourses = getRegistrationFromAccount(account);
        int listSize = listOfCourses.size();
        for (int i = 0; i <= listSize - 1; i++) {
            Course course = listOfCourses.get(i);
            scheduleString = scheduleString + course.toString();
        }
        return scheduleString;
    }

    // REQUIRES: valid student who exists in database
    // EFFECTS: returns list of courses associated with student
    public List<Course> getRegistrationFromStudent(Student student) throws SQLException {
        List<Course> listOfCourses = new ArrayList<>();
        String query = "SELECT * FROM registration WHERE studentID = \"" + student.getStudentID() + "\";";
        Statement myStatement = null;
        ResultSet myResultSet = null;


        myStatement = courseConn.createStatement();
        myResultSet = myStatement.executeQuery(query);

        while (myResultSet.next()) {
            Course course = convertRegistrationRowToCourse(myResultSet);
            listOfCourses.add(course);
        }
        return listOfCourses;
    }

    // EFFECTS: adds course to course table in database; if already exists, skip to registration step
    public void addCourseToSchedule(Course course, Account account) throws SQLException {

        try {
            Statement myStatement = courseConn.createStatement();

            myStatement.executeUpdate("INSERT INTO course(subject, courseNo, section, term) VALUES(\""
                    + course.getSubject() + "\", \"" + course.getCourseNo() + "\", \""
                    + course.getSection() + "\", \"" + course.getTerm() + "\");");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Course already exists in course database.");
        } finally {
            addRegistration(course, account);
        }
    }

    // EFFECTS: adds course to associated account's registration
    public void addRegistration(Course course, Account account) throws SQLException {
        Statement myStatement = courseConn.createStatement();

        String studentID = account.getStudentID();
        String courseID = getCourseID(course);

        myStatement.executeUpdate("INSERT INTO registration(courseID, studentID) VALUES(" + courseID + ", "
                + studentID + ");");
    }

    // REQUIRES: registration must already exist
    // EFFECTS: deletes registration from registration table
    public void deleteCourseFromSchedule(Course course, Account account) throws SQLException {
        Statement myStatement = null;
        myStatement = courseConn.createStatement();
        String studentID = account.getStudentID();
        String courseID = getCourseID(course);

        myStatement.executeUpdate("DELETE FROM registration WHERE courseID = " + courseID + " AND studentID = "
                + studentID + ";");
    }

    // REQUIRES: course must already exist in database
    // EFFECTS: return courseID of course as string
    private String getCourseID(Course course) throws SQLException {
        String courseID = null;
        Statement myStatement = courseConn.createStatement();

        ResultSet rs = myStatement.executeQuery("SELECT * FROM course WHERE subject = \"" + course.getSubject()
                + "\" AND courseNo = \"" + course.getCourseNo() + "\" AND section = \""
                + course.getSection() + "\" AND term = \"" + course.getTerm() + "\";");

        while (rs.next()) {
            courseID = rs.getString("courseID");
        }
        return courseID;
    }

    // EFFECTS: converts result from course table query to course object
    private Course convertRegistrationRowToCourse(ResultSet myResultSet) throws SQLException {
        String courseID = myResultSet.getString("courseID");
        Course course = convertCourseIDToCourse(courseID);
        return course;
    }

    // REQUIRES: valid courseID must be associated with a course in database
    // EFFECTS: converts courseID to course object
    private Course convertCourseIDToCourse(String courseID) throws SQLException {
        String query = "SELECT * FROM course WHERE courseID = \"" + courseID + "\";";
        Statement myStatement = null;
        ResultSet myResultSet = null;
        Course course = new Course(null, null, null, null);

        myStatement = courseConn.createStatement();
        myResultSet = myStatement.executeQuery(query);

        while (myResultSet.next()) {
            course = convertCourseRowToCourse(myResultSet);
        }
        return course;
    }

    // EFFECTS: converts singular result row  from course table query to course object
    private Course convertCourseRowToCourse(ResultSet myResultSet) throws SQLException {
        String subject = myResultSet.getString("subject");
        String courseNo = myResultSet.getString("courseNo");
        String section = myResultSet.getString("section");
        String term = myResultSet.getString("term");

        Course course = new Course(subject, courseNo, section, term);

        return course;
    }

    // EFFECTS: closes database connection
    private static void close(Connection myConn, Statement myStatement, ResultSet myResultSet)
            throws SQLException {

        if (myResultSet != null) {
            myResultSet.close();
        }

        if (myStatement != null) {
            myStatement.close();
        }

        if (myConn != null) {
            myConn.close();
        }
    }
}

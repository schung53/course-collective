package model;

import database.CourseDAO;

import java.sql.SQLException;

// Represents a course of interest
public class Course {
    private String subject;
    private String courseNo;
    private String section;
    private String term;
    private CourseDAO courseDAO;

    // EFFECTS: constructs a course and establishes database connection
    public Course(String subject, String courseNo, String section, String term) throws SQLException {
        this.subject = subject;
        this.courseNo = courseNo;
        this.section = section;
        this.term = term;
        courseDAO = new CourseDAO();
    }

    public String getSubject() {
        return subject;
    }

    public String getCourseNo() {
        return courseNo;
    }

    public String getSection() {
        return section;
    }

    public String getTerm() {
        return term;
    }

    // EFFECTS: converts course object to string (e.g. "CPSC | 210 | 201 | 2019W")
    public String toString() {
        String courseString = "\n";
        courseString = courseString + getSubject() + " | " + getCourseNo() + " | " + getSection() + " | "
                + getTerm();
        return courseString;
    }
}

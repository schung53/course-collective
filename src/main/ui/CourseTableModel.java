package ui;

import model.Course;

import javax.swing.table.AbstractTableModel;
import java.util.List;

// Represents a model of the table showing courses
public class CourseTableModel extends AbstractTableModel {
    private static final int SUBJECT_COL = 0;
    private static final int COURSE_NO_COL = 1;
    private static final int SECTION_COL = 2;
    private static final int TERM_COL = 3;

    private String[] columnNames = { "Subject", "Course No.", "Section", "Term" };
    private List<Course> courses;

    // EFFECTS: constructs a table model with a set of courses
    public CourseTableModel(List<Course> theCourses) {
        courses = theCourses;
    }

    @Override
    public int getRowCount() {
        return courses.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {

        Course course = courses.get(row);

        switch (col) {
            case SUBJECT_COL:
                return course.getSubject();
            case COURSE_NO_COL:
                return course.getCourseNo();
            case SECTION_COL:
                return course.getSection();
            case TERM_COL:
                return course.getTerm();
            default:
                return course.getSubject();
        }
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
}


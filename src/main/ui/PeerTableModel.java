package ui;

import model.Student;

import javax.swing.table.AbstractTableModel;
import java.util.List;

// Represents a model of the table showing students
public class PeerTableModel extends AbstractTableModel {
    private static final int FIRST_NAME_COL = 0;
    private static final int LAST_NAME_COL = 1;
    private static final int EMAIL_COL = 2;
    private static final int COHORT_COL = 3;

    private String[] columnNames = { "First Name", "Last Name", "Email", "Cohort" };
    private List<Student> students;

    // EFFECTS: constructs a table model with a set of students
    public PeerTableModel(List<Student> theStudents) {
        students = theStudents;
    }

    @Override
    public int getRowCount() {
        return students.size();
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

        Student student = students.get(row);

        switch (col) {
            case FIRST_NAME_COL:
                return student.getFirstName();
            case LAST_NAME_COL:
                return student.getLastName();
            case EMAIL_COL:
                return student.getEmail();
            case COHORT_COL:
                return student.getBcsYear();
            default:
                return student.getFirstName();
        }
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

}

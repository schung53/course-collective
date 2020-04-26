package ui;

import database.*;
import model.*;
import persistence.Writer;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

// Represents the main page of the app
public class ScheduleSharerForm {
    private static final String SCHEDULE_FILE = "./data/schedule.txt";
    private JFrame mainFrame;
    private JFormattedTextField subjectTextField;
    private JFormattedTextField courseNoTextField;
    private JFormattedTextField sectionTextField;
    private JFormattedTextField termTextField;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JFormattedTextField cohortTextField;
    private JTable scheduleTable;
    private JTable peerTable;
    private JTextField subjectTextField2;
    private JTextField courseNoTextField2;
    private JTextField sectionTextField2;
    private JTextField termTextField2;
    private ImageIcon ubcLogoImage;
    private JLabel ubcLogoLabel;
    private CourseDAO courseDAO;
    private Account account;
    private StudentDAO studentDAO;

    // EFFECTS: constructs the main app page and makes it visible to user
    public ScheduleSharerForm(Account account) throws SQLException {
        this.account = account;
        initialize();
        mainFrame.setVisible(true);
    }

    // EFFECTS: initializes all components of page
    private void initialize() throws SQLException {
        mainFrame = new JFrame("BCS Schedule Sharer");
        mainFrame.setBounds(100, 100, 955, 600);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.getContentPane().setLayout(null);

        JLabel viewEditScheduleLabel = new JLabel("View/Edit Your Schedule");
        viewEditScheduleLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
        viewEditScheduleLabel.setBounds(28, 38, 183, 16);
        mainFrame.getContentPane().add(viewEditScheduleLabel);

        JLabel subjectLabel = new JLabel("Subject");
        subjectLabel.setBounds(28, 70, 58, 16);
        mainFrame.getContentPane().add(subjectLabel);

        JLabel courseNoLabel = new JLabel("Course No.");
        courseNoLabel.setBounds(114, 70, 80, 16);
        mainFrame.getContentPane().add(courseNoLabel);

        JLabel sectionLabel = new JLabel("Section");
        sectionLabel.setBounds(206, 70, 58, 16);
        mainFrame.getContentPane().add(sectionLabel);

        JLabel termLabel = new JLabel("Term");
        termLabel.setBounds(298, 70, 49, 16);
        mainFrame.getContentPane().add(termLabel);

        subjectTextField = new JFormattedTextField();
        subjectTextField.setText("CPSC");
        subjectTextField.setBounds(28, 91, 67, 26);
        mainFrame.getContentPane().add(subjectTextField);

        courseNoTextField = new JFormattedTextField();
        courseNoTextField.setText("210");
        courseNoTextField.setBounds(114, 91, 67, 26);
        mainFrame.getContentPane().add(courseNoTextField);

        sectionTextField = new JFormattedTextField();
        sectionTextField.setText("201");
        sectionTextField.setBounds(206, 91, 67, 26);
        mainFrame.getContentPane().add(sectionTextField);

        termTextField = new JFormattedTextField();
        termTextField.setText("2019W");
        termTextField.setBounds(298, 91, 67, 26);
        mainFrame.getContentPane().add(termTextField);

        JLabel browsePeerScheduleLabel = new JLabel("Browse Peer Schedules");
        browsePeerScheduleLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
        browsePeerScheduleLabel.setBounds(28, 184, 166, 16);
        mainFrame.getContentPane().add(browsePeerScheduleLabel);

        JButton addCourseButton = new JButton("Add course");
        addCourseButton.setBounds(28, 130, 110, 29);
        mainFrame.getContentPane().add(addCourseButton);
        addCourseAction(addCourseButton);

        JButton deleteCourseButton = new JButton("Delete course");
        deleteCourseButton.setBounds(136, 130, 110, 29);
        mainFrame.getContentPane().add(deleteCourseButton);
        deleteCourseAction(deleteCourseButton);

        JButton viewScheduleButton = new JButton("View schedule");
        viewScheduleButton.setBounds(241, 130, 117, 29);
        mainFrame.getContentPane().add(viewScheduleButton);
        viewScheduleAction(viewScheduleButton);

        JLabel firstNameLabel = new JLabel("First Name");
        firstNameLabel.setBounds(28, 216, 80, 16);
        mainFrame.getContentPane().add(firstNameLabel);

        JLabel lastNameLabel = new JLabel("Last Name");
        lastNameLabel.setBounds(160, 216, 80, 16);
        mainFrame.getContentPane().add(lastNameLabel);

        JLabel cohortLabel = new JLabel("Cohort/Entry Year");
        cohortLabel.setBounds(296, 216, 128, 16);
        mainFrame.getContentPane().add(cohortLabel);

        firstNameTextField = new JTextField();
        firstNameTextField.setBounds(28, 235, 117, 26);
        mainFrame.getContentPane().add(firstNameTextField);
        firstNameTextField.setColumns(10);

        lastNameTextField = new JTextField();
        lastNameTextField.setBounds(160, 235, 117, 26);
        mainFrame.getContentPane().add(lastNameTextField);
        lastNameTextField.setColumns(10);

        cohortTextField = new JFormattedTextField();
        cohortTextField.setText("2019");
        cohortTextField.setBounds(298, 235, 67, 26);
        mainFrame.getContentPane().add(cohortTextField);

        JButton viewPeerScheduleButton = new JButton("View peer schedule");
        viewPeerScheduleButton.setBounds(28, 273, 174, 29);
        mainFrame.getContentPane().add(viewPeerScheduleButton);
        viewPeerScheduleAction(viewPeerScheduleButton);

        JScrollPane scheduleScrollPane = new JScrollPane();
        scheduleScrollPane.setBounds(16, 346, 453, 211);
        mainFrame.getContentPane().add(scheduleScrollPane);

        scheduleTable = new JTable();
        setInitialSchedule();
        scheduleScrollPane.setViewportView(scheduleTable);

        JScrollPane peerScrollPane = new JScrollPane();
        peerScrollPane.setBounds(487, 346, 453, 211);
        mainFrame.getContentPane().add(peerScrollPane);

        peerTable = new JTable();
        setInitialPeersInCourse();
        peerScrollPane.setViewportView(peerTable);

        JLabel browsePeersInCoursesLabel = new JLabel("Browse Peers In Courses");
        browsePeersInCoursesLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
        browsePeersInCoursesLabel.setBounds(503, 184, 183, 16);
        mainFrame.getContentPane().add(browsePeersInCoursesLabel);

        JLabel subjectLabel2 = new JLabel("Subject");
        subjectLabel2.setBounds(503, 216, 61, 16);
        mainFrame.getContentPane().add(subjectLabel2);

        JLabel courseNoLabel2 = new JLabel("Course No.");
        courseNoLabel2.setBounds(593, 216, 80, 16);
        mainFrame.getContentPane().add(courseNoLabel2);

        JLabel sectionLabel2 = new JLabel("Section");
        sectionLabel2.setBounds(685, 216, 58, 16);
        mainFrame.getContentPane().add(sectionLabel2);

        JLabel termLabel2 = new JLabel("Term");
        termLabel2.setBounds(775, 216, 49, 16);
        mainFrame.getContentPane().add(termLabel2);

        subjectTextField2 = new JTextField();
        subjectTextField2.setBounds(503, 235, 67, 26);
        mainFrame.getContentPane().add(subjectTextField2);
        subjectTextField2.setColumns(10);

        courseNoTextField2 = new JTextField();
        courseNoTextField2.setBounds(593, 235, 67, 26);
        mainFrame.getContentPane().add(courseNoTextField2);
        courseNoTextField2.setColumns(10);

        sectionTextField2 = new JTextField();
        sectionTextField2.setBounds(685, 235, 67, 26);
        mainFrame.getContentPane().add(sectionTextField2);
        sectionTextField2.setColumns(10);

        termTextField2 = new JTextField();
        termTextField2.setBounds(775, 235, 67, 26);
        mainFrame.getContentPane().add(termTextField2);
        termTextField2.setColumns(10);

        JButton viewPeersInCourseButton = new JButton("View peers in course");
        viewPeersInCourseButton.setBounds(503, 273, 174, 29);
        mainFrame.getContentPane().add(viewPeersInCourseButton);
        viewPeersInCourseAction(viewPeersInCourseButton);

        JLabel appNameLabel = new JLabel("BCS Schedule Sharer");
        appNameLabel.setFont(new Font("Helvetica", Font.PLAIN, 24));
        appNameLabel.setBounds(615, 68, 263, 36);
        mainFrame.getContentPane().add(appNameLabel);

        JButton saveToFileButton = new JButton("Save to file");
        saveToFileButton.setBounds(354, 130, 110, 29);
        mainFrame.getContentPane().add(saveToFileButton);
        saveToFileAction(saveToFileButton);

        JPanel viewEditSchedulePanel = new JPanel();
        viewEditSchedulePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        viewEditSchedulePanel.setBounds(16, 26, 453, 139);
        mainFrame.getContentPane().add(viewEditSchedulePanel);

        JPanel browsePeerSchedulePanel = new JPanel();
        browsePeerSchedulePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        browsePeerSchedulePanel.setBounds(16, 174, 453, 139);
        mainFrame.getContentPane().add(browsePeerSchedulePanel);

        JPanel browsePeersInCoursePanel = new JPanel();
        browsePeersInCoursePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        browsePeersInCoursePanel.setBounds(487, 174, 453, 139);
        mainFrame.getContentPane().add(browsePeersInCoursePanel);

        JLabel scheduleLabel = new JLabel("Schedule");
        scheduleLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
        scheduleLabel.setBounds(16, 325, 70, 16);
        mainFrame.getContentPane().add(scheduleLabel);

        JLabel peersLabel = new JLabel("Peers");
        peersLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
        peersLabel.setBounds(487, 325, 70, 16);
        mainFrame.getContentPane().add(peersLabel);

        ubcLogoImage = new ImageIcon("./data/ubclogo.png");
        ubcLogoLabel = new JLabel(ubcLogoImage);
        ubcLogoLabel.setBounds(525, 35, 100, 100);
        mainFrame.getContentPane().add(ubcLogoLabel);
    }

    // EFFECTS: upon initialization of the main page, sets course table with user's schedule
    private void setInitialSchedule() throws SQLException {
        courseDAO = new CourseDAO();
        List courses = courseDAO.getRegistrationFromAccount(account);
        CourseTableModel model = new CourseTableModel(courses);

        scheduleTable.setModel(model);
    }

    // EFFECTS: sets blank peer table upon initialization of page
    private void setInitialPeersInCourse() throws SQLException {
        studentDAO = new StudentDAO();
        Course course = new Course(null, null, null, null);
        List students = studentDAO.peersFromCourse(course);
        CourseTableModel model = new CourseTableModel(students);

        peerTable.setModel(model);
    }

    // EFFECTS: adds action listener for add course button; adds courses
    private void addCourseAction(JButton addCourseButton) {
        addCourseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {

                try {
                    Course course = new Course(subjectTextField.getText(), courseNoTextField.getText(),
                            sectionTextField.getText(), termTextField.getText());

                    courseDAO.addCourseToSchedule(course, account);

                    List courses = courseDAO.getRegistrationFromAccount(account);
                    CourseTableModel model = new CourseTableModel(courses);
                    scheduleTable.setModel(model);
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Error: " + e1,
                            "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }
    // EFFECTS: adds action listener for add course button; deletes courses
    private void deleteCourseAction(JButton deleteCourseButton) {
        deleteCourseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {

                try {
                    Course course = new Course(subjectTextField.getText(), courseNoTextField.getText(),
                            sectionTextField.getText(), termTextField.getText());

                    courseDAO.deleteCourseFromSchedule(course, account);

                    List courses = courseDAO.getRegistrationFromAccount(account);
                    CourseTableModel model = new CourseTableModel(courses);
                    scheduleTable.setModel(model);
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Error: " + e1,
                            "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }

    // EFFECTS: adds action listener for view schedule button; refreshes user's schedule table
    private void viewScheduleAction(JButton viewScheduleButton) {
        viewScheduleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {

                try {
                    List courses = courseDAO.getRegistrationFromAccount(account);
                    CourseTableModel model = new CourseTableModel(courses);
                    scheduleTable.setModel(model);

                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Error: " + e1,
                            "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }
    // EFFECTS: adds action listener for view peer schedule button; refreshes peer's schedule table
    private void viewPeerScheduleAction(JButton viewPeerScheduleButton) {
        viewPeerScheduleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {

                try {
                    Student student = new Student(firstNameTextField.getText(), lastNameTextField.getText(),
                            cohortTextField.getText(), null);

                    List courses = courseDAO.getRegistrationFromStudent(student);
                    CourseTableModel model = new CourseTableModel(courses);
                    scheduleTable.setModel(model);
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Error: " + e1,
                            "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }

    // EFFECTS: adds action listener for view peers in course button; refreshes peer table
    private void viewPeersInCourseAction(JButton viewPeersInCourseButton) {
        viewPeersInCourseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {

                try {
                    studentDAO = new StudentDAO();
                    Course course = new Course(subjectTextField2.getText(), courseNoTextField2.getText(),
                            sectionTextField2.getText(), termTextField2.getText());

                    List students = studentDAO.peersFromCourse(course);
                    PeerTableModel model = new PeerTableModel(students);
                    peerTable.setModel(model);
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Error: " + e1,
                            "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }

    // EFFECTS: adds action listener for save to file button; saves user's schedule as text file
    public void saveToFileAction(JButton saveToFileButton) {
        saveToFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Writer writer = new Writer(new File(SCHEDULE_FILE));
                    writer.write(account);
                    writer.close();
                    System.out.println("Schedule saved to file " + SCHEDULE_FILE);
                } catch (FileNotFoundException e) {
                    System.out.println("Unable to save schedule to " + SCHEDULE_FILE);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    // this is due to a programming error
                }
            }
        });
    }
}

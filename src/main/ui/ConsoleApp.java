package ui;

import model.Course;
import model.Student;
import persistence.Writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

// BCS schedule sharing application
public class ConsoleApp {
    private static final String SCHEDULE_FILE = "./data/schedule.txt";
    private Student student;
    private Scanner input;

    /*public ConsoleApp() {
        runScheduleShare();
    }

    private void runScheduleShare() {
        boolean keepGoing = true;
        String command = null;
        input = new Scanner(System.in);

        loginOrRegisterPrompt();

        while (keepGoing) {
            displayMenu();
            command = input.nextLine();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye.");
    }

    private void loginOrRegisterPrompt() {
        String command = null;

        System.out.println("LOGIN OR REGISTER?");
        System.out.println("\tl -> login");
        System.out.println("\tr -> register");
        command = input.nextLine();
        command = command.toLowerCase();

        if (command.equals("l")) {
            loginPrompt();
        } else if (command.equals("r")) {
            registerPrompt();
        } else {
            System.out.println("Selection not valid.\n");
            loginOrRegisterPrompt();
        }
    }

    private void loginPrompt() {
        String username = null;
        String password = null;

        System.out.println("USERNAME:");
        username = input.nextLine();
        username = username.toLowerCase();

        System.out.println("PASSWORD:");
        password = input.nextLine();

        init(username, password);
        if (student.getFirstName() != null) {
            System.out.println("\nWelcome " + student.getFirstName() + " " + student.getLastName() + ".");
        } else {
            System.out.println("\nStudent does not exist.\n");
            loginOrRegisterPrompt();
        }
    }

    private void init(String username, String password) {
        student = new Student(username, password);
    }

    public void registerPrompt() {
        System.out.println("FIRST NAME:");
        String newFirstName = input.nextLine();
        System.out.println("LAST NAME:");
        String newLastName = input.nextLine();
        System.out.println("BCS COHORT/ENTRY YEAR (e.g. 2019):");
        String newBcsYear = input.nextLine();
        System.out.println("EMAIL:");
        String newEmail = input.nextLine();
        System.out.println("CHOOSE USERNAME:");
        String newUsername = input.nextLine();
        System.out.println("CHOOSE PASSWORD:");
        String newPassword = input.nextLine();

        init(newUsername, newPassword);
        student.register(newFirstName, newLastName, newBcsYear, newEmail, newUsername, newPassword);
        System.out.println("\nWelcome " + newFirstName + " " + newLastName + ".");
    }

    public void displayMenu() {
        System.out.println("\nSCHEDULE SHARE OPTIONS:");
        System.out.println("\ta -> add courses to your schedule");
        System.out.println("\tv -> view your schedule");
        System.out.println("\tb -> browse courses");
        System.out.println("\tp -> view peer's schedule");
        System.out.println("\ts -> save course schedule to file");
        System.out.println("\tq -> quit");
    }

    public void processCommand(String command) {
        if (command.equals("a")) {
            doAddCourses();
        } else if (command.equals("v")) {
            doViewSchedule();
        } else if (command.equals("b")) {
            doBrowseCourses();
        } else if (command.equals("p")) {
            doViewPeer();
        } else if (command.equals("s")) {
            saveSchedule();
        } else {
            System.out.println("\nSelection not valid.\n");
        }
    }

    public void doAddCourses() {
        boolean keepGoing = true;
        String continuation = "y";

        while (keepGoing) {
            if (continuation.equals("n")) {
                keepGoing = false;
            } else if (continuation.equals("y")) {
                System.out.println("\nSUBJECT (e.g. CPSC):");
                String newSubject = input.nextLine().toUpperCase();
                System.out.println("COURSE NUMBER (e.g. 210):");
                String newCourseNumber = input.nextLine();
                System.out.println("SECTION (e.g. 201):");
                String newSection = input.nextLine();
                System.out.println("TERM (e.g. 2019W):");
                String newTerm = input.nextLine().toUpperCase();

                student.addCourse(newSubject, newCourseNumber, newSection, newTerm);
                continuation = addAnotherCoursePrompt();
            } else {
                System.out.println("\nSelection not valid.");
                continuation = addAnotherCoursePrompt();
            }
        }
    }

    public String addAnotherCoursePrompt() {
        String command = null;

        System.out.println("\nADD ANOTHER COURSE?");
        System.out.println("\ty -> yes");
        System.out.println("\tn -> no");
        command = input.nextLine();
        return command;
    }

    public void doViewSchedule() {
        String schedule = student.viewSchedule();
        System.out.println(schedule);
    }

    public void doBrowseCourses() {
        boolean keepGoing = true;
        String continuation = "y";

        while (keepGoing) {
            if (continuation.equals("n")) {
                keepGoing = false;
            } else if (continuation.equals("y")) {
                System.out.println("\nSUBJECT (e.g. CPSC):");
                String subject = input.nextLine().toUpperCase();
                System.out.println("COURSE NUMBER (e.g. 210):");
                String courseNumber = input.nextLine();
                System.out.println("SECTION (e.g. 201):");
                String section = input.nextLine();
                System.out.println("TERM (e.g. 2019W):");
                String term = input.nextLine().toUpperCase();

                Course selectedCourse = new Course(subject, courseNumber, section, term);
                String studentsInCourse = student.browseCourses(selectedCourse);
                System.out.println(studentsInCourse);
                continuation = browseCoursePrompt();
            } else {
                System.out.println("Selection not valid.");
                continuation = browseCoursePrompt();
            }
        }
    }

    public String browseCoursePrompt() {
        String command = null;

        System.out.println("\nBROWSE ANOTHER COURSE?");
        System.out.println("\ty -> yes");
        System.out.println("\tn -> no");
        command = input.nextLine();
        return command;
    }

    public void doViewPeer() {
        boolean keepGoing = true;
        String continuation = "y";

        while (keepGoing) {
            if (continuation.equals("n")) {
                keepGoing = false;
            } else if (continuation.equals("y")) {
                System.out.println("\nFIRST NAME:");
                String peerFirstName = input.nextLine();
                System.out.println("LAST NAME:");
                String peerLastName = input.nextLine();
                System.out.println("COHORT:");
                String peerCohort = input.nextLine();

                String peerCourses = student.viewPeer(peerFirstName, peerLastName, peerCohort);
                System.out.println(peerCourses);
                continuation = viewPeerPrompt();
            } else {
                System.out.println("Selection not valid.");
                continuation = viewPeerPrompt();
            }
        }
    }

    public String viewPeerPrompt() {
        String command = null;

        System.out.println("\nVIEW ANOTHER PEER?");
        System.out.println("\ty -> yes");
        System.out.println("\tn -> no");
        command = input.nextLine();
        return command;
    }

    public void saveSchedule() {
        try {
            Writer writer = new Writer(new File(SCHEDULE_FILE));
            writer.write(student);
            writer.close();
            System.out.println("Schedule saved to file " + SCHEDULE_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save schedule to " + SCHEDULE_FILE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // this is due to a programming error
        }
    }*/
 
}

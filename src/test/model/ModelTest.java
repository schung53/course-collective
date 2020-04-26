package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {
    Student testStudent;
    Student newTestStudent;
    Course newCourse;

/*    @BeforeEach
    void runBefore() {
        testStudent = new Student("schung53", "password");
        newCourse = new Course("CPSC", "304", "201", "2019W");
        newTestStudent = new Student("jmadison44", "password");
    }

    @Test
    void testConstructor() {
        assertEquals("James", testStudent.getFirstName());
        assertEquals("Chung", testStudent.getLastName());
    }

    @Test
    void testRegister() {
        newTestStudent.register("James", "Madison", "2019", "jmadison@gmail.com",
                "jmadison44", "password");
        assertEquals("James", newTestStudent.getFirstName());
        assertEquals("Madison", newTestStudent.getLastName());
    }

    @Test
    void testAddCourse() {
        newTestStudent.addCourse("CPSC", "304", "201", "2019W");
        String expectedSchedule = "\nYOUR COURSES:\nSUBJECT | COURSE NO. | SECTION | TERM\nCPSC | 304 | 201 | 2019W";
        assertEquals(expectedSchedule, newTestStudent.viewSchedule());
    }

    @Test
    void testBrowseCourses() {
        String expectedGroup = "\nSTUDENTS IN COURSE:\nFIRST NAME | LAST NAME | COHORT | EMAIL" +
                "\nJames | Madison | 2019 | jmadison@gmail.com";
        assertEquals(expectedGroup, newTestStudent.browseCourses(newCourse));
    }

    @Test
    void testViewPeer() {
        String expectedSchedule = "\nJAMES MADISON'S COURSES:\nSUBJECT | COURSE NO. | SECTION | TERM" +
                "\nCPSC | 304 | 201 | 2019W";
        assertEquals(expectedSchedule,
                testStudent.viewPeer("James", "Madison", "2019"));
    }

    @Test
    void testGetCourseSubject() {
        assertEquals("CPSC", newCourse.getCourseSubject());
    }

    @Test
    void testGetCourseNumber() {
        assertEquals("304", newCourse.getCourseNumber());
    }

    @Test
    void testGetCourseSection() {
        assertEquals("201", newCourse.getCourseSection());
    }

    @Test
    void testGetCourseTerm() {
        assertEquals("2019W", newCourse.getCourseTerm());
    }*/

}
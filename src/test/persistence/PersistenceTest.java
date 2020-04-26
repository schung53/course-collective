package persistence;

import model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class PersistenceTest {
    private static final String TEST_FILE = "./data/testSchedule.txt";
    private Writer testWriter;
    private Student student;

/*
    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        testWriter = new Writer(new File(TEST_FILE));
        student = new Student("jmadison44", "password");
    }

    @Test
    void testWriteSchedule() {
        testWriter.write(student);
        testWriter.close();

        try {
            List<String> fileContents = Reader.readFile(new File(TEST_FILE));
            assertEquals("James Madison", fileContents.get(0));
            assertEquals("", fileContents.get(1));
            assertEquals("SUBJECT | COURSE NO. | SECTION | TERM", fileContents.get(2));
            assertEquals("CPSC | 304 | 201 | 2019W", fileContents.get(3));

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
*/

}

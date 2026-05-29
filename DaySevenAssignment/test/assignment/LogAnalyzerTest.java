package assignment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class LogAnalyzerTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {

        File folder = new File("resources");

        if (!folder.exists()) {
            folder.mkdirs();
        }

        File summary = new File("resources/summary.txt");

        if (summary.exists()) {
            summary.delete();
        }

        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void restore() {
        System.setOut(originalOut);
    }

    /** Well-formed log prints a success message and writes a matching summary. */
    @Test
    void exec001() throws IOException {

        String expectedFile = Files.readString(Path.of("test/resources/exec001/summary.txt"));
        String file = "test/resources/exec001/server.log";

        LogAnalyzer.main(new String[] { file });

        String expectedConsole = "Analysis complete. Summary written to summary.txt" + System.lineSeparator();
        assertEquals(expectedConsole, outputStream.toString());

        String summaryFile = Files.readString(Path.of("resources/summary.txt"));
        assertEquals(expectedFile, summaryFile);
    }

    /** Info-only log leaves the error messages section empty. */
    @Test
    void exec002() throws IOException {

        String expectedFile = Files.readString(Path.of("test/resources/exec002/summary.txt"));
        String file = "test/resources/exec002/server.log";

        LogAnalyzer.main(new String[] { file });

        String expectedConsole = "Analysis complete. Summary written to summary.txt" + System.lineSeparator();
        assertEquals(expectedConsole, outputStream.toString());

        String summaryFile = Files.readString(Path.of("resources/summary.txt"));
        assertEquals(expectedFile, summaryFile);
    }

    /** A single log entry makes the earliest and latest timestamps the same. */
    @Test
    void exec003() throws IOException {

        String expectedFile = Files.readString(Path.of("test/resources/exec003/summary.txt"));
        String file = "test/resources/exec003/server.log";

        LogAnalyzer.main(new String[] { file });

        String expectedConsole = "Analysis complete. Summary written to summary.txt" + System.lineSeparator();
        assertEquals(expectedConsole, outputStream.toString());

        String summaryFile = Files.readString(Path.of("resources/summary.txt"));
        assertEquals(expectedFile, summaryFile);
    }

    /** Out-of-order timestamps still return the correct earliest and latest values. */
    @Test
    void exec004() throws IOException {

        String expectedFile = Files.readString(Path.of("test/resources/exec004/summary.txt"));
        String file = "test/resources/exec004/server.log";

        LogAnalyzer.main(new String[] { file });

        String expectedConsole = "Analysis complete. Summary written to summary.txt" + System.lineSeparator();
        assertEquals(expectedConsole, outputStream.toString());

        String summaryFile = Files.readString(Path.of("resources/summary.txt"));
        assertEquals(expectedFile, summaryFile);
    }

    /** Empty log still writes a summary with zero counts. */
    @Test
    void exec005() throws IOException {

        String expectedFile = Files.readString(Path.of("test/resources/exec005/summary.txt"));
        String file = "test/resources/exec005/server.log";

        LogAnalyzer.main(new String[] { file });

        String expectedConsole = "Analysis complete. Summary written to summary.txt" + System.lineSeparator();
        assertEquals(expectedConsole, outputStream.toString());

        String summaryFile = Files.readString(Path.of("resources/summary.txt"));
        assertEquals(expectedFile, summaryFile);
    }

    /** Messages containing a colon or bracket are kept intact. */
    @Test
    void exec006() throws IOException {

        String expectedFile = Files.readString(Path.of("test/resources/exec006/summary.txt"));
        String file = "test/resources/exec006/server.log";

        LogAnalyzer.main(new String[] { file });

        String expectedConsole = "Analysis complete. Summary written to summary.txt" + System.lineSeparator();
        assertEquals(expectedConsole, outputStream.toString());

        String summaryFile = Files.readString(Path.of("resources/summary.txt"));
        assertEquals(expectedFile, summaryFile);
    }

    /** Lines with an invalid log level are skipped while valid lines are still counted. */
    @Test
    void exec007() throws IOException {

        String expectedFile = Files.readString(Path.of("test/resources/exec007/summary.txt"));
        String file = "test/resources/exec007/server.log";

        LogAnalyzer.main(new String[] { file });

        String expectedConsole = "Skipping malformed line: [2024-08-01 10:00:02] CRITICAL: not a real level" + System.lineSeparator()
            + "Analysis complete. Summary written to summary.txt" + System.lineSeparator();

        assertEquals(expectedConsole, outputStream.toString());

        String summaryFile = Files.readString(Path.of("resources/summary.txt"));
        assertEquals(expectedFile, summaryFile);
    }

    /** Lines without timestamp brackets are skipped while valid lines are still counted. */
    @Test
    void exec008() throws IOException {

        String expectedFile = Files.readString(Path.of("test/resources/exec008/summary.txt"));
        String file = "test/resources/exec008/server.log";

        LogAnalyzer.main(new String[] { file });

        String expectedConsole = "Skipping malformed line: 2024-08-02 11:00:02 WARN: this line has no brackets" + System.lineSeparator()
            + "Analysis complete. Summary written to summary.txt" + System.lineSeparator();

        assertEquals(expectedConsole, outputStream.toString());

        String summaryFile = Files.readString(Path.of("resources/summary.txt"));
        assertEquals(expectedFile, summaryFile);
    }

    /** Lines without a message are skipped while valid lines are still counted. */
    @Test
    void exec009() throws IOException {

        String expectedFile = Files.readString(Path.of("test/resources/exec009/summary.txt"));
        String file = "test/resources/exec009/server.log";

        LogAnalyzer.main(new String[] { file });

        String expectedConsole = "Skipping malformed line: [2024-08-03 12:00:02] ERROR" + System.lineSeparator()
            + "Analysis complete. Summary written to summary.txt" + System.lineSeparator();

        assertEquals(expectedConsole, outputStream.toString());

        String summaryFile = Files.readString(Path.of("resources/summary.txt"));
        assertEquals(expectedFile, summaryFile);
    }

    /** Malformed lines are skipped, and the next valid line is still processed. */
    @Test
    void exec010() throws IOException {

        String expectedFile = Files.readString(Path.of("test/resources/exec010/summary.txt"));
        String file = "test/resources/exec010/server.log";

        LogAnalyzer.main(new String[] { file });

        String expectedConsole = "Skipping malformed line: some completely malformed line" + System.lineSeparator()
            + "Analysis complete. Summary written to summary.txt" + System.lineSeparator();

        assertEquals(expectedConsole, outputStream.toString());

        String summaryFile = Files.readString(Path.of("resources/summary.txt"));
        assertEquals(expectedFile, summaryFile);
    }

    /** Missing input file prints an error message and does not create a summary file. */
    @Test
    void exec011() {

        String file = "test/resources/exec011/server.log";

        LogAnalyzer.main(new String[] { file });

        String expectedConsole = "Log file not found." + System.lineSeparator();
        assertEquals(expectedConsole, outputStream.toString());

        File summary = new File("resources/summary.txt");
        assertFalse(summary.exists());
    }

    /** Read errors cannot be triggered reliably in this test setup, so this case is disabled. */
    @Disabled("Generic read IOException is unreachable via portable file input on modern JDKs")
    @Test
    void exec012() {

        String file = "test/resources/exec012";

        LogAnalyzer.main(new String[] { file });

        String expectedConsole = "Error reading file." + System.lineSeparator();
        assertEquals(expectedConsole, outputStream.toString());
    }

    /** Blocking the output path as a directory triggers the summary write error message. */
    @Test
    void exec013() {

        File blocker = new File("resources/summary.txt");

        if (blocker.exists()) {
            blocker.delete();
        }

        blocker.mkdir();

        String file = "test/resources/exec013/server.log";
        LogAnalyzer.main(new String[] { file });

        String expectedConsole =
            "Error writing summary file." + System.lineSeparator()
            + "Analysis complete. Summary written to summary.txt" + System.lineSeparator();

        assertEquals(expectedConsole, outputStream.toString());
        assertFalse(blocker.isFile());

        blocker.delete();
    }
}


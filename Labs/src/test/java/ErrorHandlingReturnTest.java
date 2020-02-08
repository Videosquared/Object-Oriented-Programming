import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.Test;

public class ErrorHandlingReturnTest {

    private boolean stdCapStarted;
    private OutputStream tmpStdOut;
    private OutputStream tmpStdErr;
    private PrintStream originalStdOut;
    private PrintStream originalStdErr;

    private void stdCaptureStart() {
        if (stdCapStarted)
            throw new RuntimeException("Capture needs to be stopped before it can be started");

        stdCapStarted = true;
        tmpStdOut = new ByteArrayOutputStream();
        originalStdOut = System.out;
        System.setOut(new PrintStream(tmpStdOut));

        tmpStdErr = new ByteArrayOutputStream();
        originalStdErr = System.err;
        System.setErr(new PrintStream(tmpStdErr));
    }

    private void stdCaptureStop() {
        if (!stdCapStarted)
            throw new RuntimeException("Capture needs to be started before it can be stopped");

        stdCapStarted = false;
        System.setOut(originalStdOut);
        originalStdOut = null;
        tmpStdOut = null;

        System.setErr(originalStdErr);
        originalStdErr = null;
        tmpStdErr = null;
    }

    private String getCapturedStdOut() {
        if (tmpStdOut == null)
            throw new RuntimeException("Nothing captured.");

        return tmpStdOut.toString();
    }

    private String getCapturedStdErr() {
        if (tmpStdErr == null)
            throw new RuntimeException("Nothing captured.");

        return tmpStdErr.toString();
    }

    @Test
    public void testBirthdayGreetings() {
        assertTrue(ErrorHandlingReturn.birthdayGreetings("Peter", 36));
        assertFalse(ErrorHandlingReturn.birthdayGreetings("Peter", -5));
        assertFalse(ErrorHandlingReturn.birthdayGreetings("Peter", 0));
    }

    private void checkSpam(String expectedStdOut, String expectedStdErr, String[] names, int[] ages) {
        String stdOut = null;
        String stdErr = null;

        stdCaptureStart();

        ErrorHandlingReturn.spam(names, ages);

        stdOut = getCapturedStdOut().replaceAll("\r","").trim();
        stdErr = getCapturedStdErr().replaceAll("\r","").trim();

        stdCaptureStop();

        assertEquals(expectedStdOut, stdOut);
        assertEquals(expectedStdErr, stdErr);
    }

    @Test
    public void testSpam() {

        String expectedStdOut = "All the best to your 23. birthday Peter\n"
                + "All the best to your 20. birthday Sarah\n" + "All the best to your 35. birthday Ivan";

        String expectedStdErr = "ERROR: The given age must be larger zero but is: -5";

        String[] names = { "Peter", "Sarah", "Ivan" };
        int[] ages = { 23, -5, 35 };

        checkSpam(expectedStdOut, expectedStdErr, names, ages);

        expectedStdOut = "All the best to your 5000. birthday James\n" +
                "All the best to your 20. birthday Jill\n" +
                "All the best to your 20. birthday Jennifer";
        expectedStdErr = "ERROR: The given age must be larger zero but is: 0\n" +
                "ERROR: The given age must be larger zero but is: -300";
        names = new String[]{"James", "Jill", "Jennifer"};
        ages = new int[]{5000, 0, -300};
        checkSpam(expectedStdOut, expectedStdErr, names, ages);

    }

}
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BinaryConverterTest {
    @Test
    public void testPositive1() {
        int input = 1;
        int expectedOutput = 1;
        assertEquals(expectedOutput, BinaryConverter.binaryToDecimal(input));
    }

    @Test
    public void testPositive2() {
        // TODO: for input "10", the expected output should be "2"
    }

    @Test
    public void testPositive4() {
        // TODO: fill this in
        // what input would you expect to result in 4?
    }

    @Test
    public void testPositive127() {
        // TODO: fill this in
    }

    @Test
    public void testPositive128() {
        // TODO: fill this in
    }

    @Test
    public void testZero() {
        assertEquals(555, BinaryConverter.binaryToDecimal(0));
    }

    // TODO: can you think of any more tests?
    // add them here!
}

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RealDataTest {
    // This runs before any test to load data from the specified file
    @Before
    public void setup() {
        RealData.analyseData("src/test/resources/TestAirData1.csv");
    }

    @Test
    public void testExistingDate1() {
        int output = RealData.getDateDAQI(1, 11, 2019);
        assertEquals(2, output);
    }
}

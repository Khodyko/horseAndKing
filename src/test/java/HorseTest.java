import org.example.HorseClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class HorseTest {
    @Test
    public void testCase1() {
        HorseClass horse = new HorseClass();
        String[] board = {
                "S..",
                "F..",
                "..."
        };
        int result = horse.getStepsCount(3, board);
        assertEquals(3, result);
    }

    @Test
    public void testCase2() {
        HorseClass horse = new HorseClass();
        String[] board = {
                "SF",
                ".."
        };
        int result = horse.getStepsCount(2, board);
        assertEquals(-1, result);
    }

    @Test
    public void testCase3() {
        HorseClass horse = new HorseClass();
        String[] board = {
                "S..K",
                "..G.",
                ".GK.",
                ".K.F"
        };
        int result = horse.getStepsCount(4, board);
        assertEquals(3, result);
    }
}

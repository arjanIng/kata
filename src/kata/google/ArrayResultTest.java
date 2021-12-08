package kata.google;

import static org.junit.Assert.assertEquals;

public class ArrayResultTest {
    
    public void verify(int[] expected, int[] solution) {
        assertEquals("Length not equal, expected " + showArray(expected) + ", but got " + showArray(solution), expected.length, solution.length);
        for (int i = 0; i < expected.length; i++) {
            assertEquals("Difference found, expected " + showArray(expected) + ", but got " + showArray(solution), expected[i], solution[i]);
        }
    }

    private String showArray(int[] array) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int value : array) {

            sb.append(value).append(", ");
        }
        sb.setLength(sb.length() > 2 ? sb.length() - 2 : sb.length());
        sb.append("]");
        return sb.toString();
    }

}

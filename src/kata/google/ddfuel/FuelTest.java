package kata.google.ddfuel;

import kata.google.ArrayResultTest;
import org.junit.Test;

public class FuelTest extends ArrayResultTest {

    @Test
    public void testZero() {
        int[][] input = new int[][]{
                {0}
        };
        int[] expected = new int[]{1, 1};
        verify(expected, Solution.solution(input));
    }

    @Test
    public void test1() {
        int[][] input = new int[][]{
                {0, 1},
                {0, 0},
        };
        int[] expected = new int[]{1, 1};
        verify(expected, Solution.solution(input));
    }

    @Test
    public void test5() {
        int[][] input = new int[][]{
                {0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };
        int[] expected = new int[]{1, 0, 0, 0, 1};
        verify(expected, Solution.solution(input));
    }

    @Test
    public void test51() {
        int[][] input = new int[][]{
                {0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };
        int[] expected = new int[]{1, 1, 0, 0, 2};
        verify(expected, Solution.solution(input));
    }

    @Test
    public void test52() {
        int[][] input = new int[][]{
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };
        int[] expected = new int[]{1, 1, 1, 0, 3};
        verify(expected, Solution.solution(input));
    }

    @Test
    public void test53() {
        int[][] input = new int[][]{
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };
        int[] expected = new int[]{1, 0, 0, 0, 0, 1};
        verify(expected, Solution.solution(input));
    }

    @Test
    public void test54() {
        int[][] input = new int[][]{
                {1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1}
        };
        int[] expected = new int[]{1, 1, 2};
        verify(expected, Solution.solution(input));
    }

    @Test
    public void testCase1() {
        int[][] input = new int[][]{
                {0, 1, 0, 0, 0, 1},
                {4, 0, 0, 3, 2, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0}
        };
        int[] expected = new int[]{0, 3, 2, 9, 14};
        verify(expected, Solution.solution(input));
    }

    @Test
    public void testCase2() {
        int[][] input = new int[][]{
                {0, 2, 1, 0, 0}, 
                {0, 0, 0, 3, 4}, 
                {0, 0, 0, 0, 0}, 
                {0, 0, 0, 0, 0}, 
                {0, 0, 0, 0, 0}};
        int[] expected = new int[]{7, 6, 8, 21};
        verify(expected, Solution.solution(input));
    }

    @Test
    public void testCaseLarge() {
        int[][] input = new int[][]{
                {0, 1, 0, 0, 0, 1, 1, 3, 0, 1},
                {4, 0, 0, 3, 2, 0, 0, 0, 0, 0},
                {1, 2, 0, 5, 0, 0, 0, 0, 0, 0},
                {0, 2, 0, 0, 4, 0, 5, 0, 0, 0},
                {0, 0, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        int[] expected = new int[]{14, 19, 42, 0, 14, 89};
        verify(expected, Solution.solution(input));
    }

    @Test
    public void generateOutputTest() {
        int[] expected = new int[]{0, 3, 2, 9, 14};

        Solution.Fraction[] fractions = new Solution.Fraction[4];
        fractions[0] = new Solution.Fraction(0, 1);
        fractions[1] = new Solution.Fraction(3, 14);
        fractions[2] = new Solution.Fraction(1, 7);
        fractions[3] = new Solution.Fraction(9, 14);

        int[] output = Solution.generateOutput(fractions, 14);

        verify(expected, output);
    }

}
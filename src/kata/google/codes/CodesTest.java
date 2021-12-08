package kata.google.codes;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class CodesTest {
    
    @Test
    public void test2() {
        assertEquals(0, Solution.solution(new int[] {1, 1}));
    }

    @Test
    public void test3() {
        assertEquals(1, Solution.solution(new int[] {1, 1, 1}));
    }

    @Test
    public void test4() {
        assertEquals(4, Solution.solution(new int[] {1, 1, 1, 1}));
    }

    @Test
    public void test1223() {
        assertEquals(1, Solution.solution(new int[] {1, 2, 2, 3}));
    }

    @Test
    public void test225() {
        assertEquals(0, Solution.solution(new int[] {2, 2, 5}));
    }
    
    @Test
    public void testCase2() {
        assertEquals(3, Solution.solution(new int[] {1, 2, 3, 4, 5, 6}));
    }

    @Test
    public void testCase2rev() {
        assertEquals(0, Solution.solution(new int[] {6, 5, 4, 3, 2, 1}));
    }

    @Test
    public void testLarge() {
        int[] input = new int[2000];
        Arrays.fill(input, 1);
        assertEquals(1331334000, Solution.solution(input));
    }

    @Test
    public void testLarge2() {
        int[] input = new int[2000];
        Arrays.fill(input, 999999);
        assertEquals(1331334000, Solution.solution(input));
    }

    @Test
    public void testLarge3() {
        int[] input = new int[2000];
        for (int i = 0; i < input.length; i++) {
            input[i] = i + 1;
        }
        assertEquals(40888, Solution.solution(input));
    }

    @Test
    public void testLarge3rev() {
        int[] input = new int[2000];
        for (int i = 0; i < input.length; i++) {
            input[i] = 2000 - i;
        }
        assertEquals(0, Solution.solution(input));
    }

}
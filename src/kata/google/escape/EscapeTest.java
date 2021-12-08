package kata.google.escape;

import kata.google.ArrayResultTest;
import org.junit.Test;

public class EscapeTest extends ArrayResultTest {

    @Test
    public void testZero() {
        int[][] times = new int[][] {};
        int times_limit = 1;
        int[] expected = new int[] { };

        verify(expected, Solution.solution(times, times_limit));
    }

    @Test
    public void testOne() {
        int[][] times = new int[][] {
                { 0, 1 },
                { 1, 0 }
        };
        int times_limit = 1;
        int[] expected = new int[] { };

        verify(expected, Solution.solution(times, times_limit));
    }

    @Test
    public void testTwo() {
        int[][] times = new int[][] {
                { 0, 1, 1 },
                { 1, 0, 1 },
                { 1, 1, 0 },
        };
        int times_limit = 2;
        int[] expected = new int[] { 0 };

        verify(expected, Solution.solution(times, times_limit));
    }

    @Test
    public void testOneNotEnoughTime() {
        int[][] times = new int[][] {
                { 0, 2 },
                { 1, 0 }
        };
        int times_limit = 1;
        int[] expected = new int[] { };

        verify(expected, Solution.solution(times, times_limit));
    }
    
    @Test
    public void testCase1() {
        int[][] times = new int[][] {
                {0, 1, 1, 1, 1}, 
                {1, 0, 1, 1, 1}, 
                {1, 1, 0, 1, 1}, 
                {1, 1, 1, 0, 1}, 
                {1, 1, 1, 1, 0}};
        int times_limit = 3;
        int[] expected = new int[] { 0, 1 };

        verify(expected, Solution.solution(times, times_limit));
    }

    @Test
    public void testCase2() {
        int[][] times = new int[][] {
                {0, 2, 2, 2, -1}, 
                {9, 0, 2, 2, -1}, 
                {9, 3, 0, 2, -1}, 
                {9, 3, 2, 0, -1}, 
                {9, 3, 2, 2, 0}
        };
        int times_limit = 1;
        int[] expected = new int[] { 1, 2 };

        verify(expected, Solution.solution(times, times_limit));
    }





}
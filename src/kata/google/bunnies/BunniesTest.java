package kata.google.bunnies;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BunniesTest {
    
    @Test
    public void testZero() {
        int[] entrances = new int[] {};
        int[] exits = new int[] {};
        int[][] path = new int[][] {};
        int expected = 0;
        
        assertEquals(expected, Solution.solution(entrances, exits, path));
    }

    @Test
    public void testOne() {
        int[] entrances = new int[] { 0 };
        int[] exits = new int[] { 1 };
        int[][] path = new int[][] {
                { 0, 1 },
                { 0, 0 }
        };
        int expected = 1;

        assertEquals(expected, Solution.solution(entrances, exits, path));
    }

    @Test
    public void testDoubleEntry() {
        int[] entrances = new int[] { 0, 1 };
        int[] exits = new int[] { 2 };
        int[][] path = new int[][] {
                { 0, 0, 2 },
                { 0, 0, 2 },
                { 0, 0, 0 }
        };
        int expected = 4;

        assertEquals(expected, Solution.solution(entrances, exits, path));
    }

    @Test
    public void testDoublePath() {
        int[] entrances = new int[] { 0 };
        int[] exits = new int[] { 2 };
        int[][] path = new int[][] {
                { 0, 1, 2 },
                { 0, 0, 1 },
                { 0, 0, 0 }
        };
        int expected = 3;

        assertEquals(expected, Solution.solution(entrances, exits, path));
    }

    @Test
    public void testDeadEnd() {
        int[] entrances = new int[] { 0 };
        int[] exits = new int[] { 2 };
        int[][] path = new int[][] {
                { 0, 1, 2 },
                { 0, 0, 0 },
                { 0, 0, 0 }
        };
        int expected = 2;

        assertEquals(expected, Solution.solution(entrances, exits, path));
    }

    @Test
    public void testNoPath() {
        int[] entrances = new int[] { 0 };
        int[] exits = new int[] { 2 };
        int[][] path = new int[][] {
                { 0, 1, 0 },
                { 0, 0, 0 },
                { 0, 0, 0 }
        };
        int expected = 0;

        assertEquals(expected, Solution.solution(entrances, exits, path));
    }

    @Test
    public void testZigZag() {
        int[] entrances = new int[] { 0 };
        int[] exits = new int[] { 3 };
        int[][] path = new int[][] {
                { 0, 0, 2, 0 },
                { 0, 0, 0, 3 },
                { 0, 1, 0, 0 },
                { 0, 0, 0, 0 }
        };
        int expected = 1;

        assertEquals(expected, Solution.solution(entrances, exits, path));
    }

    @Test
    public void testCase1() {
        int[] entrances = new int[] { 0 };
        int[] exits = new int[] { 3 };
        int[][] path = new int[][] {
                { 0, 7, 0, 0 },
                { 0, 0, 6, 0 },
                { 0, 0, 0, 8 },
                { 9, 0, 0, 0 }
        };
        int expected = 6;

        assertEquals(expected, Solution.solution(entrances, exits, path));
    }

    @Test
    public void testCase2() {
        int[] entrances = new int[] { 0, 1 };
        int[] exits = new int[] { 4, 5 };
        int[][] path = new int[][] {
                {0, 0, 4, 6, 0, 0}, 
                {0, 0, 5, 2, 0, 0}, 
                {0, 0, 0, 0, 4, 4}, 
                {0, 0, 0, 0, 6, 6}, 
                {0, 0, 0, 0, 0, 0}, 
                {0, 0, 0, 0, 0, 0}
        };
        int expected = 16;

        assertEquals(expected, Solution.solution(entrances, exits, path));
    }

}
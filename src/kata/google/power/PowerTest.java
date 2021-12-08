package kata.google.power;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PowerTest {
    
    @Test
    public void test1() {
        assertEquals("1", Solution.solution(new int[] { 1 }));
    }

    @Test
    public void test2() {
        assertEquals("2", Solution.solution(new int[] { 2 }));
    }

    @Test
    public void test12() {
        assertEquals("2", Solution.solution(new int[] { 1, 2 }));
    }

    @Test
    public void test1neg2() {
        assertEquals("1", Solution.solution(new int[] { 1, -2 }));
    }

    @Test
    public void testneg1neg2() {
        assertEquals("2", Solution.solution(new int[] { -1, -2 }));
    }

    @Test
    public void testneg1neg2neg3() {
        assertEquals("6", Solution.solution(new int[] { -1, -2, -3, }));
    }

    @Test
    public void test12neg3() {
        assertEquals("2", Solution.solution(new int[] { 1, 2, -3 }));
    }

    @Test
    public void test1neg23() {
        assertEquals("3", Solution.solution(new int[] { 1, -2, 3 }));
    }

    @Test
    public void testMisc() {
        assertEquals("1", Solution.solution(new int[] { -1, -1, -1, -1, -1 }));
    }

    @Test
    public void testEmpty() {
        assertEquals("0", Solution.solution(new int[] { }));
    }

    @Test
    public void testZero() {
        assertEquals("0", Solution.solution(new int[] { 0 }));
    }

    @Test
    public void testZeroes() {
        assertEquals("0", Solution.solution(new int[] { 0, 0 }));
    }

    @Test
    public void testNeg1() {
        assertEquals("-1", Solution.solution(new int[] { -1 }));
    }

    @Test
    public void testNeg2() {
        assertEquals("-2", Solution.solution(new int[] { -2 }));
    }

    @Test
    public void testNeg4zero() {
        assertEquals("0", Solution.solution(new int[] { -4, 0 }));
    }

    @Test
    public void testNeg1neg1neg1() {
        assertEquals("1", Solution.solution(new int[] { -1, -1, -1 }));
    }

    @Test
    public void testCase1() {
        assertEquals("8", Solution.solution(new int[] { 2, 0, 2, 2, 0 }));
    }

    @Test
    public void testCase2() {
        assertEquals("60", Solution.solution(new int[] { -2, -3, 4, -5 }));
    }

    @Test
    public void testCaseLarge() {
        int[] large = new int[50];
        StringBuilder expected = new StringBuilder("1");
        for (int i = 0; i < large.length; i++) {
            large[i] = 1000;
            expected.append("000");
        }
        assertEquals(expected.toString(), Solution.solution(large));
    }


}
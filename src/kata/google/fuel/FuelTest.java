package kata.google.fuel;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FuelTest {
    
    @Test
    public void testZero() {
        assertEquals(0, Solution.solution("0"));
    }

    @Test
    public void test1() {
        assertEquals(0, Solution.solution("1"));
    }

    @Test
    public void test2() {
        assertEquals(1, Solution.solution("2"));
    }

    @Test
    public void test3() {
        assertEquals(2, Solution.solution("3"));
    }

    @Test
    public void test4() {
        assertEquals(2, Solution.solution("4"));
    }

    @Test
    public void test5() {
        assertEquals(3, Solution.solution("5"));
    }

    @Test
    public void test15() {
        assertEquals(5, Solution.solution("15"));
    }

    @Test
    public void test23() {
        assertEquals(6, Solution.solution("23"));
    }

    @Test
    public void test39() {
        assertEquals(7, Solution.solution("39"));
    }

    @Test
    public void test47() {
        assertEquals(7, Solution.solution("47"));
    }

    @Test
    public void test768() {
        assertEquals(10, Solution.solution("768"));
    }

    @Test
    public void testLarge() {
        StringBuilder input = new StringBuilder(309);
        for (int i = 0; i < 308; i++) {
            input.append("9");
        }
        assertEquals(1266, Solution.solution(input.toString()));
    }

}
package kata.google.lambs;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LambTest {

    @Test
    public void test1() {
        assertEquals(0, Solution.solution(1));
    }

    @Test
    public void test2() {
        assertEquals(1, Solution.solution(2));
    }

    @Test
    public void test10() {
        assertEquals(1, Solution.solution(10));
    }

    @Test
    public void test143() {
        assertEquals(3, Solution.solution(143));
    }


}
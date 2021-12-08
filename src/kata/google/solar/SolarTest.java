package kata.google.solar;

import kata.google.ArrayResultTest;
import org.junit.Test;

public class SolarTest extends ArrayResultTest {

    @Test
    public void test0() {
        verify(new int[]{}, Solution.solution(0));
    }

    @Test
    public void test1() {
        verify(new int[]{1}, Solution.solution(1));
    }

    @Test
    public void test2() {
        verify(new int[]{1, 1}, Solution.solution(2));
    }

    @Test
    public void test3() {
        verify(new int[]{1, 1, 1}, Solution.solution(3));
    }

    @Test
    public void test4() {
        verify(new int[]{4}, Solution.solution(4));
    }

    @Test
    public void test12() {
        verify(new int[]{9, 1, 1, 1}, Solution.solution(12));
    }

    @Test
    public void test15324() {
        verify(new int[]{15129, 169, 25, 1}, Solution.solution(15324));
    }

}
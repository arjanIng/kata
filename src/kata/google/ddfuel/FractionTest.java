package kata.google.ddfuel;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FractionTest {

    @Test
    public void testZero() {
        Solution.Fraction fraction = new Solution.Fraction(0, 1);
        assertEquals("0", fraction.toString());
        assertTrue(fraction.isZero());
    }

    @Test
    public void testWholeNumber() {
        Solution.Fraction fraction = new Solution.Fraction(1, 1);
        assertEquals("1", fraction.toString());
    }

    @Test
    public void testNormalize() {
        Solution.Fraction fraction = new Solution.Fraction(2, 4);
        assertEquals("1/2", fraction.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDivideByZero() {
        new Solution.Fraction(1, 0);
    }

    @Test
    public void testAdd() {
        Solution.Fraction fraction = new Solution.Fraction(1, 2);
        fraction = fraction.add(new Solution.Fraction(3, 4));
        
        assertEquals("5/4", fraction.toString());
    }

    @Test
    public void testAdd2() {
        Solution.Fraction fraction = new Solution.Fraction(1, 2);
        fraction = fraction.add(new Solution.Fraction(-3, 4));

        assertEquals("-1/4", fraction.toString());
    }

    @Test
    public void testNegative() {
        Solution.Fraction fraction = new Solution.Fraction(1, 2);
        fraction = fraction.negate();

        assertEquals("-1/2", fraction.toString());
    }

    @Test
    public void testSubtract() {
        Solution.Fraction fraction = new Solution.Fraction(0, 0);
        fraction = fraction.subtract(new Solution.Fraction(1, 2));

        assertEquals("-1/2", fraction.toString());
    }

    @Test
    public void testSubtract2() {
        Solution.Fraction fraction = new Solution.Fraction(-1, 2);
        fraction = fraction.subtract(new Solution.Fraction(1, 2));

        assertEquals("-1", fraction.toString());
    }

    @Test
    public void testMultiply() {
        Solution.Fraction fraction = new Solution.Fraction(1, 2);
        fraction = fraction.multiply(new Solution.Fraction(3, 4));

        assertEquals("3/8", fraction.toString());
    }

    @Test
    public void testReciprocal() {
        Solution.Fraction fraction = new Solution.Fraction(-2, 3);
        fraction = fraction.reciprocal();
        assertEquals("-3/2", fraction.toString());
    }

    @Test
    public void testDivide() {
        Solution.Fraction fraction = new Solution.Fraction(1, 2);
        fraction = fraction.divide(new Solution.Fraction(3, 4));

        assertEquals("2/3", fraction.toString());
    }

    @Test
    public void testDivide2() {
        Solution.Fraction fraction = new Solution.Fraction(1, 2);
        fraction = fraction.divide(new Solution.Fraction(-3, 4));

        assertEquals("-2/3", fraction.toString());
    }

    @Test
    public void testFindlcm() {
        int[] numbers = new int[] {1, 14, 7, 14};
        assertEquals(14, Solution.Fraction.lcm(numbers));
    }

    @Test
    public void testFindlcm2() {
        int[] numbers = new int[] {2, 3};
        assertEquals(6, Solution.Fraction.lcm(numbers));
    }

    @Test
    public void testFindlcm3() {
        int[] numbers = new int[] {5, 7};
        assertEquals(35, Solution.Fraction.lcm(numbers));
    }

    @Test
    public void testFindlcm4() {
        int[] numbers = new int[] {50, 25, 25, 100, 50, 100};
        assertEquals(100, Solution.Fraction.lcm(numbers));
    }


}
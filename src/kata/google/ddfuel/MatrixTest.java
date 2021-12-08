package kata.google.ddfuel;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class MatrixTest {
    
    private final static int[][] TEST_INPUT = new int[][]{
            {0, 1, 0, 0, 0, 1},
            {4, 0, 0, 3, 2, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0}
    };
    
    private int terminal;
    
    private Solution.Fraction f(int n, int d) {
        return new Solution.Fraction(n, d);
    }
    
    @Test
    public void testDeterminant() {
        Solution.Fraction[][] testData = {
                {f(1, 1), f(0, 1), f(4, 1), f(-6, 1)},
                {f(2, 1), f(5, 1), f(0, 1), f(3, 1)},
                {f(-1, 1), f(2, 1), f(3, 1), f(5, 1)},
                {f(2, 1), f(1, 1), f(-2, 1), f(3, 1)},
        };
        
        Solution.Matrix m = new Solution.Matrix(testData);
        assertEquals(new Solution.Fraction(318, 1), m.determinant());
    }

    @Test
    public void testCofactors() {
        Solution.Fraction[][] testData = {
                {f(1, 1), f(0, 1), f(4, 1), f(-6, 1)},
                {f(2, 1), f(5, 1), f(0, 1), f(3, 1)},
                {f(-1, 1), f(2, 1), f(3, 1), f(5, 1)},
                {f(2, 1), f(1, 1), f(-2, 1), f(3, 1)},
        };
        String expected = "74, -26, 52, -6\n" +
                "-38, 95, -31, -27\n" +
                "12, -30, 60, 42\n" +
                "166, -97, 35, 51\n";

        Solution.Matrix m = new Solution.Matrix(testData);
        assertEquals(expected, m.cofactors().toString());
    }

    @Test
    public void testCase() {
        String expected = "0, 1/2, 0, 0, 0, 1/2\n" +
                "4/9, 0, 0, 1/3, 2/9, 0\n" +
                "0, 0, 0, 0, 0, 0\n" +
                "0, 0, 0, 0, 0, 0\n" +
                "0, 0, 0, 0, 0, 0\n" +
                "0, 0, 0, 0, 0, 0\n";
        Solution.Matrix m = Solution.Matrix.fromTestInput(TEST_INPUT);
        assertEquals(expected, m.toString());
    }

    @Test
    public void testRearrange() {
        String expected = "0, 0, 0, 0, 0, 0\n" +
                "0, 0, 0, 0, 0, 0\n" +
                "0, 0, 0, 0, 0, 0\n" +
                "0, 0, 0, 0, 0, 0\n" +
                "0, 0, 0, 1/2, 0, 1/2\n" +
                "0, 1/3, 2/9, 0, 4/9, 0\n";
        Solution.Matrix m = Solution.Matrix.fromTestInput(TEST_INPUT);
        Solution.Matrix mr = m.rearrange(findTerminalOrder(m));
        assertEquals(expected, mr.toString());
    }

    @Test
    public void testGetSubMatrix() {
        String expected = "0, 0, 0, 1/2\n" +
                        "0, 1/3, 2/9, 0\n";
        Solution.Matrix m = Solution.Matrix.fromTestInput(TEST_INPUT);
        Solution.Matrix mr = m.rearrange(findTerminalOrder(m));
        Solution.Matrix r = mr.subMatrix(terminal, 0, 6, terminal);
        assertEquals(expected, r.toString());
    }

    @Test
    public void testGetQ() {
        String expected = "0, 1/2\n" +
                        "4/9, 0\n";
        Solution.Matrix m = Solution.Matrix.fromTestInput(TEST_INPUT);
        Solution.Matrix mr = m.rearrange(findTerminalOrder(m));
        Solution.Matrix q = mr.subMatrix(terminal, terminal, 6, 6);
        assertEquals(expected, q.toString());
    }

    @Test
    public void testSubtract() {
        String expected = "1, -1/2\n" +
                        "-4/9, 1\n";
        Solution.Matrix m = Solution.Matrix.fromTestInput(TEST_INPUT);
        Solution.Matrix mr = m.rearrange(findTerminalOrder(m));
        Solution.Matrix q = mr.subMatrix(terminal, terminal, 6, 6);
        Solution.Matrix id = q.identity();
        Solution.Matrix sub = id.subtract(q);
        assertEquals(expected, sub.toString());
    }

    @Test
    public void testIdentity() {
        int[][] input = new int[][]{
                {0, 1, 0},
                {4, 0, 0},
                {0, 2, 3}
        };
        String expected = "1, 0, 0\n" +
                "0, 1, 0\n" +
                "0, 0, 1\n";

        Solution.Matrix m = Solution.Matrix.fromTestInput(input);
        Solution.Matrix id = m.identity();
        assertEquals(expected, id.toString());
    }

    @Test
    public void testInverse() {
        String expected = "9/7, 9/14\n" +
                "4/7, 9/7\n";
        Solution.Matrix m = Solution.Matrix.fromTestInput(TEST_INPUT);
        Solution.Matrix mr = m.rearrange(findTerminalOrder(m));
        Solution.Matrix q = mr.subMatrix(terminal, terminal, 6, 6);
        Solution.Matrix id = q.identity();
        Solution.Matrix sub = id.subtract(q);
        Solution.Matrix inverted = sub.inverse();
        
        assertEquals(expected, inverted.toString());
    }

    @Test
    public void testProduct() {
        String expected = "0, 3/14, 1/7, 9/14\n" +
                "0, 3/7, 2/7, 2/7\n";
        Solution.Matrix m = Solution.Matrix.fromTestInput(TEST_INPUT);
        Solution.Matrix mr = m.rearrange(findTerminalOrder(m));
        Solution.Matrix q = mr.subMatrix(terminal, terminal, 6, 6);
        Solution.Matrix r = mr.subMatrix(terminal, 0, 6, terminal);
        Solution.Matrix id = q.identity();
        Solution.Matrix sub = id.subtract(q);
        Solution.Matrix inverted = sub.inverse();
        Solution.Matrix product = inverted.product(r);

        assertEquals(expected, product.toString());
    }

    @Test
    public void testFactorial() {
        String expected = "0, 3/14, 1/7, 9/14\n" +
                "0, 3/7, 2/7, 2/7\n";
        Solution.Matrix m = Solution.Matrix.fromTestInput(TEST_INPUT);
        Solution.Matrix mr = m.rearrange(findTerminalOrder(m));
        Solution.Matrix q = mr.subMatrix(terminal, terminal, 6, 6);
        Solution.Matrix r = mr.subMatrix(terminal, 0, 6, terminal);
        Solution.Matrix id = q.identity();
        Solution.Matrix sub = id.subtract(q);
        Solution.Matrix inverted = sub.inverse();
        Solution.Matrix product = inverted.product(r);

        assertEquals(expected, product.toString());
    }
    
    private List<Integer> findTerminalOrder(Solution.Matrix matrix) {
        List<Integer> index = matrix.findRowNumbers(Solution.Matrix::isTerminal);
        terminal = index.size();
        index.addAll(matrix.findRowNumbers(row -> !Solution.Matrix.isTerminal(row)));
        return index;
    }
    

}
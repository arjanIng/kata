package kata.google.ddfuel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class Solution {
    
    public static int[] solution(int[][] m) {
        if (m[0].length == 1) return new int[]{1, 1};

        Matrix probabilities = Matrix.fromTestInput(m);

        List<Integer> index = probabilities.findRowNumbers(Matrix::isTerminal);
        int terminal = index.size();
        if (terminal == probabilities.rows) return generateOutputAllEndstates(index.size());
        List<Integer> nonTerminating = probabilities.findRowNumbers(row -> !Matrix.isTerminal(row));
        index.addAll(nonTerminating);

        Matrix rearranged = probabilities.rearrange(index);

        Matrix qpart = rearranged.subMatrix(terminal, terminal, rearranged.rows, rearranged.columns);
        Matrix rpart = rearranged.subMatrix(terminal, 0, rearranged.rows, terminal);

        Matrix idMinusQ = qpart.identity().subtract(qpart);
        Matrix inverse = idMinusQ.inverse();
        Matrix product = inverse.product(rpart);

        Fraction[] result = product.data[0];
        int[] denominators = new int[result.length];
        for (int i = 0; i < result.length; i++) denominators[i] = result[i].getDenominator();

        int gcd = Fraction.lcm(denominators);

        return generateOutput(result, gcd);
    }

    public static int[] generateOutputAllEndstates(int size) {
        int[] output = new int[size + 1];
        output[0] = 1;
        output[size] = 1;
        return output;
    }

    public static int[] generateOutput(Fraction[] result, int gcd) {
        int[] output = new int[result.length + 1];
        for (int i = 0; i < result.length; i++) {
            output[i] = result[i].getNumerator() * (gcd / result[i].getDenominator());
        }
        output[output.length - 1] = gcd;
        return output;
    }

    static class Matrix {
        private final int rows;
        private final int columns;
        private final Fraction[][] data;

        public Matrix(Fraction[][] input) {
            this.rows = input.length;
            this.columns = input[0].length;
            this.data = input.clone();
        }

        public Matrix(int rows, int columns) {
            this.rows = rows;
            this.columns = columns;
            this.data = new Fraction[rows][columns];
            initZero();
        }

        public static Matrix fromTestInput(int[][] input) {
            Matrix converted = new Matrix(input.length, input[0].length);
            int[] denominators = new int[input.length];
            converted.inspect((r, c) -> denominators[r] += input[r][c]);
            return converted.transform((r, c) -> new Fraction(input[r][c], denominators[r]));
        }

        public static boolean isTerminal(Fraction[] row) {
            return Arrays.stream(row).allMatch(Fraction::isZero);
        }

        public Matrix subMatrix(int r1, int c1, int r2, int c2) {
            return new Matrix(r2 - r1, c2 - c1).transform((r, c) -> data[r1 + r][c1 + c]);
        }

        public Matrix identity() {
            return transform((r, c) -> r.equals(c) ? Fraction.ONE : Fraction.ZERO);
        }

        public Matrix subtract(Matrix other) {
            return transform((r, c) -> data[r][c].subtract(other.data[r][c]));
        }

        public Matrix multiply(Fraction f) {
            return transform((r, c) -> data[r][c].multiply(f));
        }

        public Matrix rearrange(List<Integer> index) {
            return transform((r, c) -> data[index.get(r)][index.get(c)]);
        }

        public Matrix transpose() {
            return transform((r, c) -> data[c][r]);
        }

        public Matrix product(Matrix b) {
            assert (columns == b.rows);
            Matrix product = new Matrix(rows, b.columns);
            product = product.transform((r, c) -> {
                Fraction result = Fraction.ZERO;
                for (int n = 0; n < columns; n++) {
                    result = result.add(data[r][n].multiply(b.data[n][c]));
                }
                return result;
            });
            return product;
        }

        private Matrix withoutRowColumn(int row, int column) {
            Matrix nm = new Matrix(rows - 1, columns - 1);
            nm = nm.transform((r, c) -> {
                int rr = r < row ? r : r + 1;
                int cc = c < column ? c : c + 1;
                return data[rr][cc];
            });
            return nm;
        }

        public Matrix cofactors() {
            Matrix cofactors = new Matrix(rows, columns);
            if (rows == 1) return new Matrix(this.data);
            if (rows == 2) return cofactors.transform((r, c) -> data[r][c].multiply(detSign(r, c))).transpose();
            return cofactors.transform((r, c) -> withoutRowColumn(r, c).determinant().multiply(detSign(r, c)));
        }

        public Fraction determinant() {
            assert (rows == columns);
            if (rows == 1) return data[0][0];
            if (rows == 2) return (data[0][0].multiply(data[1][1])).subtract(data[0][1].multiply(data[1][0]));

            Fraction result = Fraction.ZERO;
            Fraction sign = Fraction.ONE;
            for (int i = 0; i < columns; i++) {
                Matrix slice = withoutRowColumn(0, i);
                if (!data[0][i].isZero()) {
                    result = result.add(data[0][i].multiply(sign).multiply(slice.determinant()));
                }
                sign = sign.negate();
            }
            return result;
        }

        public Matrix inverse() {
            Fraction determinant = determinant();
            Matrix cofactors = cofactors();
            Matrix adjugate = cofactors.transpose();

            return adjugate.multiply(determinant.reciprocal());
        }

        public List<Integer> findRowNumbers(Predicate<Fraction[]> predicate) {
            List<Integer> foundRowIndexes = new ArrayList<>();
            for (int r = 0; r < rows; r++) {
                if (predicate.test(data[r])) foundRowIndexes.add(r);
            }
            return foundRowIndexes;
        }

        private Matrix transform(BiFunction<Integer, Integer, Fraction> cell) {
            Fraction[][] newData = new Fraction[rows][columns];
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < columns; c++) {
                    newData[r][c] = cell.apply(r, c);
                }
            }
            return new Matrix(newData);
        }

        private void inspect(BiConsumer<Integer, Integer> cell) {
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < columns; c++) {
                    cell.accept(r, c);
                }
            }
        }

        private Fraction detSign(int r, int c) {
            return (r + c) % 2 == 0 ? Fraction.ONE : Fraction.ONE.negate();
        }

        private void initZero() {
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < columns; c++) {
                    this.data[r][c] = Fraction.ZERO;
                }
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder(7);
            for (Fraction[] row : data) {
                for (Fraction fraction : row) {
                    sb.append(fraction).append(", ");
                }
                sb.setLength(sb.length() - 2);
                sb.append("\n");
            }

            return sb.toString();
        }
    }

    static class Fraction {
        public static final Fraction ZERO = new Fraction(0, 1);
        public static final Fraction ONE = new Fraction(1, 1);

        private final int num;
        private final int den;

        public Fraction(int numerator, int denominator) {
            if (denominator == 0 && numerator != 0) throw new IllegalArgumentException("Division by zero");
            if (numerator == 0) denominator = 1;

            if (denominator < 0) {
                numerator = -numerator;
                denominator = -denominator;
            }

            // normalize before storing
            int lcd = Math.abs(gcd(numerator, denominator));
            this.num = numerator / lcd;
            this.den = denominator / lcd;
        }

        public Fraction add(Fraction f) {
            return new Fraction(num * f.den + f.num * den, den * f.den);
        }

        public Fraction subtract(Fraction f) {
            return add(f.negate());
        }

        public Fraction reciprocal() {
            return new Fraction(den, num);
        }

        public Fraction multiply(Fraction f) {
            return new Fraction(num * f.num, den * f.den);
        }

        public Fraction divide(Fraction f) {
            return multiply(f.reciprocal());
        }

        public Fraction negate() {
            return new Fraction(-num, den);
        }

        private static int gcd(int num1, int num2) {
            return num2 == 0 ? num1 : gcd(num2, num1 % num2);
        }

        private static int lcm(int a, int b) {
            return a * (b / gcd(a, b));
        }

        public static int lcm(int[] input) {
            int result = input[0];
            for (int i = 1; i < input.length; i++) result = lcm(result, input[i]);
            return result;
        }

        public int getNumerator() {
            return num;
        }

        public int getDenominator() {
            return den;
        }

        public boolean isZero() {
            return num == 0;
        }

        @Override
        public String toString() {
            if (num == 0) return "0";
            if (den == 1) return num + "";
            return num + "/" + den;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Fraction fraction = (Fraction) o;
            return num == fraction.num &&
                    den == fraction.den;
        }

        @Override
        public int hashCode() {
            return Objects.hash(num, den);
        }

    }
}

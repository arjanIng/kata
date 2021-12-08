package kata.google.fuel;

import java.math.BigInteger;

public class Solution {
    private static final BigInteger ZERO = BigInteger.ZERO;
    private static final BigInteger ONE = BigInteger.ONE;
    private static final BigInteger TWO = BigInteger.valueOf(2);
    private static final BigInteger THREE = BigInteger.valueOf(3);
    
    public static int solution(String x) {
        BigInteger remaining = new BigInteger(x);
        int step = 0;
        while (remaining.compareTo(ONE) > 0) {
            step++;
            
            if (remaining.equals(THREE)) {
                remaining = remaining.subtract(ONE);
                continue;
            }
            
            if (remaining.and(ONE).equals(ZERO)) {
                remaining = remaining.shiftRight(1);
                continue;
            }

            if (remaining.and(TWO).equals(ZERO)) {
                remaining = remaining.subtract(ONE);
            } else {
                remaining = remaining.add(ONE);
            }
        }
        return step;
    }

}

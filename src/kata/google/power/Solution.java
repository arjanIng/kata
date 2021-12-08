package kata.google.power;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static String solution(int[] xs) {
        List<Integer> relevantCells = new ArrayList<>();
        int leastSignificantNegative = Integer.MIN_VALUE;
        int lsnIndex = -1;
        boolean oddNumberOfNegatives = false;
        for (int x : xs) {
            if (x != 0) {
                if (x < 0) {
                    oddNumberOfNegatives = !oddNumberOfNegatives;
                    if (x > leastSignificantNegative) {
                        leastSignificantNegative = x;
                        lsnIndex = relevantCells.size();
                    }
                }
                relevantCells.add(x);
            }
        }

        if (relevantCells.size() == 0) return "0";

        if (oddNumberOfNegatives && relevantCells.size() > 1) {
            relevantCells.remove(lsnIndex);
        }

        BigInteger product = BigInteger.valueOf(1);
        for (Integer p : relevantCells) {
            product = product.multiply(BigInteger.valueOf(p));
        }
        
        if (product.signum() < 0) {
            if (xs.length > 1) return "0";
        }
        return product.toString();
    }

}
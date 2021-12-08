package kata.google.lambs;

public class Solution {
    public static int solution(int total_lambs) {
        return divide(total_lambs, false) - divide(total_lambs, true);
    }

    public static int divide(int total_lambs, boolean generous) {
        int henchmenPaid = 1, remaining = total_lambs - 1, previous = 1, previous2 = 0;
        while (remaining > 0) {
            int payout = generous ? previous * 2 : previous + previous2;

            if (payout <= remaining) {
                henchmenPaid++;
                remaining -= payout;
            } else {
                remaining = 0;
            }
            previous2 = previous;
            previous = payout;
        }
        return henchmenPaid;
    }

}
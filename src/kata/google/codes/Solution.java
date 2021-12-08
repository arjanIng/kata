package kata.google.codes;

public class Solution {
    
    public static int solution(int[] l) {
        if (l.length < 3) return 0;
        int count = 0;
        for (int x = 0; x < l.length - 2; x++) {
            for (int y = x + 1; y < l.length - 1; y++) {
                if (l[y] % l[x] == 0) {
                    for (int z = y + 1; z < l.length; z++) {
                        if (l[z] % l[y] == 0) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }
}

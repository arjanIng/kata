package kata.google.escape;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solution {
    public static int[] solution(int[][] times, int times_limit) {
        int dim = times.length;
        
        if (dim < 3) return new int[0]; // no bunnies to resque
        
        int remaining = times_limit;
        List<Integer> bunniesCarried = new ArrayList<>();
        List<Integer> bunniesResqued = new ArrayList<>();
        
        
        
        
        int pos = 0;
        while (remaining > 0) {
            for (int c = 0; c < dim; c++) {
                if (c != pos) {
                    int time = times[pos][c];
                    pos = c;
                    if (pos > 0 && pos < dim - 1) {
                        bunniesCarried.add(pos - 1);
                    }
                    if (pos == dim - 1 && remaining >= 0) {
                        bunniesResqued.addAll(bunniesCarried);
                        bunniesCarried.clear();
                        if (bunniesResqued.size() == dim - 2) {
                            break;
                        }
                    }
                    remaining -= time;
                }
            }
        }
        bunniesResqued.sort(Comparator.naturalOrder());
        int[] resqued = new int[bunniesResqued.size()];
        for (int i = 0; i < resqued.length; i++) resqued[i] = bunniesResqued.get(i);
                
        return resqued;
    }
}
package kata.advent;

import kata.util.IOUtil;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class Crabs {
    
    List<Integer> positions = new ArrayList<>();
    
    public void crabs() {
        List<String> input = IOUtil.getResourceBody("crabs.txt");
        System.out.println(format("initial state: {}", input.get(0)));
        
        int minPos = Integer.MAX_VALUE, maxPos = Integer.MIN_VALUE;
        for (String snum : input.get(0).split(",")) {
            int num = Integer.parseInt(snum);
            minPos = Math.min(minPos, num);
            maxPos = Math.max(maxPos, num);
            positions.add(num);
        }
        
        int minOutcome = Integer.MAX_VALUE;
        
        for (int i = minPos; i <= maxPos; i++) {
            int fuelSpent = 0;
            for (Integer pos : positions) {
                int distance = Math.abs(pos - i);
                for (int f = 1; f <= distance; f++) {
                    fuelSpent += f;
                }
            }
            if (fuelSpent < minOutcome) {
                minOutcome = fuelSpent;
                System.out.println(format("Fastest to position %d, fuel spent %d", i, fuelSpent));
            }
        }
        
    }

    public static void main(String[] args) {
        Crabs crabs = new Crabs();
        crabs.crabs();
    }
}

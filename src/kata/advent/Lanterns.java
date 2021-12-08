package kata.advent;

import kata.util.IOUtil;

import java.util.List;

import static java.lang.String.format;

public class Lanterns {
    
    long[] numAtAge = new long[9];
    long numFishes = 0;
    
    public void lanterns() {
        List<String> input = IOUtil.getResourceBody("lantern.txt");
        System.out.println(format("initial state: %s", input.get(0)));
        
        for (String num : input.get(0).split(",")) {
            numAtAge[Integer.parseInt(num)]++;
            numFishes++;
        }
        
        for (int day = 0; day < 256; day++) {
            long numBorn = numAtAge[0];
            for (int i = 0; i < 8; i++) {
               numAtAge[i] = numAtAge[i + 1];
            }
            numAtAge[6] += numBorn;
            numAtAge[8] = numBorn;
            numFishes += numBorn;
            System.out.println(format("After %d days: %d", day + 1, numFishes));
        }
        
    }

    public static void main(String[] args) {
        Lanterns lanterns = new Lanterns();
        lanterns.lanterns();
    }


}

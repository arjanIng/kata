package kata.advent;

import kata.util.IOUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

/**
 *   aaa    000
 *  b   c  1   2
 *  b   c  1   2
 *   ddd    333
 *  e   f  4   5
 *  e   f  4   5
 *   ggg    666
 *   
 *   000           000    000           000     000    000    000    000
 *  1   2      2      2      2  1   2  1       1          2  1   2  1   2
 *  1   2      2      2      2  1   2  1       1          2  1   2  1   2
 *                 333    333    333    333     333           333    333
 *  4   5      5  4          5      5      5   4   5      5  4   5      5
 *  4   5      5  4          5      5      5   4   5      5  4   5      5
 *   666           666    666           666     666           666    666
 *   
 *      6     *2      5      5     *4      5       6     *3     *7      6
 *      
 * 0: 8
 * 1: 6
 * 2: 8
 * 3: 7
 * 4: 4
 * 5: 9
 * 6: 7
 */
public class Signal {
    
    public void signal() {
        List<String> input = IOUtil.getResourceBody("signal.txt");

        List<Integer> signatures = Arrays.asList(42, 17, 34, 39, 30, 37, 41, 25, 49, 45);
        long total = 0;
        for (String line : input) {
            String[] parts = line.split(" \\| ");
            String output = parts[1];

            for (char c : "abcdefg".toCharArray()) {
                output = output.replace("" + c, "" + parts[0].chars()
                        .filter(t -> t == c).count());
            }
            
            total += Integer.parseInt(Arrays.stream(output.split(" "))
                    .map(d -> d.chars().map(Character::getNumericValue)
                            .reduce(0, Integer::sum))
                    .map(signatures::indexOf).map(String::valueOf)
                    .collect(Collectors.joining()));
        }
        System.out.printf("Count: %d%n", total);
    }
    
    public static void main(String[] args) {
        Signal signal = new Signal();
        signal.signal();
    }

}

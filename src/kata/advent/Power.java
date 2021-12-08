package kata.advent;

import kata.util.IOUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.lang.String.format;

public class Power {

    int[] zeros;
    int[] ones;

    List<String> generatorInput = new ArrayList<>();
    List<String> scrubberInput = new ArrayList<>();

    private void populateZerosOnes(List<String> input) {
        zeros = new int[input.get(0).length()];
        ones = new int[input.get(0).length()];
        for (String line : input) {
            char[] chars = line.trim().toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == '0') {
                    zeros[i]++;
                } else if (chars[i] == '1') {
                    ones[i]++;
                } else {
                    throw new RuntimeException("wrong");
                }
            }
        }
    }

    private String findValue(List<String> input, boolean useMost) {
        int i = 0;
        while (input.size() > 1) {
            populateZerosOnes(input);
            int mostCommon = zeros[i] > ones[i] ? 0 : 1;
            int leastCommon = zeros[i] > ones[i] ? 1 : 0;
            for (Iterator<String> iter = input.iterator(); iter.hasNext(); ) {
                String s = iter.next();
                int test;
                if (useMost) {
                    test = mostCommon;
                } else {
                    test = leastCommon;
                }
                ;
                if (Integer.parseInt(s.substring(i, i + 1)) != test) {
                    iter.remove();
                }
            }
            i++;
        }
        return input.get(0);
    }

    public void power() {
        List<String> input = IOUtil.getResourceBody("power.txt");

        input.stream().forEach(s -> {
            generatorInput.add(s);
            scrubberInput.add(s);
        });

        String generator = findValue(generatorInput, true);
        String scrubber = findValue(scrubberInput, false);

        System.out.println(format("generator: {}, scrubber: {}", generator, scrubber));

        int igenerator = Integer.parseInt(generator, 2);
        int iscrubber = Integer.parseInt(scrubber, 2);

        System.out.println(format("igenerator: {}, iscrubber: {}, mul: {}", igenerator, iscrubber, igenerator * iscrubber));
    }
    
    public static void main(String[] args) {
        Power power = new Power();
        power.power();
    }

}

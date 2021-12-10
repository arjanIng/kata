package kata.advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Syntax {

    public void syntax(String inputFile) throws IOException {
        List<String> input = Files.lines(Paths.get(inputFile))
                .collect(Collectors.toList());
        
        Map<Character, Character> openingSymbols = new HashMap<>();
        openingSymbols.put(')', '(');
        openingSymbols.put(']', '[');
        openingSymbols.put('}', '{');
        openingSymbols.put('>', '<');
        
        Map<Character, Integer> errorPoints = new HashMap<>();
        errorPoints.put(')', 3);
        errorPoints.put(']', 57);
        errorPoints.put('}', 1197);
        errorPoints.put('>', 25137);

        Map<Character, Integer> incompletePoints = new HashMap<>();
        incompletePoints.put('(', 1);
        incompletePoints.put('[', 2);
        incompletePoints.put('{', 3);
        incompletePoints.put('<', 4);

        long errorScore = 0;
        List<Long> incompleteScores = new ArrayList<>();
        
        for (String line: input) {
            long incompleteScore = 0;
            boolean errorFound = false;
            Stack<Character> stack = new Stack<>();
            for (char c: line.toCharArray()) {
                if (openingSymbols.containsValue(c)) {
                    //opening symbol, always legal.
                    stack.push(c);
                } else if (openingSymbols.containsKey(c)) {
                    // closing symbol, check with stack.
                    if (stack.peek() == openingSymbols.get(c)) {
                        stack.pop();
                    } else {
                        errorScore += errorPoints.get(c);
                        errorFound = true;
                        break;
                    }
                }
            }
            if (!errorFound) {
                while (stack.size() > 0) {
                    char c = stack.pop();
                    incompleteScore *= 5;
                    incompleteScore += incompletePoints.get(c);
                }
                incompleteScores.add(incompleteScore);
            }
        }
        incompleteScores = incompleteScores.stream().sorted().collect(Collectors.toList());
        System.out.printf("Part 1: %d%n", errorScore);
        System.out.printf("Part 2: %d%n", incompleteScores.get(incompleteScores.size() / 2));
    }

    public static void main(String[] args) throws IOException {
        Syntax lava = new Syntax();
        lava.syntax("./src/kata/advent/syntax.txt");
    }

}

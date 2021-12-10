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
        
        Map<Character, Character> symbols = new HashMap<>();
        symbols.put('(', ')');
        symbols.put('[', ']');
        symbols.put('{', '}');
        symbols.put('<', '>');
        
        Map<Character, Integer[]> points = new HashMap<>();
        points.put(')', new Integer[] { 3, 1 } );
        points.put(']', new Integer[] { 57, 2} );
        points.put('}', new Integer[] { 1197, 3} );
        points.put('>', new Integer[] { 25137, 4 });

        long errorScore = 0;
        List<Long> incompleteScores = new ArrayList<>();
        
        for (String line: input) {
            long incompleteScore = 0;
            boolean errorFound = false;
            Stack<Character> stack = new Stack<>();
            
            for (char c: line.toCharArray()) {
                if (symbols.containsKey(c)) {
                    stack.push(symbols.get(c));
                } else {
                    if (stack.peek() == c) {
                        stack.pop();
                    } else {
                        errorScore += points.get(c)[0];
                        errorFound = true;
                        break;
                    }
                }
            }
            
            if (!errorFound) {
                while (stack.size() > 0) {
                    char c = stack.pop();
                    incompleteScore *= 5;
                    incompleteScore += points.get(c)[1];
                }
                incompleteScores.add(incompleteScore);
            }
        }
        incompleteScores.sort(null);
        System.out.printf("Part 1: %d%n", errorScore);
        System.out.printf("Part 2: %d%n", incompleteScores.get(incompleteScores.size() / 2));
    }

    public static void main(String[] args) throws IOException {
        Syntax lava = new Syntax();
        lava.syntax("./src/kata/advent/syntax.txt");
    }

}

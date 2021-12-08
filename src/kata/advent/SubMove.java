package kata.advent;

import kata.util.IOUtil;

import java.util.List;

import static java.lang.String.format;

public class SubMove {
    
    int pos = 0;
    int aim = 0;
    int depth = 0;
    
    public void subMove() {
        List<String> input = IOUtil.getResourceBody("submove.txt");
        
        for (String line : input) {
            String[] parts = line.split(" ");
            int amount = Integer.parseInt(parts[1]);
            switch(parts[0]) {
                case "forward":
                    pos += amount;
                    depth += amount * aim;
                    break;
                case "up":
                    aim -= amount;
                    break;
                case "down":
                    aim += amount;
                    break;
            }
            System.out.println(format("command: %s %s, pos: %i, depth: %i", parts[0], parts[1], pos, depth));
        }
        System.out.println(format("pos: %d", pos));
        System.out.println(format("depth: %d", depth));
        System.out.println(format("pos * depth: %d", pos * depth));
        
    }
    
    public void main(String[] args) {
        SubMove subMove = new SubMove();
        subMove.subMove();
    } 
    
}

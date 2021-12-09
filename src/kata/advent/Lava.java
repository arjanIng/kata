package kata.advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Lava {

    private int[][] map;

    public void lava(String inputFile) throws IOException {
        List<String> input = Files.lines(Paths.get(inputFile))
                .collect(Collectors.toList());

        map = input.stream().map(l -> l.chars()
                .map(Character::getNumericValue).toArray())
                .toArray(size -> new int[size][1]);

        int risk = 0;
        List<Integer> sizes = new ArrayList<>();

        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                if (map[y][x] < lowestNeighbor(x, y)) {
                    risk += map[y][x] + 1;
                    sizes.add(basinSize(x, y));
                }
            }
        }
        sizes.sort(Collections.reverseOrder());
        System.out.printf("Part 1: %d%n", risk);
        System.out.printf("Part 2: %d%n",
                sizes.get(0) * sizes.get(1) * sizes.get(2));
    }
    
    private int borders(int x, int y) {
        return (x < 0 || x >= map[0].length || y < 0 || y >= map.length) ? 9 : map[y][x];
    }

    private int lowestNeighbor(int x, int y) {
        int lowest = Integer.MAX_VALUE;
        lowest = Math.min(lowest, borders(x - 1, y));
        lowest = Math.min(lowest, borders(x + 1, y));
        lowest = Math.min(lowest, borders(x, y - 1));
        lowest = Math.min(lowest, borders(x, y + 1));
        return lowest;
    }

    private int basinSize(int x, int y) {
        if (borders(x, y) == 9) return 0;
        map[y][x] = 9; // mark as visited
        return basinSize(x - 1, y) +
                basinSize(x + 1, y) +
                basinSize(x, y - 1) +
                basinSize(x, y + 1) + 1;
    }

    public static void main(String[] args) throws IOException {
        Lava lava = new Lava();
        lava.lava("./src/kata/advent/lava.txt");
    }

}

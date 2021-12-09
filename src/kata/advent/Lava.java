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

        map = input.stream().map(line -> line.chars()
                .map(Character::getNumericValue).toArray())
                .toArray(size -> new int[size][0]);

        int risk = 0;
        List<Integer> sizes = new ArrayList<>();

        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[r].length; c++) {
                if (map[r][c] < lowestNeighbor(r, c)) {
                    risk += map[r][c] + 1;
                    sizes.add(basinSize(r, c));
                }
            }
        }
        sizes.sort(Collections.reverseOrder());
        System.out.printf("Part 1: %d%n", risk);
        System.out.printf("Part 2: %d%n",
                sizes.get(0) * sizes.get(1) * sizes.get(2));
    }

    private int borders(int r, int c) {
        return (r < 0 || r >= map.length ||
                c < 0 || c >= map[r].length) ? 9 : map[r][c];
    }

    private int lowestNeighbor(int r, int c) {
        int lowest = Integer.MAX_VALUE;
        lowest = Math.min(lowest, borders(r - 1, c));
        lowest = Math.min(lowest, borders(r + 1, c));
        lowest = Math.min(lowest, borders(r, c - 1));
        lowest = Math.min(lowest, borders(r, c + 1));
        return lowest;
    }

    private int basinSize(int r, int c) {
        if (borders(r, c) == 9) return 0;
        map[r][c] = 9; // mark as visited
        return 1 + basinSize(r - 1, c) +
                basinSize(r + 1, c) +
                basinSize(r, c - 1) +
                basinSize(r, c + 1);
    }

    public static void main(String[] args) throws IOException {
        Lava lava = new Lava();
        lava.lava("./src/kata/advent/lava.txt");
    }

}

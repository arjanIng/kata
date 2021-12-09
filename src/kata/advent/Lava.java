package kata.advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Lava {

    public void lava() throws IOException {
        List<String> input = Files.lines(Paths.get("./src/kata/advent/lava.txt")).collect(Collectors.toList());

        int dimy = input.size();
        int dimx = input.get(0).length();
        int[][] map = new int[dimy][dimx];
        for (int y = 0; y < dimy; y++) {
            String line = input.get(y);
            for (int x = 0; x < dimx; x++) {
                map[y][x] = Integer.parseInt("" + line.toCharArray()[x]);
            }
        }

        long risk = 0;
        List<Integer> sizes = new ArrayList<>();
        for (int y = 0; y < dimy; y++) {
            for (int x = 0; x < dimx; x++) {
                int lowPoint = lowest(surrounding(x, y, map));
                if (map[y][x] < lowPoint) {
                    risk += map[y][x] + 1;
                    int size = basinSize(x, y, map, 1);
                    System.out.printf("Found size: %d%n", size);
                    sizes.add(size);
                }
            }
        }
        System.out.printf("Risk: %d%n", risk);
        
        List<Integer> sorted = sizes.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        long mul = sorted.get(0) * sorted.get(1) * sorted.get(2);
        System.out.printf("Largest basins multiplied: %d%n", mul);
    }

    private int basinSize(int x, int y, int[][] map, int size) {
        if (x < 0 || x >= map[0].length || y < 0 || y >= map.length) return 0;
        if (map[y][x] == 9) return 0;
        map[y][x] = 9;
        size = basinSize(x - 1, y, map, size) +
                basinSize(x + 1, y, map, size) +
                basinSize(x, y - 1, map, size) +
                basinSize(x, y + 1, map, size) + 1;
        return size;
    }

    private int[] surrounding(int x, int y, int[][] map) {
        List<Integer> s = new ArrayList<>();
        if (x - 1 >= 0) s.add(map[y][x - 1]);
        if (x + 1 < map[0].length) s.add(map[y][x + 1]);
        if (y - 1 >= 0) s.add(map[y - 1][x]);
        if (y + 1 < map.length) s.add(map[y + 1][x]);
        return s.stream().mapToInt(i -> i).toArray();
    }

    private int lowest(int... values) {
        int lowest = Integer.MAX_VALUE;
        for (int value : values) {
            if (value < lowest) lowest = value;
        }
        return lowest;
    }

    public static void main(String[] args) throws IOException {
        Lava lava = new Lava();
        lava.lava();
    }

}

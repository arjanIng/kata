package kata.advent;

import kata.util.IOUtil;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class Vents {
    
    List<Vent> vents = new ArrayList<>();
    int[][] diagram;
    
    public void vents() {
        List<String> input = IOUtil.getResourceBody("vents.txt");
        
        int maxX = -1, maxY = -1;
        
        for (String line : input) {
            String points[] = line.split(" -> ");
            Vent vent = new Vent();
            String point1[] = points[0].split(",");
            vent.x1 = Integer.parseInt(point1[0]);
            vent.y1 = Integer.parseInt(point1[1]);
            String point2[] = points[1].split(",");
            vent.x2 = Integer.parseInt(point2[0]);
            vent.y2 = Integer.parseInt(point2[1]);
            vents.add(vent);
            maxX = Math.max(Math.max(maxX, vent.x1), vent.x2);
            maxY = Math.max(Math.max(maxY, vent.y1), vent.y2);
        }
        
        vents.stream().map(Vent::toString).forEach(System.out::println);
        
        diagram = new int[maxX + 1][maxY + 1];
        
        for (Vent vent : vents) {
            if (vent.y1 == vent.y2) {
                // horizontal
                for (int x = Math.min(vent.x1, vent.x2); x <= Math.max(vent.x1, vent.x2); x++) {
                    diagram[x][vent.y1]++;
                }
            } else if (vent.x1 == vent.x2) {
                // vertical
                for (int y = Math.min(vent.y1, vent.y2); y <= Math.max(vent.y1, vent.y2); y++) {
                    diagram[vent.x1][y]++;
                }
            } else {
                // diagonal
                int dirx = vent.x1 < vent.x2 ? 1 : -1;
                int diry = vent.y1 < vent.y2 ? 1 : -1;
                boolean endReached = false;
                for (int x = vent.x1, y = vent.y1; 
                     !endReached;
                     x += dirx, y += diry) {
                    diagram[x][y]++;
                    if (x == vent.x2) endReached = true;
                }
            }
        }

        printDiagram();
        int spots = findDangerSpots();
        System.out.println(format("spots: %d", spots));
    }
    
    private void printDiagram() {
        for (int y = 0; y < diagram.length; y++) {
            StringBuilder row = new StringBuilder();
            for (int x = 0; x < diagram[0].length; x++) {
                row.append(diagram[x][y] == 0 ? "." : diagram[x][y]);
            }
            System.out.println(row.toString());
        }
        System.out.println();
    }

    private int findDangerSpots() {
        int spots = 0;
        for (int y = 0; y < diagram.length; y++) {
            for (int x = 0; x < diagram[0].length; x++) {
                if (diagram[x][y] > 1) spots++;
            }
        }
        return spots;
    }

    class Vent {
        int x1, y1, x2, y2;
    }
    
    public static void main(String[] args) {
        Vents vents = new Vents();
        vents.vents();
    }
}

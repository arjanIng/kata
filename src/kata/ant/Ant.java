package kata.ant;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class Ant {
    private boolean[][] field;

    private int x;
    private int y;
    private Direction direction;
    
    Map<Integer, Direction> directions = new HashMap<>();

    public Ant(boolean[][] field) {
        this.field = field;

        directions.put(0, Direction.UP);
        directions.put(1, Direction.RIGHT);
        directions.put(2, Direction.DOWN);
        directions.put(3, Direction.LEFT);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void step() {
        boolean filled = field[x][y];
        this.field[x][y] = !filled;
        if (!filled) {
            this.direction = clockWise(this.direction);
        } else {
            this.direction = counterClockWise(this.direction);
        }
        
        switch (direction) {
            case UP:
                y++;
                if (y >= field.length) y = 0;
                return;
            case RIGHT:
                x++;
                if (x >= field.length) x = 0;
                return;
            case DOWN:
                y--;
                if (y < 0) y = field.length - 1;
                return;
            case LEFT:
                x--;
                if (x < 0) x = field.length - 1;
                return;
        }
    }
    
    private Direction clockWise(Direction in) {
        int dir = directions.entrySet().stream().filter((entry) -> entry.getValue().equals(in)).findFirst().get().getKey();
        return directions.get(dir < 3 ? dir + 1 : 0);
    }

    private Direction counterClockWise(Direction in) {
        int dir = directions.entrySet().stream().filter((entry) -> entry.getValue().equals(in)).findFirst().get().getKey();
        return directions.get(dir > 0 ? dir - 1 : 3);
    }

    public Direction getDirection() {
        return this.direction;
    }

    public boolean[][] getField() {
        return this.field;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return y;
    }

    public String printField() {
        StringBuilder b = new StringBuilder();
        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field.length; x++) {
                if (field[x][y] == true) {
                    b.append("#");
                } else {
                    b.append(" ");
                }
            }
            b.append("\n");
        }
        return b.toString();
    }

    public enum Direction {
        UP(0),
        RIGHT(1),
        DOWN(2),
        LEFT(3);

        private int num;

        Direction(int num) {
            this.num = num;
        }
    }
    
    public static void main(String... args) throws InterruptedException {
        boolean[][] field = new boolean[50][50];
        Ant a = new Ant(field);
        a.setDirection(Ant.Direction.UP);
        a.setLocation(25, 25);
        
        while (true) {
            for (int x = 0; x < 1000; x++) a.step();
            System.out.println(a.printField());
            System.out.flush();
            Thread.sleep(1000);
        }
        
    }
}

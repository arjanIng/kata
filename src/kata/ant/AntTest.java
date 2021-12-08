package kata.ant;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AntTest {
    
    private void clockWise(Ant.Direction startDirection, Ant.Direction expectedDirection, boolean expectedValue, int startX, int startY, int expectedX, int expectedY) {
        Ant a = new Ant(new boolean[3][3]);
        a.setDirection(startDirection);
        a.setLocation(startX,startY);

        a.step();
        assertEquals(expectedDirection, a.getDirection());
        assertEquals(expectedValue, a.getField()[1][1]);
        assertEquals("x is different", expectedX, a.getX());
        assertEquals("y is different", expectedY, a.getY());
    }

    private void counterClockWise(Ant.Direction startDirection, Ant.Direction expectedDirection, boolean expectedValue, int startX, int startY, int expectedX, int expectedY) {
        boolean[][] field = new boolean[3][3];
        field[1][1] = true;
        field[0][0] = true;
        Ant a = new Ant(field);
        
        a.setDirection(startDirection);
        a.setLocation(startX,startY);

        a.step();
        assertEquals(expectedDirection, a.getDirection());
        assertEquals(expectedValue, a.getField()[1][1]);
        assertEquals("x is different", expectedX, a.getX());
        assertEquals("y is different", expectedY, a.getY());
    }

    @Test
    public void testWhiteUpRight() {
        clockWise(Ant.Direction.UP, Ant.Direction.RIGHT, true, 1, 1, 2, 1);
        clockWise(Ant.Direction.UP, Ant.Direction.RIGHT, false, 0, 0, 1, 0);
    }

    @Test
    public void testWhiteRightDown() {
        clockWise(Ant.Direction.RIGHT, Ant.Direction.DOWN, true, 1, 1, 1, 0);
        clockWise(Ant.Direction.RIGHT, Ant.Direction.DOWN, false, 0, 0, 0, 2);
    }

    @Test
    public void testWhiteDownLeft() {
        clockWise(Ant.Direction.DOWN, Ant.Direction.LEFT, true, 1, 1, 0, 1);
        clockWise(Ant.Direction.DOWN, Ant.Direction.LEFT, false, 0, 0, 2, 0);
    }

    @Test
    public void testWhiteLeftUp() {
        clockWise(Ant.Direction.LEFT, Ant.Direction.UP, true, 1, 1, 1, 2);
        clockWise(Ant.Direction.LEFT, Ant.Direction.UP, false, 0, 0, 0, 1);
    }

    @Test
    public void testBlackUpLeft() {
        counterClockWise(Ant.Direction.UP, Ant.Direction.LEFT, false, 1, 1, 0, 1);
        counterClockWise(Ant.Direction.UP, Ant.Direction.LEFT, true, 0, 0, 2, 0);
    }
    
    @Test
    public void testToString() {
        boolean[][] field = new boolean[3][3];
        Ant a = new Ant(field);
        a.setDirection(Ant.Direction.UP);
        a.setLocation(1, 1);
        a.step();
        assertEquals("   \n # \n   \n", a.printField());
    }
    
    

}

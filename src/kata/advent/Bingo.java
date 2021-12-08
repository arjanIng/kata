package kata.advent;

import kata.util.IOUtil;
import kata.util.TriConsumer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.lang.String.format;

public class Bingo {
    List<Board> boards = new ArrayList<>();
    int[] numbers;
    
    public void bingo() {
        List<String> input = IOUtil.getResourceBody("bingo.txt");

        boolean firstLine = true;
        int boardrow = 0;
        Board currentBoard = null;
        for (String line : input) {
            if (firstLine) {
                String[] snumbers = line.split(",");
                numbers = new int[snumbers.length];
                for (int i = 0; i < snumbers.length; i++) {
                    numbers[i] = Integer.parseInt(snumbers[i]);
                }
                firstLine = false;
                continue;
            }
            if (line.trim().isEmpty()) continue;
            
            if (boardrow == 0) currentBoard = new Board();
            currentBoard.addLine(boardrow, line);
            boardrow++;
            if (boardrow == 5) {
                boards.add(currentBoard);
                boardrow = 0;
            }
        }
        
        System.out.println(format("Numbers: %d, Boards: %d", numbers.length, boards.size()));
        List<Board> notWon = new ArrayList<>();
        notWon.addAll(boards);
        for (int number : numbers) {
            for (Iterator<Board> iter = notWon.iterator(); iter.hasNext(); ) {
                Board board = iter.next();
                board.mark(number);
                if (board.isWin()) {
                    if (notWon.size() == 1) {
                        int sumUnmarked = board.countUnmarked();
                        System.out.println(format("Last board won at number: %d, sumunmarked: %d, mul: %d", number, sumUnmarked, number * sumUnmarked));
                    }
                    iter.remove();
                }
            }
        }
    }
    
    class Board {
        Field[][] fields = new Field[5][5];
        
        public void loopBoard(TriConsumer<Integer, Integer, Field> consumer) {
            for (int r = 0; r < 5; r++) {
                for (int c = 0; c < 5; c++) {
                    consumer.accept(r, c, fields[r][c]);
                }
            }
        }
        
        public void addLine(int row, String line) {
            String[] snumbers = line.trim().split("\\s+");
            for (int i = 0; i < 5; i++) {
                fields[row][i] = new Field();
                fields[row][i].number = Integer.parseInt(snumbers[i]);
            }
        }
        
        public void mark(int number) {
            loopBoard((r, c, f) -> { if (f.number == number) f.marked = true; });
        }
        
        public int countUnmarked() {
            List<Field> unMarked = new ArrayList<>();
            loopBoard((r, c, f) -> { if (!f.marked) {
                unMarked.add(f);
            }});
            return unMarked.stream().map(f -> f.number).reduce(0, (a, b) -> a + b);
        }
        
        public boolean isWin() {
            // check rows
            final boolean[] rowsNotAllMarked = new boolean[5];
            final boolean[] columnsNotAllMarked = new boolean[5];
            
            loopBoard((r, c, f) -> {
                if (!f.marked) {
                    rowsNotAllMarked[r] = true;
                    columnsNotAllMarked[c] = true;
                }
            });

            for (boolean b : rowsNotAllMarked) { if (!b) return true; }
            for (boolean b : columnsNotAllMarked) { if (!b) return true; }
            return false;
        }
    }
    
    class Field {
        int number;
        boolean marked;
    }
    
    public static void main(String[] args) {
        Bingo bingo = new Bingo();
        bingo.bingo();
    }
    
}

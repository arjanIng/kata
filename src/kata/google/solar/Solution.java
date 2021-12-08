package kata.google.solar;

public class Solution {

    public static int[] solution(int area) {
        int[] squarePanels;
        if (area == 0) return new int[]{};

        int largestSquare = findLargestSquare(area);
        int[] remaining = solution(area - largestSquare);
        squarePanels = new int[1 + remaining.length];
        squarePanels[0] = largestSquare;
        System.arraycopy(remaining, 0, squarePanels, 1, remaining.length);
        return squarePanels;
    }

    private static int findLargestSquare(int area) {
        int multiplier = 0, square = 0, previousSquare = 0;
        while (square <= area) {
            previousSquare = square;
            multiplier++;
            square = multiplier * multiplier;
        }
        return previousSquare;
    }
}

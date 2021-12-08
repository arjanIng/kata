package kata.google.bunnies;

import java.util.Stack;

public class Solution {
    private static final int INF = Integer.MAX_VALUE;

    public static int[][] normalizeNetwork(int[] entrances, int[] exits, int[][] network) {
        int dim = network.length + 2;
        int[][] normalized = new int[dim][dim];

        for (int y = 0; y < network.length; y++) {
            for (int x = 0; x < network.length; x++) {
                normalized[y + 1][x + 1] = network[y][x];
            }
        }

        for (Integer ent : entrances) normalized[0][ent + 1] = INF;
        for (Integer exi : exits) normalized[exi + 1][dim - 1] = INF;
        return normalized;
    }


    public static Stack<Integer> findPath(int[][] network, Stack<Integer> path) {
        if (path.peek() == network.length - 1) return path;

        for (int i = 0; i < network.length; i++) {
            int next = network[path.peek()][i];
            if (next > 0 && !path.contains(i)) {
                path.push(i);
                Stack<Integer> found = findPath(network, path);
                if (found != null) return found;
                path.pop();
            }
        }
        return null;
    }

    private static Stack<Integer> newOrigin() {
        Stack<Integer> origin = new Stack<>();
        origin.push(0);
        return origin;
    }

    public static int solution(int[] entrances, int[] exits, int[][] path) {
        if (path.length == 0) return 0;

        int throughput = 0;
        int[][] normalized = normalizeNetwork(entrances, exits, path);
        
        Stack<Integer> openPath;
        while ((openPath = findPath(normalized, newOrigin())) != null) {
            openPath.remove(0);
            int minCapacity = INF;
            int r = 0;
            for (int c : openPath) {
                minCapacity = Math.min(minCapacity, normalized[r][c]);
                r = c;
            }
            throughput += minCapacity;
            r = 0;
            for (int c : openPath) {
                normalized[r][c] -= minCapacity;
                normalized[c][r] += minCapacity;
                r = c;
            }
        }

        return throughput;
    }

}

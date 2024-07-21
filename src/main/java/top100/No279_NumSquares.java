package top100;

import java.util.Arrays;

public class No279_NumSquares {
    private static final int[][] memo = new int[101][10001];

    static {
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
    }

    public int numSquares(int n) {
        return dfs((int) Math.sqrt(n), n);
    }

    private int dfs(int i, int left) {
        if (i == 0) {
            return left == 0 ? 0 : Integer.MAX_VALUE;
        }

        if (memo[i][left] != -1) {
            return memo[i][left];
        }

        if (left < i * i) {
            return dfs(i - 1, left);
        }

        memo[i][left] = Math.min(dfs(i - 1, left), dfs(i, left - i * i) + 1);
        return memo[i][left];
    }


    public static void main(String[] args) {
        int n = 12;
        No279_NumSquares solution = new No279_NumSquares();
        System.out.println(solution.numSquares(n));
    }
}

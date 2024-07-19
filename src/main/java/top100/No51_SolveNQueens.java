package top100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class No51_SolveNQueens {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<List<String>>();
        int[] col = new int[n];
        boolean[] onPath = new boolean[n];
        boolean[] diag1 = new boolean[n * 2 - 1];
        boolean[] diag2 = new boolean[n * 2 - 1];

        dfs(0, n, col, onPath, diag1, diag2, res);
        return res;
    }

    private void dfs(int r, int n, int[] col, boolean[] onPath, boolean[] diag1, boolean[] diag2, List<List<String>> res) {
        if (r == n) {
            List<String> board = new ArrayList<>();
            for (int c : col) {
                char[] curr_row = new char[n];
                Arrays.fill(curr_row, '.');
                curr_row[c] = 'Q';
                board.add(new String(curr_row));
            }
            res.add(board);
            return;
        }

        for (int c = 0; c < n; c++) {
            int rc = r - c + n - 1;  // 防止 r - c 出现负值情况(导致出现错误)，故加上 n - 1
            if (!onPath[c] && !diag1[r + c] && !diag2[rc]) {
                col[r] = c;
                onPath[c] = diag1[r + c] = diag2[rc] = true;
                dfs(r + 1, n, col, onPath, diag1, diag2, res);
                onPath[c] = diag1[r + c] = diag2[rc] = false;
            }
        }
    }


    public static void main(String[] args) {
        int n = 4;
        No51_SolveNQueens sol = new No51_SolveNQueens();
        List<List<String>> res = sol.solveNQueens(n);
        System.out.println(res);
    }
}

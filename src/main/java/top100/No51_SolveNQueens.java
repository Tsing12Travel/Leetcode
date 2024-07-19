package top100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class No51_SolveNQueens {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
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


    public List<List<String>> solveNQueens2(int n) {
        List<List<String>> res = new ArrayList<>();
        char[][] board = new char[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        backtrack(0, board, res);
        return res;
    }

    private void backtrack(int row, char[][] board, List<List<String>> res) {
        int len = board.length;
        if (row == len) {
            res.add(boardToList(board));
            return;
        }

        for (int col = 0; col < len; col++) {
            if (isValid(row, col, board)) {
                board[row][col] = 'Q';
                backtrack(row + 1, board, res);
                board[row][col] = '.';
            }
        }
    }

    private List<String> boardToList(char[][] board) {
        List<String> list = new ArrayList<>();
        for (char[] row : board) {
            list.add(new String(row));
        }
        return list;
    }

    private boolean isValid(int row, int col, char[][] board) {
        int len = board.length;
        // 检查同一列是否存在 queen
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') return false;
        }

        // 检查主对角线是否存在 queen
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }

        // 检查副对角线是否存在 queen
        for (int i = row - 1, j = col + 1; i >= 0 && j < len; i--, j++) {
            if (board[i][j] == 'Q') return false;
        }

        return true;
    }


    public static void main(String[] args) {
        int n = 4;
        No51_SolveNQueens sol = new No51_SolveNQueens();
        List<List<String>> res = sol.solveNQueens2(n);
        System.out.println(res);
    }
}

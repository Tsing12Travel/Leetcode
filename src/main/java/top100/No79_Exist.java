package top100;

public class No79_Exist {
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0 || word == null || word.isEmpty()) return false;

        char[] words = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, words, i, j, 0)) return true;
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, char[] words, int i, int j, int start_index) {
        if (i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != words[start_index])
            return false;
        if (start_index == words.length - 1) return true;

        board[i][j] = '0';
        boolean res = dfs(board, words, i + 1, j, start_index + 1) ||
                dfs(board, words, i - 1, j, start_index + 1) ||
                dfs(board, words, i, j + 1, start_index + 1) ||
                dfs(board, words, i, j - 1, start_index + 1);
        board[i][j] = words[start_index];

        return res;
    }


    public static void main(String[] args) {
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        No79_Exist sol = new No79_Exist();
        System.out.println(sol.exist(board, "ABCCED"));
        System.out.println(sol.exist(board, "SEE"));
        System.out.println(sol.exist(board, "ABCB"));
    }
}

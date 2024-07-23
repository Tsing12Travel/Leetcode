package top100;

import java.util.Arrays;

public class No1143_longestCommonSubsequence {
    // 定义 f[i][j] 表示字符串 text1 的 [1, i] 区间和字符串 text2 的 [1, j] 区间的最长公共子序列长度（下标从 1 开始）
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] f = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    f[i][j] = f[i - 1][j - 1] + 1;
                } else {
                    f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
                }
            }
        }

        return f[m][n];
    }


    public int longestCommonSubsequence2(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        char[] a = text1.toCharArray();
        char[] b = text2.toCharArray();
        int[][] memo = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    memo[i][j] = 0;
                } else if (a[i - 1] == b[j - 1]) {
                    memo[i][j] = memo[i - 1][j - 1] + 1;
                } else {
                    memo[i][j] = Math.max(memo[i - 1][j], memo[i][j - 1]);
                }
            }
        }
        return memo[m][n];
    }


    // 递归搜索 + 保存计算结果 = 记忆化搜索
    private char[] x, y;
    private int[][] cache;

    public int longestCommonSubsequence3(String text1, String text2) {
        this.x = text1.toCharArray();
        this.y = text2.toCharArray();

        int m = x.length, n = y.length;
        cache = new int[m + 1][n + 1];

        for (int[] row : cache) {
            Arrays.fill(row, -1);  // -1 表示没有访问过
        }

        return dfs(m - 1, n - 1);
    }

    private int dfs(int i, int j) {
        if (i < 0 || j < 0) return 0;

        if (cache[i][j] != 0) return cache[i][j];

        if (x[i] == y[j]) {
            return cache[i][j] = dfs(i - 1, j - 1) + 1;
        }

        return cache[i][j] = Math.max(dfs(i - 1, j), dfs(i, j - 1));
    }


    public static void main(String[] args) {
        String text1 = "ace";
        String text2 = "abcde";
        No1143_longestCommonSubsequence sol = new No1143_longestCommonSubsequence();
        System.out.println(sol.longestCommonSubsequence(text1, text2));
    }
}

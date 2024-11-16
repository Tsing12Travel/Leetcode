package top100;

public class No72_MinDistance {
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // 有一个字符串为空串
        if (n * m == 0) {
            return n + m;
        }

        // DP 数组
        int[][] D = new int[n + 1][m + 1];

        // 边界状态初始化
        for (int i = 0; i < n + 1; i++) {
            D[i][0] = i;
        }
        for (int j = 0; j < m + 1; j++) {
            D[0][j] = j;
        }

        // 计算所有 DP 值
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                int left = D[i - 1][j] + 1;
                int down = D[i][j - 1] + 1;
                int left_down = D[i - 1][j - 1];
                if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                    left_down += 1;
                }
                D[i][j] = Math.min(left, Math.min(down, left_down));
            }
        }
        return D[n][m];
    }


    Integer[][] memo;

    public int minDistance2(String text1, String text2) {
        // dfs(i, j) 表示 s 的 0...i 个字符和 t 的 0...j 个字符的编辑距离
        // 状态转移方程: dfs(i, j) = ?
        // dfs(i - 1, j - 1);      if s[i] == t[j]
        // dfs(i - 1, j) + 1;      if s[i] != t[j] (在 s 上删除一个字符)
        // dfs(i, j - 1) + 1;      if s[i] != t[j] (在 s 上插入一个字符)
        // dfs(i - 1, j - 1) + 1;  if s[i] != t[j] (在 s 上替换一个字符)

        char[] s = text1.toCharArray(), t = text2.toCharArray();
        int n = s.length, m = t.length;
        memo = new Integer[n][m];
        return dfs(n - 1, m - 1, s, t);
    }

    public int dfs(int i, int j, char[] cas, char[] cat) {
        if (i < 0 || j < 0) return Math.max(i, j) + 1;
        if (memo[i][j] != null) return memo[i][j];

        int ans;
        if (cas[i] == cat[j]) {
            ans = dfs(i - 1, j - 1, cas, cat);
        } else {
            int insert = dfs(i, j - 1, cas, cat);
            int delete = dfs(i - 1, j, cas, cat);
            int remove = dfs(i - 1, j - 1, cas, cat);
            ans = Math.min(insert, Math.min(delete, remove)) + 1;
        }
        return memo[i][j] = ans;
    }

    public static void main(String[] args) {
        String word1 = "intention";
        String word2 = "execution";
        No72_MinDistance sol = new No72_MinDistance();
        System.out.println(sol.minDistance(word1, word2));
    }
}

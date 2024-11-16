package helloAlgo;

/* 爬楼梯：给定一个共有 n 阶的楼梯，你每步可以上 1 阶或者 2 阶，请问有多少种方案可以爬到楼顶？ */
/* 搜索 */
public class ClimbingStairsDFS {
    public int dfs(int i) {
        // 已知 dp[1] 和 dp[2] ，返回之
        if (i == 1 || i == 2) {
            return i;
        }

        // dp[i] = dp[i-1] + dp[i-2]
        int count = dfs(i - 1) + dfs(i - 2);
        return count;
    }

    /* 爬楼梯：搜索 */
    public int climbStairsDFS(int n) {
        return dfs(n);
    }

    public static void main(String[] args) {
        ClimbingStairsDFS c = new ClimbingStairsDFS();
        System.out.println(c.climbStairsDFS(3));
    }
}

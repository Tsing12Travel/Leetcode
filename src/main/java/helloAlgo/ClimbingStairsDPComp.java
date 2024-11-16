package helloAlgo;

/* 爬楼梯：空间优化后的动态规划 */
public class ClimbingStairsDPComp {
    public int climbStairs(int n) {
        if (n == 1 || n == 2) {
            return n;
        }

        int a = 1, b = 2;
        for (int i = 3; i <= n; i++) {
            int temp = a;
            b = a + b;
            a = temp;
        }

        return b;
    }
}

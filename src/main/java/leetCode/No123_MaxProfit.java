package leetCode;

public class No123_MaxProfit {
    public int maxProfit(int[] prices) {
        final int k = 2;
        int[][] f = new int[k + 2][2];

        for (int j = 1; j <= k + 1; j++) {
            f[j][1] = Integer.MIN_VALUE / 2; // 防止溢出
        }

        f[0][0] = Integer.MIN_VALUE / 2;

        for (int p : prices) {
            for (int j = k + 1; j > 0; j--) {
                f[j][0] = Math.max(f[j][0], f[j][1] + p);
                f[j][1] = Math.max(f[j][1], f[j - 1][0] - p);
            }
        }

        return f[k + 1][0];
    }


    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        // 初始化状态
        int buy1 = -prices[0], sell1 = 0;
        int buy2 = -prices[0], sell2 = 0;

        for (int i = 1; i < prices.length; i++) {
            // 更新状态
            buy1 = Math.max(buy1, -prices[i]);           // 第一次买入
            sell1 = Math.max(sell1, buy1 + prices[i]);   // 第一次卖出
            buy2 = Math.max(buy2, sell1 - prices[i]);    // 第二次买入
            sell2 = Math.max(sell2, buy2 + prices[i]);   // 第二次卖出
        }

        return sell2; // 返回最终的最大利润
    }


    public static void main(String[] args) {
        No123_MaxProfit No123 = new No123_MaxProfit();

        // 测试用例
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        System.out.println("最大利润: " + No123.maxProfit2(prices)); // 输出 6
    }
}

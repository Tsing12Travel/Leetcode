package top100;

public class No121_MaxProfit {
    // 如果第 i 天卖出股票，则最大利润为 (该天的股价 - 前面天数中最小的股价)，然后与已知的最大利润比较，如果大于则更新当前最大利润的值
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int profit = 0;
        int cost = Integer.MAX_VALUE;

        for (int price : prices) {
            cost = Math.min(cost, price);
            profit = Math.max(profit, price - cost);
        }
        return profit;
    }


    // 逆序遍历，找每个元素的右边最大，即最优的卖出，然后减去该元素，即在这一天买入能获得最大利润
    public int maxProfit2(int[] prices) {
        int res = 0, rightMax = 0;
        for (int i = prices.length - 1; i >= 0; i--) {
            rightMax = Math.max(rightMax, prices[i]);
            res = Math.max(res, rightMax - prices[i]);
        }
        return res;
    }


    // 暴力解法
    public int maxProfit3(int[] prices) {
        int maxprofit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                int profit = prices[j] - prices[i];
                if (profit > maxprofit) {
                    maxprofit = profit;
                }
            }
        }
        return maxprofit;
    }


    public static void main(String[] args) {
        No121_MaxProfit profit = new No121_MaxProfit();
        System.out.println(profit.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }
}

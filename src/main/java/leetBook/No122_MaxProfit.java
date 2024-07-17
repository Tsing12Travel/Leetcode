package leetBook;

/*解题思路：
对于单独交易日：设今天价格为 p1，明天价格为 p2，则今天买入，明天卖出可赚取 p2 - p1 (若为负值，则代表亏损)

对于连续上涨交易日：设此上涨交易日股票价格分别为 p1、p2、p3 ... pn，则第一天买入最后一天卖出收益最大，即 pn -p1，
等价于每天都买卖，即 pn - p1 = (p2 - p1) + (p3 - p2) + ... + (pn - p下标(n-1))
“等价于每天都买卖”，把可能跨越多天的买卖都化解成相邻两天的买卖

对于连续下降交易日： 则不买卖收益最大，即不会亏钱
*/
public class No122_MaxProfit {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;

        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            int temp = prices[i] - prices[i - 1];
            if (temp > 0) profit += temp;
        }

        return profit;
    }


    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        No122_MaxProfit maxProfit = new No122_MaxProfit();
        System.out.println(maxProfit.maxProfit(prices));
    }
}

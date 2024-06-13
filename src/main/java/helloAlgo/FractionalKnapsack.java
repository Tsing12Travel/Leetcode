package helloAlgo;

import java.util.Arrays;
import java.util.Comparator;

public class FractionalKnapsack {
    // 物品
    class Item {
        int w;  // 物品重量
        int v;  // 物品价值

        public Item(int w, int v) {
            this.w = w;
            this.v = v;
        }
    }

    public double fractionalKnapsack(int[] wgt, int[] val, int cap) {
        // 创建物品列表，包含两个属性：重量、价格
        Item[] items = new Item[wgt.length];
        for (int i = 0; i < wgt.length; i++) {
            items[i] = new Item(wgt[i], val[i]);
        }

        // 按照单位价值 items.v / items.w 从高到低进行排序
        Arrays.sort(items, Comparator.comparingDouble(item -> -((double) item.v / item.w)));

        // 循环贪心选择
        double res = 0;
        for (Item item : items) {
            if (item.w < cap) {
                // 若剩余容量充足，则将当前物品整个装进背包
                res += item.v;
                cap -= item.w;
            } else {
                // 若剩余容量不足，则将当前物品的一部分装进背包
                res += (double) item.v / item.w * cap;
                // 已无剩余容量，跳出循环
                break;
            }
        }
        return res;
    }
}

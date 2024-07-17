package leetBook;

import java.util.*;

public class No347_TopKFrequent {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        // 统计每个数字出现的次数。key: 数字 value: 对应数字出现的次数
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        ArrayList<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        // Collections.sort(list, (x, y) -> y.getValue() - x.getValue());
        list.sort((x, y) -> y.getValue() - x.getValue());

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = list.get(i).getKey();
        }
        return res;
    }


    public static void main(String[] args) {
        No347_TopKFrequent f = new No347_TopKFrequent();
        int[] nums = {1, 1, 1, 2, 2, 3};
        System.out.println(Arrays.toString(f.topKFrequent(nums, 2)));
    }
}

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


    public int[] topKFrequent2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> map.get(a) - map.get(b));
        for (Integer key : map.keySet()) {
            if (pq.size() < k) {
                pq.add(key);
            // } else if (pq.size() >= k && map.get(key) > map.get(pq.peek())) {
            } else if (map.get(key) > map.get(pq.peek())) {  // 此处隐含了 pq.size() >= k
                pq.poll();
                pq.add(key);
            }
        }

        int[] res = new int[k];
        int index = 0;
        while (!pq.isEmpty()) {
            res[index++] = pq.poll();
        }

        return res;
    }


    public static void main(String[] args) {
        No347_TopKFrequent f = new No347_TopKFrequent();
        int[] nums = {1, 1, 1, 2, 2, 3};
        System.out.println(Arrays.toString(f.topKFrequent2(nums, 2)));
    }
}

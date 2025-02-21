package leetCode;

import java.util.HashMap;
import java.util.Map;

/*2404. 出现最频繁的偶数元素
给你一个整数数组 nums ，返回出现最频繁的偶数元素。
如果存在多个满足条件的元素，只需要返回 最小 的一个。如果不存在这样的元素，返回 -1 。*/
public class No2404_MostFrequentEven {
    /*时间复杂度：
    遍历数组一次，每次对哈希表的操作（插入和查找）是 𝑂(1)，所以总的时间复杂度是 𝑂(𝑛)，其中 n 是数组的长度。
    空间复杂度：
    使用了哈希表 freqMap 来存储偶数及其频次，最坏情况下，空间复杂度是 𝑂(𝑛)，因为哈希表可能存储数组中所有的偶数。*/
    public int mostFrequent(int[] nums) {
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        int res = -1;  // 记录最频繁且最小的偶数
        int maxFreq = 0;  // 记录最大频率

        for (int num : nums) {
            if (num % 2 != 0) continue;  // 跳过奇数

            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);

            if (freqMap.get(num) > maxFreq || (freqMap.get(num) == maxFreq && num < res)) {
                maxFreq = freqMap.get(num);
                res = num;
            }
        }

        return res;
    }


    /*时间复杂度：
    遍历一次 nums 数组来统计频次：𝑂(𝑛)，其中 n 是数组的长度。遍历哈希表来找到最频繁的偶数：𝑂(𝑘)，其中 k 是哈希表中偶数元素的个数。总的时间复杂度是 𝑂(𝑛+𝑘)。
    空间复杂度：
    使用哈希表存储偶数及其频次，空间复杂度为 𝑂(𝑘)，其中 k 是哈希表中偶数的数量。*/
    public int mostFrequentEven2(int[] nums) {
        Map<Integer, Integer> freqMap = new HashMap<>();

        // 统计偶数元素的频率
        for (int num : nums) {
            if (num % 2 == 0) {
                freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
            }
        }

        int maxFreq = 0;
        int result = -1;

        // 查找出现频率最高的偶数，若频率相同则选择最小的
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            int num = entry.getKey();
            int freq = entry.getValue();
            if (freq > maxFreq || (freq == maxFreq && num < result)) {
                maxFreq = freq;
                result = num;
            }
        }

        return result;
    }


    public static void main(String[] args) {
        No2404_MostFrequentEven No2404 = new No2404_MostFrequentEven();
        int[] nums = {0, 1, 2, 2, 4, 4, 1};
        int[] nums2 = {4, 4, 4, 9, 2, 4};
        int[] nums3 = {29, 47, 21, 41, 13, 37, 25, 7};
        System.out.println(No2404.mostFrequent(nums));
        System.out.println(No2404.mostFrequent(nums2));
        System.out.println(No2404.mostFrequent(nums3));
    }
}

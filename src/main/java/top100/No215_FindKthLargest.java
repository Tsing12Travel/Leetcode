package top100;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class No215_FindKthLargest {
    public int findKthLargest(int[] nums, int k) {
        List<Integer> num_list = new ArrayList<Integer>();
        for (int num : nums) {
            num_list.add(num);
        }
        return quickSelect(num_list, k);
    }

    private int quickSelect(List<Integer> num_list, int k) {
        // 随机选择基准数
        Random rand = new Random();
        int pivot = num_list.get(rand.nextInt(num_list.size()));

        // 将大于、等于、小于 pivot 的元素划分至 big、equal、small 中
        List<Integer> big = new ArrayList<>();
        List<Integer> equal = new ArrayList<>();
        List<Integer> small = new ArrayList<>();

        for (int num : num_list) {
            if (num > pivot) {
                big.add(num);
            } else if (num < pivot) {
                small.add(num);
            } else {
                equal.add(num);
            }
        }

        // 第 k 大元素在 big 中，递归划分
        if (k <= big.size()) {
            return quickSelect(big, k);
        }
        // 第 k 大元素在 small 中，递归划分
        if (big.size() + equal.size() < k) {
            return quickSelect(small, k - (big.size() + equal.size()));
        }
        // 第 k 大元素在 equal 中，直接返回 pivot
        return pivot;
    }


    public int findKthLargest2(int[] nums, int k) {
        int[] buckets = new int[20001];
        for (int i = 0; i < nums.length; i++) {
            buckets[nums[i] + 10000]++;
        }

        for (int i = 20000; i >= 0; i--) {
            k = k - buckets[i];
            if (k <= 0) {
                return i - 10000;
            }
        }
        return 0;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 1, 5, 6, 4};
        int k = 2;
        No215_FindKthLargest no215 = new No215_FindKthLargest();
        System.out.println(no215.findKthLargest2(nums, k));
    }
}

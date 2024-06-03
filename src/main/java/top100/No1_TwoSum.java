package top100;

import java.util.HashMap;

public class No1_TwoSum {
    static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{target - nums[i], i};
            }
            map.put(nums[i], i);
        }

        return null;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 5, 6, 3, 9, 0};
        System.out.println(twoSum(nums, 5));
    }
}

package top100;

public class No53_MaxSubArray {
    public int maxSubArray(int[] nums) {
        int pre = 0;
        int max = nums[0];

        for (int num : nums) {
            // 如果前边累加后还不如自己本身大，那就把前边的都扔掉，从此自己本身重新开始累加
            pre = Math.max(pre + num, num);
            max = Math.max(max, pre);
        }

        return max;
    }
}

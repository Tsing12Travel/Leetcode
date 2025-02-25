package top100;

public class No53_MaxSubArray {
    public int maxSubArray(int[] nums) {
        int pre = 0;
        int max = nums[0];

        for (int num : nums) {
            // 如果前边累加后还不如自己本身大，那就把前边的都扔掉，从自己本身重新开始累加
            pre = Math.max(pre + num, num);
            max = Math.max(max, pre);
        }

        return max;
    }


    public static void main(String[] args) {
        No53_MaxSubArray No53 = new No53_MaxSubArray();
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] nums2 = {1};
        int[] nums3 = {5, 4, -1, 7, 8};
        System.out.println(No53.maxSubArray(nums));
        System.out.println(No53.maxSubArray(nums2));
        System.out.println(No53.maxSubArray(nums3));
    }
}

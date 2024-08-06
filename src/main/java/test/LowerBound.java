package test;

public class LowerBound {
    public int lowerBound(int[] nums, int k) {
        int len = nums.length;
        if (len == 0) return 0;

        int left = 0;
        int right = len - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 3, 3, 3, 3, 5, 6, 7};
        LowerBound lb = new LowerBound();
        System.out.println(lb.lowerBound(nums, 5));
    }
}

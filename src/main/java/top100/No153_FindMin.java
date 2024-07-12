package top100;

public class No153_FindMin {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] <= nums[nums.length - 1]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return nums[left];
    }


    public static void main(String[] args) {
        No153_FindMin n = new No153_FindMin();
        int[] nums = new int[]{4, 5, 6, 7, 0, 1, 2};
        System.out.println(n.findMin(nums));
    }
}

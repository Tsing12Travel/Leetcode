package top100;

public class No4_FindMedianSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        // 确保 nums1 是较短的数组
        if (m > n) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int imin = 0, imax = m;
        int halfLen = (m + n + 1) / 2;
        while (imin <= imax) {
            int i = (imin + imax) / 2;
            int j = halfLen - i;
            if (i < m && nums1[i] < nums2[j - 1]) {
                imin = i + 1; // i 太小
            } else if (i > 0 && nums1[i - 1] > nums2[j]) {
                imax = i - 1; // i 太大
            } else {
                // i 是完美的
                int maxOfLeft;
                if (i == 0) {
                    maxOfLeft = nums2[j - 1];
                } else if (j == 0) {
                    maxOfLeft = nums1[i - 1];
                } else {
                    maxOfLeft = Math.max(nums1[i - 1], nums2[j - 1]);
                }
                if ((m + n) % 2 == 1) {
                    return maxOfLeft;
                }

                int minOfRight;
                if (i == m) {
                    minOfRight = nums2[j];
                } else if (j == n) {
                    minOfRight = nums1[i];
                } else {
                    minOfRight = Math.min(nums1[i], nums2[j]);
                }

                return (maxOfLeft + minOfRight) / 2.0;
            }
        }

        throw new IllegalArgumentException("Input arrays are not sorted.");
    }

    public static void main(String[] args) {
        No4_FindMedianSortedArrays solution = new No4_FindMedianSortedArrays();
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        System.out.println(solution.findMedianSortedArrays(nums1, nums2)); // 输出: 2.0

        int[] nums3 = {1, 2};
        int[] nums4 = {3, 4};
        System.out.println(solution.findMedianSortedArrays(nums3, nums4)); // 输出: 2.5
    }

}

package top100;

/* 11.盛水最多的容器 */
// 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
// 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
// 返回容器可以储存的最大水量。
// 说明：你不能倾斜容器。

public class No11_MaxArea {
    public static int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int ans = 0;

        while (left < right) {
            int currentVolume = Math.min(height[left], height[right]) * (right - left);
            ans = Math.max(ans, currentVolume);
            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return ans;
    }


    public static int maxArea2(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int res = 0;

        while (left < right) {
            res = height[left] < height[right] ? Math.max(res, (right - left) * height[left++]) : Math.max(res, (right - left) * height[right--]);
        }

        return res;
    }


    public static void main(String[] args) {
        int[] height = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        // int[] height = new int[]{1, 1};
        System.out.println(maxArea2(height));
    }
}

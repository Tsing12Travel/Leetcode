package top100;

import java.util.ArrayDeque;
import java.util.Deque;

// 思路：以当前 height[i] 为高度时，其宽度最大可以是多少
// 即找出左右两边第一个小于 height[i] 高度(height[left]、height[right])对应的下标 left、right，此时宽度为 right - left - 1
/* 假设 h=heights[i] 是矩形的高度，那么矩形的宽度最大是多少？我们需要知道：
在 i 左侧的小于 h 的最近元素的下标 left，如果不存在则为 −1。这样 left+1 就是在 i 左侧的大于等于 h 的最近元素的下。
在 i 右侧的小于 h 的最近元素的下标 right，如果不存在则为 n。这样 right−1 就是在 i 右侧的大于等于 h 的最近元素的下标。

如对于：heights = [2, 1, 5, 6, 2, 3]
选择 i = 2 这个柱子作为矩形高，那么左边小于 heights[2] = 5 的最近元素的下标为 left = 1，右边小于 heights[2] = 5 的最近元素的下标为 right = 4
那么矩形的宽度就是 right − left − 1 = 4 − 1 − 1 = 2，矩形面积为 h × (right − left − 1)= 5 × 2 = 10。

枚举 i，计算对应的矩形面积，更新答案的最大值
如何快速计算 left 和 right？这可以用单调栈求出
*/
public class No84_LargestRectangleArea {
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        if (len == 0) return 0;
        Deque<Integer> stack = new ArrayDeque<>();

        int[] left = new int[len];
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        int[] right = new int[len];
        for (int i = len - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? len : stack.peek();
            stack.push(i);
        }

        int max = 0;
        for (int i = 0; i < len; i++) {
            max = Math.max(max, heights[i] * (right[i] - left[i] - 1));
        }
        return max;
    }


    public static void main(String[] args) {
        // int[] heights = new int[]{2, 1, 5, 6, 2, 3};
        int[] heights = new int[]{2, 4};
        No84_LargestRectangleArea test = new No84_LargestRectangleArea();
        System.out.println(test.largestRectangleArea(heights));
    }
}

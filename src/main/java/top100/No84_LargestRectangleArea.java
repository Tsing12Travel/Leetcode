package top100;

import java.util.ArrayDeque;
import java.util.Deque;

// 思路：以当前 height[i] 为高度时，其宽度最大可以是多少
// 即找出左右两边第一个小于 height[i] 高度(height[left]、height[right])对应的下标 left、right，此时宽度为 right - left - 1
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

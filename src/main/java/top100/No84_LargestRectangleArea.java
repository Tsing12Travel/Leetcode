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


    // 暴力解法：枚举以每个柱形为高度的最大矩形的面积。即依次遍历柱形的高度，对于每一个高度分别向两边扩散，求出以当前高度为矩形的最大宽度多少
    public int largestRectangleArea2(int[] heights) {
        int len = heights.length;
        if (len == 0) return 0;  // 特判
        int max = 0;

        for (int i = 0; i < len; i++) {
            int currHeight = heights[i];

            // 找左边最后 1 个大于等于 heights[i] 的下标
            int left = i;
            while (left > 0 && heights[left - 1] >= currHeight) {
                left--;
            }

            // 找右边最后 1 个大于等于 heights[i] 的索引
            int right = i;
            while (right < len - 1 && heights[right + 1] >= currHeight) {
                right++;
            }

            max = Math.max(max, (right - left + 1) * currHeight);
        }
        return max;
    }


    public int largestRectangleArea3(int[] heights) {
        int len = heights.length;
        if (len == 0) return 0;
        if (len == 1) return heights[0];

        int res = 0;

        int[] newHeights = new int[len + 2];  // 首尾存储哨兵(均为 0)，故长度需要 +2
        newHeights[0] = 0;
        System.arraycopy(heights, 0, newHeights, 1, len);
        newHeights[len + 1] = 0;
        len += 2;  // 新数组的长度
        heights = newHeights;  // newHeights 赋给 heights，竟然节省了内存？！

        Deque<Integer> stack = new ArrayDeque<>(len);
        // 先放入哨兵，在循环里就不用做非空判断
        stack.addLast(0);

        for (int i = 1; i < len; i++) {
            while (heights[i] < heights[stack.peekLast()]) {
                int curHeight = heights[stack.pollLast()];
                int curWidth = i - stack.peekLast() - 1;
                res = Math.max(res, curHeight * curWidth);
            }
            stack.addLast(i);
        }
        return res;
    }


    public static void main(String[] args) {
        // int[] heights = new int[]{2, 1, 5, 6, 2, 3};
        int[] heights = new int[]{2, 4};
        No84_LargestRectangleArea test = new No84_LargestRectangleArea();
        System.out.println(test.largestRectangleArea3(heights));
    }
}

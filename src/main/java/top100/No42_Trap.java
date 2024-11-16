package top100;

import java.util.ArrayDeque;
import java.util.Deque;

public class No42_Trap {
    // 相向双指针
    // 注意 while 循环可以不加等号，因为在「谁小移动谁」的规则下，相遇的位置一定是最高的柱子，这个柱子是无法接水的
    public int trap(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int preMax = 0;  // 前缀最大值，随着左指针 left 的移动而更新
        int sufMax = 0;  // 后缀最大值，随着右指针 right 的移动而更新
        int res = 0;

        while (left < right) {
            preMax = Math.max(preMax, height[left]);
            sufMax = Math.max(sufMax, height[right]);

            res += preMax < sufMax ? (preMax - height[left++]) : (sufMax - height[right--]);
        }

        return res;
    }


    public int trap2(int[] height) {
        int n = height.length;
        int res = 0;
        // 左右指针：分别指向左右两边界的列
        int left = 0, right = n - 1;
        // 左指针的左边最大高度、右指针的右边最大高度
        int leftMax = height[left], rightMax = height[right];
        // 最两边的列存不了水
        left++;
        right--;
        // 向中间靠拢
        while (left <= right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (leftMax < rightMax) {
                // 左指针的leftMax比右指针的rightMax矮
                // 说明：左指针的右边至少有一个板子 > 左指针左边所有板子
                // 根据水桶效应，保证了左指针当前列的水量决定权在左边
                // 那么可以计算左指针当前列的水量：左边最大高度-当前列高度
                res += leftMax - height[left];
                left++;
            } else {
                // 右边同理
                res += rightMax - height[right];
                right--;
            }
        }
        return res;
    }


    // 前后缀分解。后两个 for 循环可以合并成一个循环，此处为了方便阅读，没有合并
    public int trap3(int[] height) {
        int n = height.length;
        int[] preMax = new int[n];  // preMax[i] 表示从 height[0] 到 height[i] 的最大值
        preMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            preMax[i] = Math.max(preMax[i - 1], height[i]);
        }

        int[] sufMax = new int[n];  // sufMax[i] 表示从 height[i] 到 height[n-1] 的最大值
        sufMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            sufMax[i] = Math.max(sufMax[i + 1], height[i]);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += Math.min(preMax[i], sufMax[i]) - height[i];  // 累加每个水桶能接多少水
        }
        return ans;
    }


    // 单调栈：找上一个更大元素，在找的过程中填坑
    // 上面的方法相当于「竖着」计算面积，单调栈的做法相当于「横着」计算面积。
    // 注意 while 中加了等号，这可以让栈中没有重复元素，从而在有很多重复元素的情况下，使用更少的空间
    public int trap4(int[] height) {
        int ans = 0;
        Deque<Integer> st = new ArrayDeque<>();
        for (int i = 0; i < height.length; i++) {
            while (!st.isEmpty() && height[i] >= height[st.peek()]) {
                int bottomH = height[st.pop()];
                if (st.isEmpty()) {
                    break;
                }
                int left = st.peek();
                int dh = Math.min(height[left], height[i]) - bottomH;  // 面积的高
                ans += dh * (i - left - 1);
            }
            st.push(i);
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        No42_Trap test = new No42_Trap();
        System.out.println(test.trap(height));
    }
}

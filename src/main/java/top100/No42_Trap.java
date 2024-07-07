package top100;

public class No42_Trap {
    public int trap(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int preMax = 0;
        int sufMax = 0;
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


    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        No42_Trap test = new No42_Trap();
        System.out.println(test.trap(height));
    }
}

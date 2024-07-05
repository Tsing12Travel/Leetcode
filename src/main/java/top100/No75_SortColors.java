package top100;

import java.util.Arrays;

public class No75_SortColors {
    // 方法 0：遍历获得 0、1、2 的个数，然后重新输出数组

    // 两次遍历，第一次把所有 0 放到头部，第二次把 1 放到 0 后面
    public void sortColors(int[] nums) {
        int len = nums.length;
        int ptr = 0;

        for (int i = 0; i < len; i++) {
            if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[ptr];
                nums[ptr] = temp;
                ptr++;
            }
        }

        for (int i = ptr; i < len; i++) {
            if (nums[i] == 1) {
                int temp = nums[i];
                nums[i] = nums[ptr];
                nums[ptr] = temp;
                ptr++;
            }
        }
    }


    // 有点类似刷油漆
    public void sortColors2(int[] nums) {
        int n0 = 0, n1 = 0;

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            nums[i] = 2;

            if (num < 2) {
                nums[n1++] = 1;
            }

            if (num < 1) {
                nums[n0++] = 0;
            }
        }
    }


    // p0 交换 0，p1 交换 1
    public void sortColors3(int[] nums) {
        int n = nums.length;
        int p0 = 0, p1 = 0;

        for (int i = 0; i < n; ++i) {
            if (nums[i] == 1) {
                int temp = nums[i];
                nums[i] = nums[p1];
                nums[p1] = temp;
                ++p1;
            } else if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[p0];
                nums[p0] = temp;

                if (p0 < p1) {
                    temp = nums[i];
                    nums[i] = nums[p1];
                    nums[p1] = temp;
                }

                ++p0;
                ++p1;
            }
        }
    }


    // p0 交换 0，p2 交换 2
    public void sortColors4(int[] nums) {
        int n = nums.length;
        int p0 = 0, p2 = n - 1;

        for (int i = 0; i <= p2; ++i) {
            while (i <= p2 && nums[i] == 2) {
                int temp = nums[i];
                nums[i] = nums[p2];
                nums[p2] = temp;
                --p2;
            }

            if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[p0];
                nums[p0] = temp;
                ++p0;
            }
        }
    }


    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        No75_SortColors test = new No75_SortColors();
        test.sortColors2(nums);
        System.out.println(Arrays.toString(nums));
    }
}

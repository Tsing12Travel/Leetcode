package top100;

public class No287_FindDuplicate {
    // 快慢指针
    // Floyd 判圈算法：如果有限状态机、迭代函数或者链表上存在环，那么在某个环上以不同速度前进的 2 个指针必定会在某个时刻相遇
    public int findDuplicate(int[] nums) {
        int slow = 0, fast = 0;

        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }


    // 二分查找
    public int findDuplicate2(int[] nums) {
        int n = nums.length;
        int l = 1, r = n - 1, ans = -1;

        while (l <= r) {
            int mid = (l + r) >> 1;
            int cnt = 0;

            for (int num : nums) {
                if (num <= mid) {
                    cnt++;
                }
            }

            if (cnt <= mid) {
                l = mid + 1;
            } else {
                r = mid - 1;
                ans = mid;
            }
        }
        return ans;
    }


    // 二进制：将所有数二进制展开按位考虑如何找出重复的数，如果我们能确定重复数每一位是 1 还是 0 就可以按位还原出重复的数是什么
    public int findDuplicate3(int[] nums) {
        int n = nums.length, ans = 0;
        int bit_max = 31;

        while (((n - 1) >> bit_max) == 0) {
            bit_max -= 1;
        }

        for (int bit = 0; bit <= bit_max; ++bit) {
            int x = 0, y = 0;
            for (int i = 0; i < n; ++i) {
                if ((nums[i] & (1 << bit)) != 0) {
                    x += 1;
                }

                if (i >= 1 && ((i & (1 << bit)) != 0)) {
                    y += 1;
                }
            }

            if (x > y) {
                ans |= 1 << bit;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        // int[] nums = {1, 3, 4, 2, 2};
        int[] nums = {3, 3, 3, 3, 3};
        No287_FindDuplicate sol = new No287_FindDuplicate();
        System.out.println(sol.findDuplicate(nums));
    }
}

package top100;

public class No287_FindDuplicate {
    // 「Floyd 判圈算法」
    // 如果有限状态机、迭代函数或者链表上存在环，那么在某个环上以不同速度前进的2个指针必定会在某个时刻相遇
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


    public static void main(String[] args) {
        // int[] nums = {1, 3, 4, 2, 2};
        int[] nums = {3, 3, 3, 3, 3};
        No287_FindDuplicate sol = new No287_FindDuplicate();
        System.out.println(sol.findDuplicate(nums));
    }
}

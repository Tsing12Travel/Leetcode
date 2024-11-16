package top100;

public class No55_CanJump {
    /*遍历数组同时维护能跳到最远位置。算法如下：
    从左到右遍历 nums，同时维护能跳到的最远位置 mx，初始值为 0。
    如果 i > mx，说明无法跳到 i，返回 false。
    否则，用 i + nums[i] 更新 mx 的最大值。
    如果循环中没有返回 false，那么最后返回 true。*/
    public boolean canJump(int[] nums) {
        int mx = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            if (i > mx) return false;  // 无法到达 i
            mx = Math.max(mx, i + nums[i]);  // 从 i 最右可以跳到 i + nums[i]
        }

        return true;
    }


    // mx ≥ n−1 时就返回 true，可以让我们提前退出循环
    public boolean canJump2(int[] nums) {
        int mx = 0;

        for (int i = 0; mx < nums.length - 1; i++) {
            if (i > mx) return false;  // 无法到达 i
            mx = Math.max(mx, i + nums[i]);  // 从 i 最右可以跳到 i + nums[i]
        }

        return true;
    }


    public boolean canJump3(int[] nums) {
        int n = nums.length;
        int rightmost = 0;
        for (int i = 0; i < n; ++i) {
            if (i <= rightmost) {
                rightmost = Math.max(rightmost, i + nums[i]);
                if (rightmost >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }


    public static void main(String[] args) {
        No55_CanJump test = new No55_CanJump();
        System.out.println(test.canJump(new int[]{2, 3, 1, 1, 4}));
    }
}

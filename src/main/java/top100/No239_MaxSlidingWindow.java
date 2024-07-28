package top100;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class No239_MaxSlidingWindow {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        if (len == 0 || k == 0) return new int[0];

        // Deque<Integer> deque = new LinkedList<>();  // 增加、删除多的场景 LinkedList 合适
        Deque<Integer> deque = new ArrayDeque<>();  // 使用 ArrayDeque 更省空间。但需要注意使用场景，查询、修改多的场景使用 array 合适
        int[] res = new int[len - k + 1];

        // 未形成窗口
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                deque.removeLast();
            }
            deque.addLast(nums[i]);
        }

        res[0] = deque.peekFirst();

        // 形成窗口后
        for (int i = k; i < len; i++) {
            if (deque.peekFirst() == nums[i - k]) {
                deque.removeFirst();
            }

            while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                deque.removeLast();
            }
            deque.addLast(nums[i]);
            res[i - k + 1] = deque.peekFirst();
        }

        return res;
    }


    // 暴力解法
    public int[] maxSlidingWindow2(int[] nums, int k) {
        int n = nums.length;
        if (n == 0 || k <= 0) return new int[0];

        int[] res = new int[n - k + 1];

        for (int i = 0; i < n - k + 1; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                if (nums[j] > max) max = nums[j];
            }
            res[i] = max;
        }

        return res;
    }


    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        No239_MaxSlidingWindow slidingWindow = new No239_MaxSlidingWindow();
        int[] res = slidingWindow.maxSlidingWindow(nums, k);
        System.out.println(Arrays.toString(res));
    }
}

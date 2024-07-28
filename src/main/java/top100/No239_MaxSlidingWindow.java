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


    // 未形成窗口和形成窗口不拆开
    public int[] maxSlidingWindow2(int[] nums, int k) {
        int len = nums.length;
        if (len == 0 || k == 0) return new int[0];

        Deque<Integer> deque = new ArrayDeque<>();
        int[] res = new int[len - k + 1];

        for (int i = 0; i < len; i++) {
            // 移除窗口之外的索引
            if (!deque.isEmpty() && deque.peek() < i - k + 1) {
                deque.poll();
            }

            // 移除所有比当前元素小的元素
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            // 添加当前元素的索引
            deque.offer(i);

            // 当 i 达到窗口大小时，开始记录窗口最大值
            if (i >= k - 1) {
                res[i - k + 1] = nums[deque.peek()];
            }
        }
        return res;
    }


    // 暴力解法
    public int[] maxSlidingWindow3(int[] nums, int k) {
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
        int[] res = slidingWindow.maxSlidingWindow2(nums, k);
        System.out.println(Arrays.toString(res));
    }
}

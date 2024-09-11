package top100;

import java.util.*;

public class No239_MaxSlidingWindow {
    /* 遍历数组时，每轮保证单调队列 deque ：
    deque 内 仅包含窗口内的元素 ⇒ 每轮窗口滑动移除了元素 nums[i − 1] ，需将 deque 内的对应元素一起删除。
    deque 内的元素 非严格递减 ⇒ 每轮窗口滑动添加了元素 nums[j + 1] ，需将 deque 内所有 < nums[j + 1] 的元素删除。 */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        if (len == 0 || k == 0) return new int[0];

        // 窗口对应的数据结构为 双端队列 ，本题使用 单调队列
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


    // 使用 PriorityQueue 实现大顶堆的滑动窗口最大值
    public int[] maxSlidingWindow4(int[] nums, int k) {
        int len = nums.length;
        if (len == 0 || k == 0) return new int[0];

        // 大顶堆，比较规则是按照值降序排列，如果值相同则按照下标升序排列
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return b[0] - a[0];  // 根据值降序排列
            }
        });

        int[] res = new int[len - k + 1];

        // 初始化堆，加入前 k 个元素
        for (int i = 0; i < k; i++) {
            maxHeap.add(new int[]{nums[i], i});
        }
        res[0] = maxHeap.peek()[0];  // 第一个窗口的最大值

        // 开始滑动窗口
        for (int i = k; i < len; i++) {
            // 把新元素加入堆
            maxHeap.add(new int[]{nums[i], i});

            // 移除堆顶不在当前窗口的元素（如果它的下标小于 i - k + 1）
            while (maxHeap.peek()[1] <= i - k) {
                maxHeap.poll();
            }

            // 堆顶元素就是当前窗口的最大值
            res[i - k + 1] = maxHeap.peek()[0];
        }

        return res;
    }


    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        No239_MaxSlidingWindow slidingWindow = new No239_MaxSlidingWindow();
        int[] res = slidingWindow.maxSlidingWindow4(nums, k);
        System.out.println(Arrays.toString(res));
    }
}

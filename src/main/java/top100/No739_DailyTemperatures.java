package top100;

import java.util.*;

public class No739_DailyTemperatures {
    // 单调栈
    // 在单调栈基础题中，经常需要类似这种的解题思路：在 O(n) 的时间复杂度内求出数组中各个元素右侧第一个更大的元素及其下标，然后一并得到其他信息
    public int[] dailyTemperatures(int[] temperatures) {
        int len = temperatures.length;
        int[] res = new int[len];
        // java 中的 Stack 会涉及到线程锁，降低性能。而 ArrayList 移除最后一个元素不方便，可采用 LinkedList 或 ArrayDeque 实现 stack
        Deque<Integer> stack = new LinkedList<>();
        // Deque<Integer> stack = new ArrayDeque<>();
        // Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int prevIndex = stack.pop();
                res[prevIndex] = i - prevIndex;
            }
            stack.push(i);
        }
        return res;
    }


    public int[] dailyTemperatures2(int[] temperatures) {
        int n = temperatures.length;
        int[] ans = new int[n];
        ans[n - 1] = 0;

        for (int i = n - 2; i >= 0; i--) {
            int j = i + 1;
            while (j < n && temperatures[j] <= temperatures[i] && ans[j] != 0) {
                j += ans[j];
            }
            if (j < n && temperatures[j] > temperatures[i]) {
                ans[i] = j - i;
            }
        }
        return ans;
    }


    // 暴力解法
    public int[] dailyTemperatures3(int[] temperatures) {
        int length = temperatures.length;
        int[] ans = new int[length];
        int[] next = new int[101];
        Arrays.fill(next, Integer.MAX_VALUE);
        for (int i = length - 1; i >= 0; --i) {
            int warmerIndex = Integer.MAX_VALUE;
            for (int t = temperatures[i] + 1; t <= 100; ++t) {
                if (next[t] < warmerIndex) {
                    warmerIndex = next[t];
                }
            }
            if (warmerIndex < Integer.MAX_VALUE) {
                ans[i] = warmerIndex - i;
            }
            next[temperatures[i]] = i;
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] temp = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        No739_DailyTemperatures DT = new No739_DailyTemperatures();
        System.out.println(Arrays.toString(DT.dailyTemperatures(temp)));
    }
}

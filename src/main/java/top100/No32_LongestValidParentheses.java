package top100;

import java.util.Deque;
import java.util.LinkedList;

public class No32_LongestValidParentheses {
    public int longestValidParentheses(String s) {
        int len = s.length();
        if (len == 0) return 0;

        int res = 0;
        Deque<Integer> stack = new LinkedList<>();
        stack.push(-1);

        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    res = Math.max(res, i - stack.peek());
                }
            }
        }

        return res;
    }


    public int longestValidParentheses2(String s) {
        int len = s.length();
        int left = 0, right = 0;
        int res = 0;

        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }

            if (left == right) {
                res = Math.max(res, 2 * left);
            } else if (right > left) {
                left = 0;
                right = 0;
            }
        }

        left = 0;
        right = 0;
        for (int i = len - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }

            if (left == right) {
                res = Math.max(res, 2 * left);
            } else if (left > right) {
                left = 0;
                right = 0;
            }
        }

        return res;
    }


    public int longestValidParentheses3(String s) {
        int maxans = 0;
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxans = Math.max(maxans, dp[i]);
            }
        }
        return maxans;
    }


    public static void main(String[] args) {
        // String s = "(()";
        String s = ")()())";
        // String s = "";
        No32_LongestValidParentheses sol = new No32_LongestValidParentheses();
        System.out.println(sol.longestValidParentheses2(s));
    }
}

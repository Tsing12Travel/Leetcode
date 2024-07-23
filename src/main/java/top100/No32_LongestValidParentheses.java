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


    /* 利用两个计数器 left 和 right 。
    首先，我们从左到右遍历字符串，对于遇到的每个 ‘(’，我们增加 left 计数器，对于遇到的每个 ‘)’ ，我们增加 right 计数器。
    每当 left 计数器与 right 计数器相等时，我们计算当前有效字符串的长度，并且记录目前为止找到的最长子字符串。
    当 right 计数器比 left 计数器大时，我们将 left 和 right 计数器同时变回 0

    这样的做法贪心地考虑了以当前字符下标结尾的有效括号长度，每次当右括号数量多于左括号数量的时候之前的字符我们都扔掉不再考虑，
    重新从下一个字符开始计算，但这样会漏掉一种情况，就是遍历的时候左括号的数量始终大于右括号的数量，即 (() ，这种时候最长有效括号是求不出来的

    解决的方法也很简单，我们只需要从右往左遍历用类似的方法计算即可，只是这个时候判断条件反了过来：
    当 left 计数器比 right 计数器大时，我们将 left 和 right 计数器同时变回 0
    当 left 计数器与 right 计数器相等时，我们计算当前有效字符串的长度，并且记录目前为止找到的最长子字符串
    这样我们就能涵盖所有情况从而求解出答案 */
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
                // 从左往右遍历时，检查右括号数量是否大于左括号数量。因为从左往右遍历时分隔符为右括号(即右括号会比左括号数量多)，如 ")()())"
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
                // 从右往左遍历时，检查左括号数量是否大于右括号数量。因为从右往左遍历时分隔符为左括号(即左括号会比右括号数量多)，如 ")()())"
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

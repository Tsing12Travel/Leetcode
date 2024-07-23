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


    public static void main(String[] args) {
        // String s = "(()";
        // String s = ")()())";
        String s = "";
        No32_LongestValidParentheses sol = new No32_LongestValidParentheses();
        System.out.println(sol.longestValidParentheses(s));
    }
}

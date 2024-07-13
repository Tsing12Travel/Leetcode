package top100;

import java.util.Stack;

public class No394_DecodeString {
    public String decodeString(String s) {
        int multi = 0;
        StringBuilder res = new StringBuilder();
        Stack<Integer> stack_multi = new Stack<>();
        Stack<StringBuilder> stack_res = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '[') {
                stack_multi.push(multi);
                stack_res.push(res);
                multi = 0;
                res = new StringBuilder();
            } else if (c == ']') {
                int curr_multi = stack_multi.pop();
                StringBuilder curr_res = new StringBuilder();
                for (int i = 0; i < curr_multi; i++) {
                    curr_res.append(res);
                }
                res = stack_res.pop().append(curr_res);
            } else if (c >= '0' && c <= '9') {
                multi = multi * 10 + c - '0';
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }
}

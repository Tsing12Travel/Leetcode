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
                // 遇到括号，记录 multi 和当前 res，并将 multi 归零
                stack_multi.push(multi);
                stack_res.push(res);
                multi = 0;
                res = new StringBuilder();
            } else if (c == ']') {
                // 弹出最近一个左括号的 multi，当前 res 进行计算不入栈
                int curr_multi = stack_multi.pop();
                StringBuilder curr_res = new StringBuilder();
                for (int i = 0; i < curr_multi; i++) {
                    curr_res.append(res);
                }
                // 与括号外合并
                res = stack_res.pop().append(curr_res);
            } else if (c >= '0' && c <= '9') {
                // 如果 multi 是多位，则需要 ×10
                multi = multi * 10 + c - '0';
            } else {
                // 如果是字母，则缓慢添加
                res.append(c);
            }
        }
        return res.toString();
    }
}

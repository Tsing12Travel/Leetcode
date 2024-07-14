package top100;

import java.util.Stack;

public class No394_DecodeString {
    // 辅助栈法
    public String decodeString(String s) {
        int multi = 0;
        StringBuilder res = new StringBuilder();
        Stack<Integer> stack_multi = new Stack<>();  // 存储数字
        Stack<StringBuilder> stack_res = new Stack<>();  // 存储字符串

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


    // 递归
    public String decodeString2(String s) {
        return dfs(s, 0)[0];
    }

    private String[] dfs(String s, int i) {
        StringBuilder res = new StringBuilder();
        int multi = 0;

        while (i < s.length()) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9')
                multi = multi * 10 + Integer.parseInt(String.valueOf(s.charAt(i)));
            else if (s.charAt(i) == '[') {
                String[] tmp = dfs(s, i + 1);
                i = Integer.parseInt(tmp[0]);
                while (multi > 0) {
                    res.append(tmp[1]);
                    multi--;
                }
            } else if (s.charAt(i) == ']')
                return new String[]{String.valueOf(i), res.toString()};
            else
                res.append(String.valueOf(s.charAt(i)));
            i++;
        }

        return new String[]{res.toString()};
    }


    public static void main(String[] args) {
        No394_DecodeString test = new No394_DecodeString();
//        String s = "3[a]2[bc]";
//        String s = "3[a2[c]]";
        String s = "2[abc]3[cd]ef";
        System.out.println(test.decodeString(s));
    }
}
